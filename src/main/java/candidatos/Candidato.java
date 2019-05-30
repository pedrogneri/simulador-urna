package candidatos;

abstract class Candidato {
    private String nome;
    private String partido;
    private int numero;
    private int votos;
    private Double percentual;

    Candidato(String nome, String partido, int numero, int votos, Double percentual) {
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
        this.votos = votos;
        this.percentual = percentual;
    }

    public String getNome() {
        return nome;
    }
    public String getPartido() {
        return partido;
    }
    public int getNumero() {
        return numero;
    }
    public int getVotos() {
        return votos;
    }
    public void setVotos(int votos) {
        this.votos = votos;
    }
    public Double getPercentual() {
        return percentual;
    }
    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    void votar(){
        setVotos(getVotos()+1);
        listar();
    }

    void calcularPercentual(int votosTotais){
        setPercentual(getVotos() * 100 / (double) votosTotais);
    }

    abstract void listar();
}

