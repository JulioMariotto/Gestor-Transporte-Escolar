package daos;

import bd.ConnectionFactory;
import beans.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PagamentoDAO {
    
    private final String selectPagamentos = "SELECT * FROM pagamento ORDER BY data_pagamento DESC";
    private final String selectPagamentosRefMes = "SELECT * FROM pagamento WHERE mes_referencia = ? ORDER BY data_pagamento DESC";
    private final String selectPagamentosRefAno = "SELECT * FROM pagamento WHERE mes_referencia BETWEEN ? AND ? ORDER BY data_pagamento DESC";
    private final String selectPagamentosData = "SELECT * FROM pagamento WHERE data_pagamento BETWEEN ? AND ? ORDER BY data_pagamento DESC";
    private final String selectPagamentosLimit50 = "SELECT * FROM pagamento ORDER BY data_pagamento DESC limit 50";
    private final String selectPagamentosAluno = "SELECT * FROM pagamento WHERE id_aluno = ? ORDER BY data_pagamento DESC";
    private final String selectFaturamentoMes = "SELECT SUM(valor_pago) FROM pagamento WHERE data_pagamento BETWEEN ? AND ?";
    private final String selectFaturamentoTotal = "SELECT SUM(valor_pago) FROM pagamento";
    private final String selectRecebidoMes = "SELECT SUM(valor_pago) FROM pagamento WHERE mes_referencia = ?";
    private final String selectRecebidoAno = "SELECT SUM(valor_pago) FROM pagamento WHERE mes_referencia LIKE ?";
    private final String insertPagamentoAluno = "INSERT INTO pagamento (id_aluno, valor_pago, mes_referencia, data_pagamento) VALUES (?, ?, ?, ?)";
    private final String deletePagamento = "DELETE FROM pagamento WHERE id_pagamento = ?";
    private final String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    
    public List<Pagamento> listaPagamentos() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectPagamentos);
            rs = stmt.executeQuery();
            AlunoDAO dao = new AlunoDAO();
            String data;
            int mes;
            String mes_ref;
            
            while(rs.next()){
                data = rs.getString("mes_referencia");
                mes = Integer.valueOf(data.substring(5, 7));
                mes_ref = meses[mes-1] + " de " +  data.substring(0, 4);
                Pagamento pag = new Pagamento(rs.getInt("id_pagamento"), dao.listarAluno(rs.getInt("id_aluno")), rs.getDouble("valor_pago"), mes_ref, rs.getString("data_pagamento"));
                lista.add(pag);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar todos os Pagamentos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public List<Pagamento> listaPagamentos(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectPagamentosData);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            AlunoDAO dao = new AlunoDAO();
            String data;
            int mes;
            String mes_ref;
            
            while(rs.next()){
                data = rs.getString("mes_referencia");
                mes = Integer.valueOf(data.substring(5, 7));
                mes_ref = meses[mes-1] + " de " +  data.substring(0, 4);
                Pagamento pag = new Pagamento(rs.getInt("id_pagamento"), dao.listarAluno(rs.getInt("id_aluno")), rs.getDouble("valor_pago"), mes_ref, rs.getString("data_pagamento"));
                lista.add(pag);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os Pagamentos entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public List<Pagamento> lista50Pagamentos() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectPagamentosLimit50);
            rs = stmt.executeQuery();
            AlunoDAO dao = new AlunoDAO();
            String data;
            int mes;
            String mes_ref;
            
            while(rs.next()){
                data = rs.getString("mes_referencia");
                mes = Integer.valueOf(data.substring(5, 7));
                mes_ref = meses[mes-1] + " de " +  data.substring(0, 4);
                Pagamento pag = new Pagamento(rs.getInt("id_pagamento"), dao.listarAluno(rs.getInt("id_aluno")), rs.getDouble("valor_pago"), mes_ref, rs.getString("data_pagamento"));
                lista.add(pag);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os Pagamentos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public List<Pagamento> listaPagamentosAlunos(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectPagamentosAluno);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            AlunoDAO dao = new AlunoDAO();
            String data;
            int mes;
            String mes_ref;
            
            while(rs.next()){
                data = rs.getString("mes_referencia");
                mes = Integer.valueOf(data.substring(5, 7));
                mes_ref = meses[mes-1] + " de " +  data.substring(0, 4);
                Pagamento pag = new Pagamento(rs.getInt("id_pagamento"), dao.listarAluno(rs.getInt("id_aluno")), rs.getDouble("valor_pago"), mes_ref, rs.getString("data_pagamento"));
                lista.add(pag);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os Pagamentos de um Aluno. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public List<Pagamento> listaPagamentosMesRef() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> lista = new ArrayList();
        try{
            DateFormat fmt = new SimpleDateFormat("yyyy-MM"); 
            String data = fmt.format(new Date());
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectPagamentosRefMes);
            stmt.setString(1, data + "-01");
            //stmt.setString(1, "Abril/2019");
            rs = stmt.executeQuery();
            AlunoDAO dao = new AlunoDAO();
            int mes;
            String mes_ref;
            
            while(rs.next()){
                data = rs.getString("mes_referencia");
                mes = Integer.valueOf(data.substring(5, 7));
                mes_ref = meses[mes-1] + " de " +  data.substring(0, 4);
                Pagamento pag = new Pagamento(rs.getInt("id_pagamento"), dao.listarAluno(rs.getInt("id_aluno")), rs.getDouble("valor_pago"), mes_ref, rs.getString("data_pagamento"));
                lista.add(pag);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os Pagamentos do mês. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
      public List<Pagamento> listaPagamentosAnoRef() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> lista = new ArrayList();
        try{
            DateFormat fmt = new SimpleDateFormat("yyyy"); 
            String data = fmt.format(new Date());
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectPagamentosRefAno);
            stmt.setString(1, data + "-01-01");
            stmt.setString(2, data + "-12-31");
            rs = stmt.executeQuery();
            AlunoDAO dao = new AlunoDAO();
            int mes;
            String mes_ref;
            
            while(rs.next()){
                data = rs.getString("mes_referencia");
                mes = Integer.valueOf(data.substring(5, 7));
                mes_ref = meses[mes-1] + " de " +  data.substring(0, 4);
                Pagamento pag = new Pagamento(rs.getInt("id_pagamento"), dao.listarAluno(rs.getInt("id_aluno")), rs.getDouble("valor_pago"), mes_ref, rs.getString("data_pagamento"));
                lista.add(pag);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os pagamentos deste ano. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Pagamento registra(Pagamento p){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertPagamentoAluno, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, p.getAluno().getId());
            stmt.setDouble(2, p.getValor_pago());
            stmt.setString(3, p.getMes_ref());
            stmt.setString(4, p.getData());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return new Pagamento(id, p.getAluno(), p.getValor_pago(), p.getMes_ref(), p.getData());
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao registrar um Pagamento. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void remove(int id){
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deletePagamento);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover um Pagamento. "+ex.getMessage());
        }finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalRecebido(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectFaturamentoMes);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total recebido entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public double totalRecebido() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectFaturamentoTotal);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total recebido. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public double recebidoMes() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            DateFormat fmt = new SimpleDateFormat("yyyy-MM"); 
            String data = fmt.format(new Date());
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectRecebidoMes);
            stmt.setString(1, data + "-01");
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total recebido neste mês. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double recebidoAno() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
            String data = fmt.format(new Date());
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectRecebidoAno);
            stmt.setString(1, "%/" + data.substring(0, 4));
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total recebido no ano. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
}
