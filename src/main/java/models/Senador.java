package models;

public class Senador extends Candidato{
    private String nomeSuplente1;
    private String nomeSuplente2;

    public Senador(String nome, String partido, int numero, String nomeSuplente1, String nomeSuplente2, int votos, Double percentual) {
        super(nome, partido, numero, votos, percentual);
        this.nomeSuplente1 = nomeSuplente1;
        this.nomeSuplente2 = nomeSuplente2;
    }

    public String getNomeSuplente1() {
        return nomeSuplente1;
    }
    public String getNomeSuplente2() {
        return nomeSuplente2;
    }
}