package candidatos;

import javax.swing.*;

public class DeputadoEstadual extends Candidato{
    private String estado;

    public DeputadoEstadual(String nome, String partido, int numero, String estado, int votos, Double percentual) {
        super(nome, partido, numero, votos, percentual);
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public void listar() {
        JOptionPane.showMessageDialog(null,
                "Cargo: Deputado Estadual" +
                        "\nNome: " + getNome() +
                        "\nPartido: " + getPartido() +
                        "\nNumero: " + getNumero() +
                        "\nEstado: " + getEstado(),
                "Deputado Estadual", -1);
    }

}
