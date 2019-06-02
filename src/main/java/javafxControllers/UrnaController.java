package javafxControllers;

import candidatos.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

import java.util.List;

import static apllication.LeitorJson.*;

public class UrnaController {
    public Button btnConfirmar;
    public Button btnBranco;
    public Button btnCorrige;
    public Label lblCargo;
    public Label lblVoto;
    public Label lblNumero;
    public Pane telaFim;
    public Pane paneCandidato;
    public AnchorPane paneTutorial;
    public TextField tfVotacao;
    private AudioClip somFim;
    private AudioClip somVoto;

    private static List<Candidato> deputados;
    private static List<Candidato> senadores;
    private static List<Candidato> governadores;
    private static List<Candidato> presidentes;

    static Candidato candidatoEscolhido;
    private List<Candidato> listaCandidatos;
    private int limiteNum;
    private String arquivoTela;

    @FXML
    public void initialize() {
        deputados = new Gson().fromJson(DEPUTADO.lerArquivo(), new TypeToken<List<DeputadoEstadual>>(){}.getType());
        senadores = new Gson().fromJson(SENADOR.lerArquivo(), new TypeToken<List<Senador>>(){}.getType());
        governadores =  new Gson().fromJson(GOVERNADOR.lerArquivo(), new TypeToken<List<Governador>>(){}.getType());
        presidentes =  new Gson().fromJson(PRESIDENTE.lerArquivo(), new TypeToken<List<Presidente>>(){}.getType());
        somFim = new AudioClip(getClass().getResource("../sons/som_fim.mp3").toString());
        somVoto = new AudioClip(getClass().getResource("../sons/som_voto.mp3").toString());

        configurarTela("Deputado Estadual", "depEstadual.fxml", deputados, 5);
    }

    @FXML
    protected void digitarNumero(ActionEvent event){
        Button btn = (Button) event.getSource();
        String num = btn.getText();

        alterarTexto(num, listaCandidatos, limiteNum);
    }

    private void alterarTexto(String num, List<Candidato> candidatos, int limite){
        if(tfVotacao.getLength() < limite) tfVotacao.setText(tfVotacao.getText().concat(num));
        if(tfVotacao.getLength() == limite) procurarCandidato(candidatos);
    }

    @FXML
    protected void corrigir() {
       limpar();
    }

    @FXML
    protected void votarBranco() throws Exception {
        limpar();
        paneCandidato.getChildren().add(FXMLLoader.load(getClass().getResource(
                "/javafxViews/candidatos/votoBranco.fxml")));
        lblVoto.setVisible(true);
        paneTutorial.setVisible(true);
    }

    @FXML
    protected void confirmar() {
        if(paneCandidato.getChildren().size() != 0){
            somVoto.play();
            alterarCargo();
            if(candidatoEscolhido != null) {
                candidatoEscolhido.votar();
                System.out.println(candidatoEscolhido.getNumero() + " | " + candidatoEscolhido.getVotos()); // teste
            }
        } else if (telaFim.isVisible() && !somFim.isPlaying()){
            alterarCargo();
            atualizarArquivosJSON();
        }
        candidatoEscolhido = null;
    }

    private void alterarCargo(){
        switch (lblCargo.getText()){
            case "Deputado Estadual":
                configurarTela("Senador", "senador.fxml", senadores, 3);
                break;
            case "Senador":
                configurarTela("Governador", "governador.fxml", governadores, 2);
                break;
            case "Governador":
                configurarTela("Presidente", "presidente.fxml", presidentes, 2);
                break;
            case "Presidente":
                somFim.play();
                telaFim.setVisible(true);
                lblCargo.setText("FIM");
                break;
            case "FIM":
                telaFim.setVisible(false);
                configurarTela("Deputado Estadual", "depEstadual.fxml", deputados, 5);
                break;
        }
        limpar();
    }

    private Candidato validacaoNumero(List<Candidato> candidatos) {
        int numeroDigitado = Integer.parseInt(tfVotacao.getText());
        return candidatos.stream().filter(candidato -> candidato.getNumero() == numeroDigitado)
                .findFirst().orElse(null);
    }

    private void procurarCandidato(List<Candidato> candidatos) {
        candidatoEscolhido = validacaoNumero(candidatos);
        String arquivo = candidatoEscolhido != null ? arquivoTela : "votoNulo.fxml";

        try{
            paneCandidato.getChildren().add(FXMLLoader.load(getClass().getResource(
                    "/javafxViews/candidatos/" + arquivo)));
        } catch (Exception e){
            e.printStackTrace();
        }
        lblVoto.setVisible(true);
        paneTutorial.setVisible(true);
        lblNumero.setVisible(true);
    }

    private void limpar(){
        tfVotacao.clear();
        paneCandidato.getChildren().clear();
        lblVoto.setVisible(false);
        paneTutorial.setVisible(false);
        lblNumero.setVisible(false);
    }

    private void configurarTela(String cargo, String nomeArquivo, List<Candidato> candidatos, int limite) {
        lblCargo.setText(cargo);
        arquivoTela = nomeArquivo;
        listaCandidatos = candidatos;
        limiteNum = limite;
    }

    private void atualizarArquivosJSON(){
        DEPUTADO.atualizarArquivo(new Gson().toJson(deputados));
        SENADOR.atualizarArquivo(new Gson().toJson(senadores));
        GOVERNADOR.atualizarArquivo(new Gson().toJson(governadores));
        PRESIDENTE.atualizarArquivo(new Gson().toJson(presidentes));
    }
}


