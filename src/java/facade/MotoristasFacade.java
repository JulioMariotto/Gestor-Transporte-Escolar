
package facade;

import beans.Motorista;
import daos.MotoristaDAO;
import java.util.List;


public class MotoristasFacade {
    
    public static Motorista inserir(Motorista a){
        MotoristaDAO dao = new MotoristaDAO();
        return dao.cadastraMotorista(a);
    }
    public static void alterar(Motorista a){
        MotoristaDAO dao = new MotoristaDAO();
        dao.updateMotorista(a);
    }
    public static Motorista buscar(int id){
        MotoristaDAO dao = new MotoristaDAO();
        return dao.getMotorista(id);
    }
    public static List<Motorista> buscar(){
        MotoristaDAO dao = new MotoristaDAO();
        return dao.listaMotoristas();
    }
    public static void deletar(int id){
        MotoristaDAO dao = new MotoristaDAO();
        dao.deleteMotorista(id);
    }
}
