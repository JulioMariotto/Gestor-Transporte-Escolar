/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import bd.ConnectionFactory;
import beans.Abastecimento;
import beans.Dispesa;
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


/**
 *  
 *  private final String selectRodagemDia = "SELECT * FROM rodagem WHERE data = ?";
    private final String selectRodagemMes = "SELECT * FROM rodagem WHERE data = BETWEEN ? AND ?";
    private final String selectTotal = "SELECT SUM(kilometragem_final - kilometragem inicial) FROM rodagem";
    private final String selectRodagensFiltoDataMotorista = "SELECT * FROM rodagem WHERE id_motorista = ? AND DATA BETWEEN ? AND ? ORDER BY data";
    private final String selectTotalMes = "SELECT SUM(kilometragem_final - kilometragem inicial) FROM rodagem WHERE data BETWEEN ? AND ?";
    private final String selectTotalMesMotorista = "SELECT SUM(kilometragem_final - kilometragem inicial) FROM rodagem WHERE id_motorista = ? AND data BETWEEN ? AND ?";
* @author julio
 */


public class AbastecimentoDAO {
    
    private final String selectAbastecimentosPorVeiculo = "SELECT * FROM abastecimento INNER JOIN dispesa on id_dispesa_abastecimento = id_dispesa and id_veiculo_abastecimento = ? ORDER BY data_dispesa DESC";
    private final String selectTodosAbastecimentos = "SELECT * FROM abastecimento INNER JOIN dispesa on id_dispesa_abastecimento = id_dispesa ORDER BY data_dispesa DESC";
    private final String selectAbastecimento = "SELECT * FROM abastecimento WHERE id_dispesa_abastecimento = ?";
    private final String selectTotalAbastecidoPeriodo = "SELECT SUM(litros_abastecimento) FROM abastecimento INNER JOIN dispesa on id_dispesa_abastecimento = id_dispesa AND data_dispesa BETWEEN ? AND ?";
    private final String selectTotalAbastecidoVeiculoPeriodo = "SELECT SUM(litros_abastecimento) FROM abastecimento INNER JOIN dispesa on id_dispesa_abastecimento = id_dispesa AND id_veiculo_abastecimento = ? AND data_dispesa BETWEEN ? AND ?";
    private final String selectValorTotalAbastecidoPeriodo = "SELECT SUM(valor_dispesa) FROM abastecimento INNER JOIN dispesa on id_dispesa_abastecimento = id_dispesa AND data_dispesa BETWEEN ? AND ?";
    private final String selectTotalRodadoVeiculoPeriodo = "SELECT MAX(km_abastecimento) - MIN(km_abastecimento) FROM abastecimento INNER JOIN dispesa on id_dispesa_abastecimento = id_dispesa AND id_veiculo_abastecimento = ? AND data_dispesa BETWEEN ? AND ?";
    private final String insertAbastecimento = "INSERT INTO abastecimento (id_dispesa_abastecimento, id_veiculo_abastecimento, km_abastecimento, litros_abastecimento, posto_abastecimento) VALUES (?, ?, ?, ?, ?)";
    private final String deleteAbastecimento = "DELETE FROM abastecimento WHERE id_dispesa_abastecimento = ?";
    
    
    
    public List<Abastecimento> listaAbastecimentosVeiculo(Veiculo v) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Abastecimento> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAbastecimentosPorVeiculo);
            stmt.setInt(1, v.getId());
            rs = stmt.executeQuery();
            DispesaDAO dao = new DispesaDAO();
            while(rs.next()){
                Abastecimento aba = new Abastecimento(v, dao.listaDispesa(rs.getInt("id_dispesa_abastecimento")), rs.getInt("km_abastecimento"), rs.getDouble("litros_abastecimento"), rs.getString("posto_abastecimento"));
                lista.add(aba);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os abastecimentos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public List<Abastecimento> listaAbastecimentos() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Abastecimento> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTodosAbastecimentos);
            rs = stmt.executeQuery();
            DispesaDAO dDAO = new DispesaDAO();
            VeiculoDAO vDAO = new VeiculoDAO();
            while(rs.next()){
                Abastecimento aba = new Abastecimento(vDAO.listarVeiculo(rs.getInt("id_veiculo_abastecimento")), dDAO.listaDispesa(rs.getInt("id_dispesa_abastecimento")), rs.getInt("km_abastecimento"), rs.getDouble("litros_abastecimento"), rs.getString("posto_abastecimento"));
                lista.add(aba);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os abastecimentos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Abastecimento listaAbastecimento(int id_dispesa) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAbastecimento);
            stmt.setInt(1, id_dispesa);
            rs = stmt.executeQuery();
            Abastecimento aba = null;
            while(rs.next()){
                aba = new Abastecimento(VeiculoFacade.buscar(rs.getInt("id_veiculo_abastecimento")), DispesaFacade.buscar(id_dispesa), rs.getInt("km_abastecimento"), rs.getDouble("litros_abastecimento"), rs.getString("posto_abastecimento"));
            }
            return aba;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os abastecimentos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Abastecimento registra(Abastecimento a){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertAbastecimento);
            stmt.setInt(1, a.getDispesa().getId());
            stmt.setInt(2, a.getVeiculo().getId());
            stmt.setInt(3, a.getKilometragem());
            stmt.setDouble(4, a.getLitros());
            stmt.setString(5, a.getPosto());
            stmt.executeUpdate();
            return a;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao registrar um abastecimento. "+ex.getMessage());
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
            stmt = con.prepareStatement(deleteAbastecimento);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover um abastecimento. "+ex.getMessage());
        }finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    
    public double getTotalAbastecido(String inicio, String fim) {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalAbastecidoPeriodo);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o total abastecido. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double getTotalAbastecidoVeiculo(String inicio, String fim, Veiculo v) {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalAbastecidoVeiculoPeriodo);
            stmt.setInt(1, v.getId());
            stmt.setString(2, inicio);
            stmt.setString(3, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o total abastecido. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public int getTotalRodadoVeiculo(String inicio, String fim, Veiculo v) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalRodadoVeiculoPeriodo);
            stmt.setInt(1, v.getId());
            stmt.setString(2, inicio);
            stmt.setString(3, fim);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o total rodado. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public double valorTotalAbastecidoPeriodo(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectValorTotalAbastecidoPeriodo);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o valor total abastecido. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

}
