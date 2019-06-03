package controllers;

import models.Presidente;
import javafx.scene.control.Label;

public class PresidenteController {
    public Label lblNome;
    public Label lblPartido;
    public Label lblVice;

    public void initialize() {
        Presidente presidenteEscolhido = (Presidente) UrnaController.candidatoEscolhido;
        lblNome.setText(presidenteEscolhido.getNome());
        lblPartido.setText(presidenteEscolhido.getPartido());
        lblVice.setText(presidenteEscolhido.getNomeVice());
    }
}
