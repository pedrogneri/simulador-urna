package org.javafx;

import candidatos.Senador;
import javafx.scene.control.Label;

public class SenadorController {
    public Label lblNome;
    public Label lblPartido;
    public Label lblSuplente1;
    public Label lblSuplente2;

    public void initialize() {
        Senador senadorEscolhido = (Senador) UrnaController.candidatoEscolhido;
        lblNome.setText(senadorEscolhido.getNome());
        lblPartido.setText(senadorEscolhido.getPartido());
        lblSuplente1.setText(senadorEscolhido.getNomeSuplente1());
        lblSuplente2.setText(senadorEscolhido.getNomeSuplente2());
    }
}
