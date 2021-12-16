package beans;

import java.io.Serializable;


public class Contato implements Serializable{
     private int id;
     private String nome;
     private String telefone;
     private String parentesco;

    public Contato() {
    }

    public Contato(int id, String nome, String telefone, String parentesco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.parentesco = parentesco;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
     
     
}
