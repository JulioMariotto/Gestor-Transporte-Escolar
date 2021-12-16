package daos;


import bd.ConnectionFactory;
import beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    
    private final String selectUsuarios = "SELECT * FROM usuario";
    private final String selectUsuario = "SELECT * FROM usuario WHERE login_usuario = ?";
    private final String insertUsuario = "INSERT INTO usuario (login_usuario, senha_usuario, nome_usuario) VALUES (?, ?, ?)";
    
    public List<Usuario> listaUsuarios(){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList();

        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectUsuarios);
            rs = stmt.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("login_usuario"), rs.getString("senha_usuario"), rs.getString("nome_usuario"));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar Usuários. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public Usuario getUsuario(String login)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectUsuario);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            Usuario response = null;
            if(rs.next()){
                response = new Usuario(rs.getInt("id_usuario"), rs.getString("login_usuario"), rs.getString("senha_usuario"), rs.getString("nome_usuario"));
            }
            return response;
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao procurar usuario. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());}
            try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());}               
        }
    }


    
    public Usuario cadastraUsuario(Usuario u)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertUsuario,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getNome());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            Usuario novo = new Usuario(id, u.getLogin(), u.getSenha(), u.getNome());
            return novo;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar um Usuário. "+ex.getMessage());
        } finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
}
