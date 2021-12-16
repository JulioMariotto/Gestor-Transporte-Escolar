package facade;

import beans.Despesa;
import daos.DespesaDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class DespesaFacade {
    
    public static List<Despesa> buscarTodas(){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesas("2010-01-01", getData());
    }
    
    public static List<Despesa> buscarTodas(String inicio, String fim){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesas(inicio, fim);
    }
    
    public static List<Despesa> buscarDespesasAbastecimento(){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesasAbastecimento("2010-01-01", getData());
    }
    
    public static List<Despesa> buscarDespesasAbastecimento(String inicio, String fim){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesasAbastecimento(inicio, fim);
    }
    
    public static List<Despesa> buscarDespesasManutencao(){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesasManutencao("2010-01-01", getData());
    }
    
    public static List<Despesa> buscarDespesasManutencao(String inicio, String fim){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesasManutencao(inicio, fim);
    }
    
    public static List<Despesa> buscarOutrasDespesas(){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesasOutras("2010-01-01", getData());
    }
    
    public static List<Despesa> buscarOutrasDespesas(String inicio, String fim){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesasOutras(inicio, fim);
    }
    
    public static Despesa buscar(int id){
        DespesaDAO dao = new DespesaDAO();
        return dao.listaDespesa(id);
    }
    
    public static Despesa registrar(Despesa d){
        DespesaDAO dao = new DespesaDAO();
        return dao.registra(d);
    }
    
    public static void deletar(int id){
        DespesaDAO dao = new DespesaDAO();
        AbastecimentosFacade.removerAbastecimento(id);
        ManutencaoFacade.remover(id);
        dao.excluir(id);
    }
    
    public static double totalDespesasMes(){
        DespesaDAO dao = new DespesaDAO();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        String data = fmt.format(new Date());
        return dao.totalDespesas(data.substring(0, 8) + "01", data.substring(0, 8) + "31");
    }
   
    public static double totalDespesas() {
        DespesaDAO dao = new DespesaDAO();
        return dao.totalDespesas();
    }
    
    public static double totalDespesas(String inicio, String fim){
        DespesaDAO dao = new DespesaDAO();
        return dao.totalDespesas(inicio, fim);
    }
    
    public static double totalDespesasAbastecimento(String inicio, String fim){
        DespesaDAO dao = new DespesaDAO();
        return dao.totalDespesasAbastecimento(inicio, fim);
    }
    
    public static double totalDespesasManutencao(String inicio, String fim){
        DespesaDAO dao = new DespesaDAO();
        return dao.totalDespesasManutencao(inicio, fim);
    }
    
    public static double totalDespesasOutras(String inicio, String fim){
        DespesaDAO dao = new DespesaDAO();
        return dao.totalDespesasOutras(inicio, fim);
    }
    
    public static String getData(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        return fmt.format(new Date());
    }

}
