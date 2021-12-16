package daos;

import bd.ConnectionFactory;
import beans.Despesa;
import beans.Manutencao;
import beans.Veiculo;
import facade.DespesaFacade;
import facade.VeiculoFacade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ManutencaoDAO {
    
    private final String selectManutencao = "SELECT * FROM manutencao WHERE id_despesa_manutencao = ?";
    private final String selectManutencoes = "SELECT * FROM manutencao INNER JOIN despesa ON id_despesa_manutencao = id_despesa ORDER BY data_despesa DESC";
    private final String selectManutencoesVeiculo = "SELECT * FROM manutencao INNER JOIN despesa ON id_despesa_manutencao = id_despesa AND id_veiculo_manutencao = ?  ORDER BY data_despesa DESC";
    private final String selectTotalManutencoes = "SELECT SUM(valor_despesa) FROM manutencao INNER JOIN despesa ON id_despesa_manutencao = id_despesa AND data_despesa BETWEEN ? AND ? ORDER BY data_despesa DESC";
    private final String selectManutencoesFiltoData = "SELECT * FROM manutencao INNER JOIN despesa ON id_despesa_manutencao = id_despesa AND data_despesa BETWEEN ? AND ? ORDER BY data_despesa DESC";
    private final String insertManutencao = "INSERT INTO manutencao (id_despesa_manutencao, id_veiculo_manutencao, problema_manutencao, km_manutencao) VALUES (?, ?, ?, ?)";
    private final String deleteManutencao = "DELETE FROM manutencao WHERE id_despesa_manutencao = ?";
    
    public Manutencao listarManutencao(int id_despesa){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectManutencao);
            stmt.setInt(1, id_despesa);
            rs = stmt.executeQuery();
            Manutencao man = null;
            while(rs.next()){
                man = new Manutencao(VeiculoFacade.buscar(rs.getInt("id_despesa_manutencao")), DespesaFacade.buscar(id_despesa), rs.getString("problema_manutencao"), rs.getString("km_manutencao"));
            }
            return man;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar uma Manutencao. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public List<Manutencao> listarManutencoes(Veiculo v) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Manutencao> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectManutencoesVeiculo);
            stmt.setInt(1, v.getId());
            rs = stmt.executeQuery();
            DespesaDAO dao = new DespesaDAO();
            while(rs.next()){
                Manutencao man = new Manutencao(v, dao.listaDespesa(rs.getInt("id_despesa_manutencao")), rs.getString("problema_manutencao"), rs.getString("km_manutencao"));
                lista.add(man);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as Manutencoes de um Veículo. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public List<Manutencao> listarManutencoes(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Manutencao> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectManutencoesFiltoData);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            VeiculoDAO dao = new VeiculoDAO();
            while(rs.next()){
                Despesa dis = new Despesa(rs.getInt("id_despesa"), rs.getDouble("valor_despesa"), rs.getString("data_despesa"), rs.getString("descricao_despesa"));
                Manutencao man = new Manutencao(dao.listarVeiculo(rs.getInt("id_veiculo_manutencao")), dis, rs.getString("problema_manutencao"), rs.getString("km_manutencao"));
                lista.add(man);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar Manutencoes entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public List<Manutencao> listarManutencoes() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Manutencao> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectManutencoes);
            rs = stmt.executeQuery();
            VeiculoDAO dao = new VeiculoDAO();
            while(rs.next()){
                Despesa dis = new Despesa(rs.getInt("id_despesa"), rs.getDouble("valor_despesa"), rs.getString("data_despesa"), rs.getString("descricao_despesa"));
                Manutencao man = new Manutencao(dao.listarVeiculo(rs.getInt("id_veiculo_manutencao")), dis, rs.getString("problema_manutencao"), rs.getString("km_manutencao"));
                lista.add(man);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as Manutencoes. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }    
        
    
    public Manutencao registra(Manutencao m){
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertManutencao);
            stmt.setInt(1, m.getDespesa().getId());
            stmt.setInt(2, m.getVeiculo().getId());
            stmt.setString(3, m.getProblema());
            stmt.setString(4, m.getKilometragem());
            stmt.executeUpdate();
            return listarManutencao(m.getDespesa().getId());
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao registrar uma Manutencao. "+ex.getMessage());
        }finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void remove(int idDespesa){
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteManutencao);
            stmt.setInt(1, idDespesa);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover uma Manutencao. "+ex.getMessage());
        }finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public double totalGastoManutencoes(String inicio, String fim) {
           Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalManutencoes);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total gasto em Manutenções. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
        
}
