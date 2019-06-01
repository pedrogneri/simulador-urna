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
    private List<Candidato> candidatosAtuais;
    private int limite;
    private String telaAtual;

    public Button btnConfirmar;
    public Button btnBranco;
    public Button btnCorrige;
    public TextField tfVotacao;
    public Pane paneCandidato;
    public Label lblCargo;
    public Pane telaFim;

    @FXML
    public void initialize() throws Exception {
        lblCargo.setText("Deputado Estadual");
        candidatosAtuais = deputados; limite = 5; telaAtual = "depEstadual.fxml";
    }

    @FXML
    protected void digitarNumero(ActionEvent event){
        Button btn = (Button) event.getSource();
        String num = btn.getText();

        alterarTexto(num, candidatosAtuais, limite);
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
    protected void confirmar() {
        switch (lblCargo.getText()){
            case "Deputado Estadual":
                candidatosAtuais = senadores; limite = 3; telaAtual = "senador.fxml";
                lblCargo.setText("Senador");
                break;
            case "Senador":
                candidatosAtuais = governadores; limite = 2; telaAtual = "governador.fxml";
                lblCargo.setText("Governador");
                break;
            case "Governador":
                candidatosAtuais = presidentes; limite = 2; telaAtual = "presidente.fxml";
                lblCargo.setText("Presidente");
                break;
            case "Presidente":
                telaFim.setVisible(true);
                lblCargo.setText("FIM");
                break;
            case "FIM":
                telaFim.setVisible(false);
                lblCargo.setText("Deputado Estadual");
                candidatosAtuais = deputados; limite = 5; telaAtual = "depEstadual.fxml";
                break;
        }
        limpar();
    }

    private void validacaoNumero(List<Candidato> candidatos) {
        int numeroDigitado = Integer.parseInt(tfVotacao.getText());
        candidatoEscolhido = candidatos.stream().filter(candidato -> candidato.getNumero() == numeroDigitado)
                .findFirst().orElse(null);
    }

    private void procurarCandidato(List<Candidato> candidatos) {
        validacaoNumero(candidatos);
        String arquivo =  candidatoEscolhido != null ? telaAtual : "votoNulo.fxml";

        try{
            paneCandidato.getChildren().add(FXMLLoader.load(getClass().getResource(
                    "/org.javafx/candidatos/" + arquivo)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void limpar(){
        tfVotacao.clear();
        paneCandidato.getChildren().clear();
    }
}
