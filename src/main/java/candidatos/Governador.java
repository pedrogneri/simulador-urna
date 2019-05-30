package candidatos;

import javax.swing.*;

public class Governador extends Candidato{
    private String nomeVice;
    private String partidoVice;
    private String estado;

    public Governador(String nome, String partido, int numero, String nomeVice, String partidoVice, String estado, int votos, Double percentual) {
        super(nome, partido, numero, votos, percentual);
        this.nomeVice = nomeVice;
        this.partidoVice = partidoVice;
        this.estado = estado;
    }

    public String getNomeVice() {
        return nomeVice;
    }
    public String getPartidoVice() {
        return partidoVice;
    }
    public String getEstado() {
        return estado;
    }

    @Override
    public void listar() {
        JOptionPane.showMessageDialog(null,
                "Cargo: Governador" +
                        "\nNome: " + getNome() +
                        "\nPartido: " + getPartido() +
                        "\nVice-governador: " + getNomeVice() +
                        "\nPartido do vice: " + getPartidoVice() +
                        "\nNumero: " + getNumero() +
                        "\nEstado: " + getEstado(),
                "Governador", -1);
    }
}

