package models;

public class DeputadoEstadual extends Candidato{
    private String estado;

    public DeputadoEstadual(String nome, String partido, int numero, String estado, int votos, Double percentual) {
        super(nome, partido, numero, votos, percentual);
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
