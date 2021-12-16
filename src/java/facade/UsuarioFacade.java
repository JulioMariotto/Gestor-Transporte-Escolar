package facade;

import beans.Usuario;
import daos.UsuarioDAO;
import java.util.List;

public class UsuarioFacade {
    
    public static Usuario inserir(Usuario u){
        
        UsuarioDAO dao = new UsuarioDAO();
        return dao.cadastraUsuario(u);
    }

    public static Usuario buscar(String login){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.getUsuario(login);
    }

    public static List<Usuario> buscar(){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listaUsuarios();
    }
}
