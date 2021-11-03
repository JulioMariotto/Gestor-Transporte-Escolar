/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author julio
 */
public class Manutencao {
    
    private Veiculo veiculo;
    private Dispesa dispesa;
    private String problema;
    private String kilometragem;

    public Manutencao(Veiculo veiculo, Dispesa dispesa, String problema, String kilometragem) {
        this.veiculo = veiculo;
        this.dispesa = dispesa;
        this.problema = problema;
        this.kilometragem = kilometragem;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Dispesa getDispesa() {
        return dispesa;
    }

    public void setDispesa(Dispesa dispesa) {
        this.dispesa = dispesa;
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
