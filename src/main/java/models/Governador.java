package models;

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
}

