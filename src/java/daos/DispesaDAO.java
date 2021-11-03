/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

/**
 *
 * @author julio
 */

import bd.ConnectionFactory;
import beans.Dispesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DispesaDAO {
    
    private final String selectDispesas = "SELECT * FROM dispesa WHERE data_dispesa BETWEEN ? AND ? ORDER BY data_dispesa DESC";
    private final String selectDispesasAbastecimento = "SELECT * FROM dispesa WHERE id_dispesa IN (SELECT id_dispesa_abastecimento FROM abastecimento) AND id_dispesa NOT IN (SELECT id_dispesa_manutencao FROM manutencao) AND data_dispesa BETWEEN ? AND ? ORDER BY data_dispesa DESC";
    private final String selectDispesasManutencao = "SELECT * FROM dispesa WHERE id_dispesa NOT IN (SELECT id_dispesa_abastecimento FROM abastecimento) AND id_dispesa IN (SELECT id_dispesa_manutencao FROM manutencao) AND data_dispesa BETWEEN ? AND ? ORDER BY data_dispesa DESC";
    private final String selectDispesasOutras = "SELECT * FROM dispesa WHERE id_dispesa NOT IN (SELECT id_dispesa_abastecimento FROM abastecimento) AND id_dispesa NOT IN (SELECT id_dispesa_manutencao FROM manutencao) AND data_dispesa BETWEEN ? AND ? ORDER BY data_dispesa DESC";
    private final String selectDispesa = "SELECT * FROM dispesa WHERE id_dispesa = ?";
    private final String selectTotalDispesas = "SELECT SUM(valor_dispesa) FROM dispesa";
    private final String selectTotalDispesasPeriodo = "SELECT SUM(valor_dispesa) FROM dispesa WHERE data_dispesa BETWEEN ? AND ?";
    private final String selectTotalDispesasAbastecimento = "SELECT SUM(valor_dispesa) FROM dispesa INNER JOIN abastecimento ON id_dispesa = id_dispesa_abastecimento AND data_dispesa BETWEEN ? AND ?";
    private final String selectTotalDispesasManutencao = "SELECT SUM(valor_dispesa) FROM dispesa INNER JOIN manutencao ON id_dispesa = id_dispesa_manutencao AND data_dispesa BETWEEN ? AND ?";
    private final String selectTotalDispesasOutras = "SELECT SUM(valor_dispesa) FROM dispesa WHERE id_dispesa NOT IN (SELECT id_dispesa_abastecimento FROM abastecimento) AND id_dispesa NOT IN (SELECT id_dispesa_manutencao FROM manutencao) AND data_dispesa BETWEEN ? AND ?";
    private final String insertDispesa = "INSERT INTO dispesa (valor_dispesa, data_dispesa, descricao_dispesa) VALUES (?, ?, ?)";
    private final String deleteDispesa = "DELETE FROM dispesa WHERE id_dispesa=?";
    
    public List<Dispesa> listaDispesas(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Dispesa> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDispesas);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Dispesa d = new Dispesa(rs.getInt("id_dispesa"), rs.getDouble("valor_dispesa"), rs.getString("data_dispesa"), rs.getString("descricao_dispesa"));
                list.add(d);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as Dispesas. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public List<Dispesa> listaDispesasAbastecimento(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Dispesa> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDispesasAbastecimento);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Dispesa d = new Dispesa(rs.getInt("id_dispesa"), rs.getDouble("valor_dispesa"), rs.getString("data_dispesa"), rs.getString("descricao_dispesa"));
                list.add(d);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as dispesas de abastecimento. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public List<Dispesa> listaDispesasManutencao(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Dispesa> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDispesasManutencao);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Dispesa d = new Dispesa(rs.getInt("id_dispesa"), rs.getDouble("valor_dispesa"), rs.getString("data_dispesa"), rs.getString("descricao_dispesa"));
                list.add(d);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as dispesas de manutencao. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public List<Dispesa> listaDispesasOutras(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Dispesa> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDispesasOutras);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Dispesa d = new Dispesa(rs.getInt("id_dispesa"), rs.getDouble("valor_dispesa"), rs.getString("data_dispesa"), rs.getString("descricao_dispesa"));
                list.add(d);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as outras dispesas. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public Dispesa listaDispesa(int id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Dispesa d = new Dispesa();
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDispesa);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                d = new Dispesa(rs.getInt("id_dispesa"), rs.getDouble("valor_dispesa"), rs.getString("data_dispesa"), rs.getString("descricao_dispesa"));
            }
            return d;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar a dispesa. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Dispesa registra(Dispesa d){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertDispesa, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, d.getValor());
            stmt.setString(2, d.getData());
            stmt.setString(3, d.getDescricao());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            d.setId(id);
            return d;
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao registar a dispesa. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void excluir(int id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteDispesa);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir a dispesa. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDispesas() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDispesas);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o total das dispesas. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDispesas(String inicio, String fim){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDispesasPeriodo);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o total das dispesas. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDispesasAbastecimento(String inicio, String fim){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDispesasAbastecimento);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o total das dispesas de abastecimento. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDispesasManutencao(String inicio, String fim){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDispesasManutencao);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o total das dispesas de manutencao. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDispesasOutras(String inicio, String fim){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDispesasOutras);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o total das outras dispesas. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

}
