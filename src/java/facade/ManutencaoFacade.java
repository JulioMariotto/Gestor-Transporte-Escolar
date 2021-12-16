package facade;

import beans.Despesa;
import beans.Manutencao;
import beans.Veiculo;
import daos.ManutencaoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ManutencaoFacade {
    
     public static Manutencao buscar(int id_dispesa){
        ManutencaoDAO dao = new ManutencaoDAO();
        return dao.listarManutencao(id_dispesa);
    }
    
     public static List<Manutencao> buscar(Veiculo v){
        ManutencaoDAO dao = new ManutencaoDAO();
        return dao.listarManutencoes(v);
    } 
    
     public static List<Manutencao> buscar(String inicio, String fim){
        ManutencaoDAO dao = new ManutencaoDAO();
        return dao.listarManutencoes(inicio, fim);
    }
      
    public static List<Manutencao> buscar(){
        ManutencaoDAO dao = new ManutencaoDAO();
        return dao.listarManutencoes();
    }
    
    public static void registrar(Manutencao m){
        ManutencaoDAO dao = new ManutencaoDAO();
        dao.registra(m);
    }
    
    public static void remover(int idDispesa){
        ManutencaoDAO dao = new ManutencaoDAO();
        dao.remove(idDispesa);
    }

    public static double totalGastoMes() {
       SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
       String data = fmt.format(new Date());
       ManutencaoDAO dao = new ManutencaoDAO();
       return dao.totalGastoManutencoes(data.substring(0, 8) + "01", data.substring(0, 8) + "31");
    }
    
    
}
