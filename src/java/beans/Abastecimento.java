
package beans;


public class Abastecimento {
    
    private Veiculo veiculo;
    private Despesa despesa;
    private int kilometragem;
    private double litros;
    private String posto;
    private double valorLitro;

    public Abastecimento() {
    }

    public Abastecimento(Veiculo veiculo, Despesa despesa, int kilometragem, double litros, String posto) {
        this.veiculo = veiculo;
        this.despesa = despesa;
        this.kilometragem = kilometragem;
        this.litros = litros;
        this.posto = posto;
        this.valorLitro = despesa.getValor() / litros;
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

    public int getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(int kilometragem) {
        this.kilometragem = kilometragem;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public double getValorLitro() {
        return valorLitro;
    }

    public void setValorLitro(double valorLitro) {
        this.valorLitro = valorLitro;
    }

    
    

    
}
