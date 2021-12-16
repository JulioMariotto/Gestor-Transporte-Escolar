package beans;


public class Manutencao {
    
    private Veiculo veiculo;
    private Despesa despesa;
    private String problema;
    private String kilometragem;

    public Manutencao(Veiculo veiculo, Despesa despesa, String problema, String kilometragem) {
        this.veiculo = veiculo;
        this.despesa = despesa;
        this.problema = problema;
        this.kilometragem = kilometragem;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(String kilometragem) {
        this.kilometragem = kilometragem;
    }
    
    
}
