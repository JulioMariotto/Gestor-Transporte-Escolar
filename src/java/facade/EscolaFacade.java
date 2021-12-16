package facade;

import beans.Escola;
import daos.EscolaDAO;
import java.util.List;


public class EscolaFacade {
    
    public static Escola inserir(Escola a){
        EscolaDAO dao = new EscolaDAO();
        return dao.registrarEscola(a);
    }
    
    public static Escola buscar(int id){
        EscolaDAO dao = new EscolaDAO();
        return dao.listarEscola(id);
    }
    public static List<Escola> listar(){
        EscolaDAO dao = new EscolaDAO();
        return dao.listarEscolas();
    }
    public static void deletar(int id){
        EscolaDAO dao = new EscolaDAO();
        dao.excluirEscola(id);
    }
}
