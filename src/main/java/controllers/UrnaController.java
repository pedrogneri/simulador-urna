package controllers;

import models.*;
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

import java.io.IOException;
import java.util.List;

import static apllication.ArquivosJson.*;

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

    private List<Candidato> deputados;
    private List<Candidato> senadores;
    private List<Candidato> governadores;
    private List<Candidato> presidentes;

    static Candidato candidatoEscolhido;
    private Gson gson;
    private List<Candidato> listaCandidatos;
    private int limiteNum;
    private String arquivoTela;

    @FXML
    public void initialize() {
        gson = new Gson();
        deputados = gson.fromJson(DEPUTADO.lerArquivo(), new TypeToken<List<DeputadoEstadual>>(){}.getType());
        senadores = gson.fromJson(SENADOR.lerArquivo(), new TypeToken<List<Senador>>(){}.getType());
        governadores = gson.fromJson(GOVERNADOR.lerArquivo(), new TypeToken<List<Governador>>(){}.getType());
        presidentes = gson.fromJson(PRESIDENTE.lerArquivo(), new TypeToken<List<Presidente>>(){}.getType());

        somFim = new AudioClip(getClass().getResource("/sons/som_fim.mp3").toExternalForm());
        somVoto = new AudioClip(getClass().getResource("/sons/som_voto.mp3").toExternalForm());

        alterarCargo();
    }

    @FXML
    protected void digitarNumero(ActionEvent event){
        Button btn = (Button) event.getSource();
        String num = tfVotacao.getText().concat(btn.getText());

        if(tfVotacao.getLength() < limiteNum) tfVotacao.setText(num);
        if(tfVotacao.getLength() == limiteNum) procurarCandidato();
    }

    @FXML
    protected void corrigir() {
       limpar();
    }

    @FXML
    protected void votarBranco() {
        limpar();
        carregarView("votoBranco.fxml");
        lblVoto.setVisible(true);
        paneTutorial.setVisible(true);
    }

    @FXML
    protected void confirmar() {
        boolean candidatoFoiEscolhido = paneCandidato.getChildren().size() != 0;
        if(candidatoFoiEscolhido){
            if(candidatoEscolhido != null)
                candidatoEscolhido.votar();
            somVoto.play();
            alterarCargo();
        } else if (telaFim.isVisible()){
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
                lblCargo.setText("");
                break;
            default:
                telaFim.setVisible(false);
                configurarTela("Deputado Estadual", "depEstadual.fxml", deputados, 5);
                break;
        }
        limpar();
    }

    private void carregarView(String nomeArquivo){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/monitor/" + nomeArquivo));
            paneCandidato.getChildren().add(pane);
        } catch (IOException e){ e.printStackTrace(); }
    }

    private Candidato validarNumero() {
        int numeroDigitado = Integer.parseInt(tfVotacao.getText());
        return listaCandidatos.stream().filter(candidato -> candidato.getNumero() == numeroDigitado)
                .findFirst().orElse(null);
    }

    private void procurarCandidato() {
        candidatoEscolhido = validarNumero();
        try{
            carregarView(candidatoEscolhido != null ? arquivoTela : "votoNulo.fxml");
        } catch (Exception e){ e.printStackTrace(); }

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

    private void atualizarArquivosJSON() {
        DEPUTADO.atualizarArquivo(gson.toJson(deputados));
        SENADOR.atualizarArquivo(gson.toJson(senadores));
        GOVERNADOR.atualizarArquivo(gson.toJson(governadores));
        PRESIDENTE.atualizarArquivo(gson.toJson(presidentes));
    }
}


