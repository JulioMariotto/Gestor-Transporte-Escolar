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
import beans.Escola;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EscolaDAO {
    
    private final String selectEscola = "SELECT * FROM escola WHERE id_escola = ?";
    private final String selectEscolas = "SELECT * FROM escola ORDER BY nome_escola DESC";
    private final String insertEscola = "INSERT INTO escola (nome_escola, telefone_escola, endereco_escola) VALUES (?, ?, ?)";
    private final String deleteEscola = "DELETE FROM escola WHERE id_escola = ?";
    
    public Escola listarEscola(int id_escola) {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        Escola c = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectEscola);
            stmt.setInt(1, id_escola);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                c = new Escola(rs.getInt("id_escola"), rs.getString("nome_escola"), rs.getString("telefone_escola"), rs.getString("endereco_escola"));  
            }
            return c;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as escolas. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public List<Escola> listarEscolas() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Escola> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectEscolas);           
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Escola e = new Escola(rs.getInt("id_escola"), rs.getString("nome_escola"), rs.getString("telefone_escola"), rs.getString("endereco_escola"));
                list.add(e);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as escolas. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public Escola registrarEscola(Escola e){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertEscola, PreparedStatement.RETURN_GENERATED_KEYS);            
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getTelefone());
            stmt.setString(3, e.getEndereco());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            e.setId(id);
            return e;
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao registar uma escola. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void excluirEscola(int id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteEscola);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir a escola. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    
}
