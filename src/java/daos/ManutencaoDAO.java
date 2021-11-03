/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import bd.ConnectionFactory;
import beans.Dispesa;
import beans.Manutencao;
import beans.Veiculo;
import facade.DispesaFacade;
import facade.VeiculoFacade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class ManutencaoDAO {
    
    private final String selectManutencao = "SELECT * FROM manutencao WHERE id_dispesa_manutencao = ?";
    private final String selectManutencoes = "SELECT * FROM manutencao INNER JOIN dispesa ON id_dispesa_manutencao = id_dispesa ORDER BY data_dispesa DESC";
    private final String selectManutencoesVeiculo = "SELECT * FROM manutencao INNER JOIN dispesa ON id_dispesa_manutencao = id_dispesa AND id_veiculo_manutencao = ?  ORDER BY data_dispesa DESC";
    private final String selectManutencoesFiltoData = "SELECT * FROM manutencao INNER JOIN dispesa ON id_dispesa_manutencao = id_dispesa AND data_dispesa BETWEEN ? AND ? ORDER BY data_dispesa DESC";
    private final String insertManutencao = "INSERT INTO manutencao (id_dispesa_manutencao, id_veiculo_manutencao, problema_manutencao, km_manutencao) VALUES (?, ?, ?, ?)";
    private final String deleteManutencao = "DELETE FROM manutencao WHERE id_dispesa_manutencao = ?";
    
    public Manutencao listarManutencao(int id_dispesa){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectManutencao);
            stmt.setInt(1, id_dispesa);
            rs = stmt.executeQuery();
            Manutencao man = null;
            while(rs.next()){
                man = new Manutencao(VeiculoFacade.buscar(rs.getInt("id_dispesa_manutencao")), DispesaFacade.buscar(id_dispesa), rs.getString("problema_manutencao"), rs.getString("km_manutencao"));
            }
            return man;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar a manutencao. "+ex.getMessage());
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
            DispesaDAO dao = new DispesaDAO();
            while(rs.next()){
                Manutencao man = new Manutencao(v, dao.listaDispesa(rs.getInt("id_dispesa_manutencao")), rs.getString("problema_manutencao"), rs.getString("km_manutencao"));
                lista.add(man);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as manutencoes deste veiculo. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public List<Manutencao> listarManutencoes(String data_inicio, String data_fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Manutencao> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectManutencoesFiltoData);
            stmt.setString(1, data_inicio);
            stmt.setString(2, data_fim);
            rs = stmt.executeQuery();
            VeiculoDAO dao = new VeiculoDAO();
            while(rs.next()){
                Dispesa dis = new Dispesa(rs.getInt("id_dispesa"), rs.getDouble("valor_dispesa"), rs.getString("data_dispesa"), rs.getString("descricao_dispesa"));
                Manutencao man = new Manutencao(dao.listarVeiculo(rs.getInt("id_veiculo_manutencao")), dis, rs.getString("problema_manutencao"), rs.getString("km_manutencao"));
                lista.add(man);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as manutencoes. "+ex.getMessage());
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
                Dispesa dis = new Dispesa(rs.getInt("id_dispesa"), rs.getDouble("valor_dispesa"), rs.getString("data_dispesa"), rs.getString("descricao_dispesa"));
                Manutencao man = new Manutencao(dao.listarVeiculo(rs.getInt("id_veiculo_manutencao")), dis, rs.getString("problema_manutencao"), rs.getString("km_manutencao"));
                lista.add(man);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as manutencoes. "+ex.getMessage());
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
            stmt.setInt(1, m.getDispesa().getId());
            stmt.setInt(2, m.getVeiculo().getId());
            stmt.setString(3, m.getProblema());
            stmt.setString(4, m.getKilometragem());
            stmt.executeUpdate();
            return listarManutencao(m.getDispesa().getId());
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao registrar uma manutencao. "+ex.getMessage());
        }finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void remove(int idDispesa){
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteManutencao);
            stmt.setInt(1, idDispesa);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover uma manutencao. "+ex.getMessage());
        }finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
        
}
