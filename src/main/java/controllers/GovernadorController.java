package controllers;

import models.Governador;
import javafx.scene.control.Label;

public class GovernadorController {
    public Label lblNome;
    public Label lblPartido;
    public Label lblVice;

    public void initialize() {
        Governador governadorEscolhido = (Governador) UrnaController.candidatoEscolhido;
        lblNome.setText(governadorEscolhido.getNome());
        lblPartido.setText(governadorEscolhido.getPartido());
        lblVice.setText(governadorEscolhido.getNomeVice());
    }
}
