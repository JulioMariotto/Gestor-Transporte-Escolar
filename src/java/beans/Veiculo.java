/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author julio
 */
public class Veiculo implements Serializable{
    
    private int id;
    private String numero;
    private String placa;
    private String licenca;
    private int capacidade;
    private String modelo;
    private String cor;
    private Motorista motorista;

    public Veiculo() {
    }

    public Veiculo(int id, String numero, String placa, String licenca, int capacidade, String modelo, String cor, Motorista motorista) {
        this.id = id;
        this.numero = numero;
        this.placa = placa;
        this.licenca = licenca;
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.cor = cor;
        this.motorista = motorista;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
    
    
    
}
