package daos;

import bd.ConnectionFactory;
import beans.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VeiculoDAO {
    
    
    private final String selectVeiculos = "SELECT * FROM veiculo";
    private final String selectVeiculo = "SELECT * FROM veiculo WHERE id_veiculo = ?";
     private final String selectVeiculosMotorista = "SELECT * FROM veiculo WHERE id_motorista_veiculo = ?";
    private final String insertVeiculo = "INSERT INTO veiculo (modelo_veiculo, cor_veiculo, numero_veiculo, placa_veiculo, num_licenca_veiculo, capacidade_veiculo, id_motorista_veiculo) VALUES (?,?,?,?,?,?,?)";
    private final String updateVeiculo = "UPDATE veiculo SET modelo_veiculo = ?, cor_veiculo = ?, numero_veiculo = ?, placa_veiculo = ?, num_licenca_veiculo = ?, capacidade_veiculo = ?, status_veiculo = 1, id_motorista_veiculo = ? WHERE id_veiculo = ?";
    private final String deleteVeiculo = "DELETE FROM veiculo WHERE id_veiculo=?";
    
    public List<Veiculo> listarVeiculos(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> lista = new ArrayList();
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectVeiculos);
            rs = stmt.executeQuery();
            MotoristaDAO dao = new MotoristaDAO();
            while(rs.next()){
                Veiculo v = new Veiculo(rs.getInt("id_veiculo"), rs.getString("numero_veiculo"), rs.getString("placa_veiculo"), rs.getString("num_licenca_veiculo"), rs.getInt("capacidade_veiculo"), rs.getString("modelo_veiculo"), rs.getString("cor_veiculo"), dao.getMotorista(rs.getInt("id_motorista_veiculo")));
                lista.add(v);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar todos os Veículos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Veiculo listarVeiculo(int id){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectVeiculo);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            MotoristaDAO dao = new MotoristaDAO();
            Veiculo v = null;
            while(rs.next()){
                 v = new Veiculo(rs.getInt("id_veiculo"), rs.getString("numero_veiculo"), rs.getString("placa_veiculo"), rs.getString("num_licenca_veiculo"), rs.getInt("capacidade_veiculo"), rs.getString("modelo_veiculo"), rs.getString("cor_veiculo"), dao.getMotorista(rs.getInt("id_motorista_veiculo")));
            }
            return v;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar um Veículo. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Veiculo cadastraVeiculo(Veiculo v){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertVeiculo, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, v.getModelo());
            stmt.setString(2, v.getCor());
            stmt.setString(3, v.getNumero());
            stmt.setString(4, v.getPlaca());
            stmt.setString(5, v.getLicenca());
            stmt.setInt(6, v.getCapacidade());
            stmt.setInt(7, v.getMotorista().getId());
            
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return listarVeiculo(id);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao registrar um Veículo. "+ex.getMessage());
        } finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Veiculo alterarVeiculo(Veiculo v){
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(updateVeiculo);
            stmt.setString(1, v.getModelo());
            stmt.setString(2, v.getCor());
            stmt.setString(3, v.getNumero());
            stmt.setString(4, v.getPlaca());
            stmt.setString(5, v.getLicenca());
            stmt.setInt(6, v.getCapacidade());
            stmt.setInt(7, v.getMotorista().getId());
            stmt.setInt(8, v.getId());
            
            stmt.executeUpdate();
            
            return listarVeiculo(v.getId());
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um Veículo. "+ex.getMessage());
        } finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void excluirVeiculo(int id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteVeiculo);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir um Veículo. "+ex.getMessage());
        } finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }

    public Veiculo listarVeiculoMotorista(int id_motorista) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
               
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectVeiculosMotorista);
            stmt.setInt(1, id_motorista);
            rs = stmt.executeQuery();
            MotoristaDAO dao = new MotoristaDAO();
            Veiculo v = null;
            while(rs.next()){
                 v = new Veiculo(rs.getInt("id_veiculo"), rs.getString("numero_veiculo"), rs.getString("placa_veiculo"), rs.getString("num_licenca_veiculo"), rs.getInt("capacidade_veiculo"), rs.getString("modelo_veiculo"), rs.getString("cor_veiculo"), dao.getMotorista(rs.getInt("id_motorista_veiculo")));
            }
            return v;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar o Veículo de um Motorista. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
}
