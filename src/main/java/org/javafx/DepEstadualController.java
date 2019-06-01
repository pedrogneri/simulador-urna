package org.javafx;

import candidatos.DeputadoEstadual;
import javafx.scene.control.Label;

public class DepEstadualController {
    public Label lblNome;
    public Label lblPartido;

    public void initialize() {
        DeputadoEstadual deputadoEscolhido = (DeputadoEstadual) UrnaController.candidatoEscolhido;
        lblNome.setText(deputadoEscolhido.getNome());
        lblPartido.setText(deputadoEscolhido.getPartido());
    }
}
