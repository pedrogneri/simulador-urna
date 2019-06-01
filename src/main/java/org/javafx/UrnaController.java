package org.javafx;

import candidatos.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.Arrays;
import java.util.List;

public class UrnaController {
    // Deputados
    private static DeputadoEstadual depEst1 = new DeputadoEstadual("Garçom", "PProf", 93002, "SP", 0,0.0);
    private static DeputadoEstadual depEst2 = new DeputadoEstadual("Bicho-Papão", "PFolc", 95002, "SP", 0,0.0);
    private static DeputadoEstadual depEst3 = new DeputadoEstadual("Jazz", "PMus", 92002, "SP", 0,0.0);
    // Senadores
    private static Senador sen1 = new Senador("Natação", "PEsp", 911, "Esgrima", "Rúgbi", 0,0.0);
    private static Senador sen2 = new Senador("Samba", "PMus", 921, "Tango", "Música Disco", 0,0.0);
    private static Senador sen3 = new Senador("Enfermeira", "PProf", 931, "Aeromoça", "Detetive", 0,0.0);
    // Governadores
    private static Governador gov1 = new Governador("Médica", "PProf", 93, "Natal", "PFest", "SP", 0,0.0);
    private static Governador gov2 = new Governador("Vôlei", "PEsp", 91, "Tênis", "PEsp", "SP", 0,0.0);
    private static Governador gov3 = new Governador("Boitatá", "PFolc", 95, "Pagode", "PMus", "SP", 0,0.0);
    // Presidentes
    private static Presidente pre1 = new Presidente("Futebol", "PEsp", 91, "Carnaval", "PFest", 0,0.0);
    private static Presidente pre2 = new Presidente("Curupira", "PFolc", 95, "Pagode", "PMus", 0,0.0);
    private static Presidente pre3 = new Presidente("Professora", "PProf", 93, "Vitória-Régia", "PFolc", 0,0.0);

    // Conjunto de candidatos
    private static List<Candidato> deputados = Arrays.asList(depEst1, depEst2, depEst3);
    private static List<Candidato> senadores = Arrays.asList(sen1, sen2, sen3);
    private static List<Candidato> governadores = Arrays.asList(gov1, gov2, gov3);
    private static List<Candidato> presidentes = Arrays.asList(pre1, pre2, pre3);

    private Candidato candidatoEscolhido;
    private List<Candidato> listaCandidatos;
    private int limiteNum;
    private String arquivoTela;

    public Button btnConfirmar;
    public Button btnBranco;
    public Button btnCorrige;
    public TextField tfVotacao;
    public Pane paneCandidato;
    public Label lblCargo;
    public Pane telaFim;
    public Label lblVoto;
    public AnchorPane paneTutorial;
    public Label lblNumero;

    @FXML
    public void initialize() {
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
    protected void votarBranco() throws Exception{
        limpar();
        paneCandidato.getChildren().add(FXMLLoader.load(getClass().getResource(
                "/org.javafx/candidatos/votoBranco.fxml")));
        lblVoto.setVisible(true);
        paneTutorial.setVisible(true);
    }

    @FXML
    protected void confirmar() {
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
                    "/org.javafx/candidatos/" + arquivo)));
        }catch (Exception e){
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
}
