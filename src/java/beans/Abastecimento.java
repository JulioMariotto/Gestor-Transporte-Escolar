/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.NumberFormat;

/**
 *
 * @author julio
 */
public class Abastecimento {
    
    private Veiculo veiculo;
    private Dispesa dispesa;
    private int kilometragem;
    private double litros;
    private String posto;
    private double valorLitro;

    public Abastecimento() {
    }

    public Abastecimento(Veiculo veiculo, Dispesa dispesa, int kilometragem, double litros, String posto) {
        this.veiculo = veiculo;
        this.dispesa = dispesa;
        this.kilometragem = kilometragem;
        this.litros = litros;
        this.posto = posto;
        this.valorLitro = dispesa.getValor() / litros;
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
