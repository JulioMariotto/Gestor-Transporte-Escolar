
package facade;

import beans.Contato;
import daos.ContatoDAO;
import java.util.List;

public class ContatoFacade {
    
    public static Contato inserir(Contato a, int id_aluno){
        ContatoDAO dao = new ContatoDAO();
        return dao.registrarContato(a, id_aluno);
    }
    public static List<Contato> listar(int id_aluno){
        ContatoDAO dao = new ContatoDAO();
        return dao.listarContatos(id_aluno);
    }
    public static void deletar(int id){
        ContatoDAO dao = new ContatoDAO();
        dao.excluirContato(id);
    }
}
