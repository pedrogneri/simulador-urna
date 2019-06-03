package models;

import javax.swing.*;

public class Presidente extends Candidato {
    private String nomeVice;
    private String partidoVice;

    public Presidente(String nome, String partido, int numero, String nomeVice, String partidoVice, int votos, Double percentual) {
        super(nome, partido, numero, votos, percentual);
        this.nomeVice = nomeVice;
        this.partidoVice = partidoVice;
    }

    public String getNomeVice() {
        return nomeVice;
    }
    public String getPartidoVice() {
        return partidoVice;
    }
}