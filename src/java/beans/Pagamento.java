package beans;

import java.io.Serializable;
import java.text.NumberFormat;


public class Pagamento implements Serializable{
    
    private int id;
    private Aluno aluno;
    private double valor_pago;
    private String mes_ref;
    private String data;

    public Pagamento(int id, Aluno aluno, double valor_pago, String mes_ref, String data) {
        this.id = id;
        this.aluno = aluno;
        this.valor_pago = valor_pago;
        this.mes_ref = mes_ref;
        this.data = data;
    }

    public Pagamento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public double getValor_pago() {
        return valor_pago;
    }
    
    public String getValor_pagoFormated(){
        return NumberFormat.getCurrencyInstance().format(valor_pago);
    }

    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
    }

    public String getMes_ref() {
        return mes_ref;
    }

    public void setMes_ref(String mes_ref) {
        this.mes_ref = mes_ref;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
