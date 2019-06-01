package org.javafx;

import candidatos.Candidato;
import candidatos.DeputadoEstadual;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class DepEstadualController {
    private List<Candidato> deputados;

    @FXML
    private Label lblNome;
    @FXML
    private Label lblPartido;

    @FXML
    public void initialize() throws Exception {

    }
}
