package org.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.lang.reflect.Field;

public class UrnaController {
    private String tela;

    private Pane paneTela;
    private TextField tfVotacao;

    public Button btnConfirmar;
    public Button btnBranco;
    public Button btnCorrige;
    public HBox hbTela;

    @FXML
    public void initialize() throws Exception{
        tela = "depEstadual.fxml";
        atualizarTela();
    }

    private void atualizarTela() throws Exception{
        paneTela = FXMLLoader.load(getClass().getResource("/org.javafx/" + tela));
        hbTela.getChildren().add(paneTela);

        tfVotacao = (TextField) paneTela.getChildren().get(1);
    }

    @FXML
    protected void digitarNumero(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if(tfVotacao.getLength() < 5) tfVotacao.setText(tfVotacao.getText().concat(btn.getText()));
    }

    @FXML
    protected void corrigir(ActionEvent event) {
        tfVotacao.clear();
    }

    @FXML
    protected void confirmar(ActionEvent event) throws Exception{
        tela = tela.equals("depEstadual.fxml") ? "senador.fxml" :
                tela.equals("senador.fxml") ? "governador.fxml" :
                tela.equals("governador.fxml") ? "presidente.fxml" : "fim.fxml";
        
        hbTela.getChildren().remove(0);
        atualizarTela();
    }
}
