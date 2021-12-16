package daos;



import bd.ConnectionFactory;
import beans.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ContatoDAO {
    
    private final String selectContatos = "SELECT * FROM contato WHERE id_aluno_contato = ? ORDER BY nome_contato DESC";
    private final String insertContato = "INSERT INTO contato (id_aluno_contato, nome_contato, telefone_contato, parentesco) VALUES (?, ?, ?, ?)";
    private final String deleteContato = "DELETE FROM contato WHERE id_aluno_contato = ?";
    
    public List<Contato> listarContatos(int id_aluno) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Contato> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectContatos);
            stmt.setInt(1, id_aluno);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Contato c = new Contato(rs.getInt("id_contato"), rs.getString("nome_contato"), rs.getString("telefone_contato"), rs.getString("parentesco"));
                list.add(c);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os Contatos de um Aluno. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public Contato registrarContato(Contato c, int id_aluno){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertContato, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id_aluno);
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getParentesco());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            c.setId(id);
            return c;
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao registar um Contato. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void excluirContato(int id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteContato);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir os Contatos de um Aluno. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    
}
