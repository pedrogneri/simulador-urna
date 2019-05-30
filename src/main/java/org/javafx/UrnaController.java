package org.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UrnaController {
    public TextField tfVotacao;
    public Button btnConfirmar;
    public Button btnBranco;
    public Button btnCorrige;

    @FXML
    protected void digitarNumero(ActionEvent event) {
        Button btn = (Button) event.getSource();
        tfVotacao.setText(tfVotacao.getText().concat(btn.getText()));
    }

    @FXML
    protected void corrigir(ActionEvent event) {
        tfVotacao.clear();
    }
}
