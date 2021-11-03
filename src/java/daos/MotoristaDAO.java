package daos;
import bd.ConnectionFactory;
import beans.Motorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author julio
 */
public class MotoristaDAO {
    
    private final String selectMotoristas = "SELECT * FROM motorista ORDER BY nome_motorista";
    private final String selectMotorista = "SELECT * FROM motorista WHERE id_motorista = ?";
    private final String deleteMotorista = "DELETE FROM motorista WHERE id_motorista = ?";
    private final String insertMotorista = "INSERT INTO motorista (nome_motorista, cnh_motorista, telefone_motorista, endereco_motorista) VALUES (?, ?, ?, ?)";
    private final String updateMotorista = "UPDATE motorista SET nome_motorista = ?, cnh_motorista = ?, telefone_motorista = ?, endereco_motorista = ? WHERE id_motorista = ?";
    
    public List<Motorista> listaMotoristas(){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Motorista> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectMotoristas);
            rs = stmt.executeQuery();
            while(rs.next()){
                Motorista motorista = new Motorista(rs.getInt("id_motorista"),
                                          rs.getString("nome_motorista"), 
                                          rs.getString("cnh_motorista"), 
                                          rs.getString("telefone_motorista"), 
                                          rs.getString("endereco_motorista")
                );
                lista.add(motorista);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar motoristas. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Motorista getMotorista(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectMotorista);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Motorista motorista = null;
            while(rs.next()){
                motorista = new Motorista(rs.getInt("id_motorista"),
                                          rs.getString("nome_motorista"), 
                                          rs.getString("cnh_motorista"), 
                                          rs.getString("telefone_motorista"), 
                                          rs.getString("endereco_motorista")
                );
            }
            return motorista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o motorista. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void deleteMotorista(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteMotorista);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar motorista. "+ex.getMessage());
        }
        finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Motorista cadastraMotorista(Motorista m)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertMotorista, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCnh());
            stmt.setString(3, m.getTelefone());
            stmt.setString(4, m.getEndereco());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            
            Motorista novo = getMotorista(id);
            return novo;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um motorista no banco de dados. "+ex.getMessage());
        } finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void updateMotorista(Motorista a)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(updateMotorista);
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getCnh());
            stmt.setString(3, a.getTelefone());
            stmt.setString(4, a.getEndereco());
            stmt.setInt(5, a.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um motorista no banco de dados. "+ex.getMessage());
        } finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
}
