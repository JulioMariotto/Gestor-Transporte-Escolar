package beans;

import java.io.Serializable;


public class Motorista implements Serializable{
    
    private int id;
    private String nome;
    private String cnh;
    private String telefone;
    private String endereco;

    public Motorista() {
    }
          
    public Motorista(int id, String nome, String cnh, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cnh = cnh;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}
