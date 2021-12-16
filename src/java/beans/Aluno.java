
package beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;


public class Aluno implements Serializable{
    
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private String dataNascimento;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private List<Contato> contatos;
    private Veiculo veiculo;
    private Escola escola;
    private String periodo;
    private String turma;
    private String horarioCasaIda;
    private String horarioEscolaIda;
    private String horarioEscolaVolta;
    private String horarioCasaVolta;
    private Float mensalidade;
    private int vencimento;
    private String dataInicio;
    private String dataFim;
    private int status;

    public Aluno() {
    }

    public Aluno(int id, String nome, String telefone, String endereco, String dataNascimento, String nomeResponsavel, String cpfResponsavel, List<Contato> contatos, Veiculo veiculo, Escola escola, String periodo, String turma, String horarioCasaIda, String horarioEscolaIda, String horarioEscolaVolta, String horarioCasaVolta, Float mensalidade, int vencimento, String dataInicio, String dataFim, int status) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
        this.contatos = contatos;
        this.veiculo = veiculo;
        this.escola = escola;
        this.periodo = periodo;
        this.turma = turma;
        this.horarioCasaIda = horarioCasaIda;
        this.horarioEscolaIda = horarioEscolaIda;
        this.horarioEscolaVolta = horarioEscolaVolta;
        this.horarioCasaVolta = horarioCasaVolta;
        this.mensalidade = mensalidade;
        this.vencimento = vencimento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getHorarioCasaIda() {
        return horarioCasaIda;
    }

    public void setHorarioCasaIda(String horarioCasaIda) {
        this.horarioCasaIda = horarioCasaIda;
    }

    public String getHorarioEscolaIda() {
        return horarioEscolaIda;
    }

    public void setHorarioEscolaIda(String horarioEscolaIda) {
        this.horarioEscolaIda = horarioEscolaIda;
    }

    public String getHorarioEscolaVolta() {
        return horarioEscolaVolta;
    }

    public void setHorarioEscolaVolta(String horarioEscolaVolta) {
        this.horarioEscolaVolta = horarioEscolaVolta;
    }

    public String getHorarioCasaVolta() {
        return horarioCasaVolta;
    }

    public void setHorarioCasaVolta(String horarioCasaVolta) {
        this.horarioCasaVolta = horarioCasaVolta;
    }

    public Float getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Float mensalidade) {
        this.mensalidade = mensalidade;
    }

    public int getVencimento() {
        return vencimento;
    }

    public void setVencimento(int vencimento) {
        this.vencimento = vencimento;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    public String getMensalidadeFormated() {
        return NumberFormat.getCurrencyInstance().format(mensalidade);
    }
    
}
