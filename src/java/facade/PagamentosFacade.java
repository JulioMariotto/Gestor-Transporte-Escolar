package facade;

import beans.Pagamento;
import daos.PagamentoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PagamentosFacade {
    
    public static Pagamento registrar(Pagamento p){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.registra(p);
    }
    public static void remover(int id){
        PagamentoDAO dao = new PagamentoDAO();
        dao.remove(id);
    }
    public static List<Pagamento> listar(){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.lista50Pagamentos();
    }
    
    public static List<Pagamento> listarMesRef(){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.listaPagamentosMesRef();
    }
    
    public static List<Pagamento> listarAnoRef(){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.listaPagamentosAnoRef();
    }
    
    public static List<Pagamento> listar(String data_inicio, String data_fim){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.listaPagamentos(data_inicio, data_fim);
    }
    
    public static List<Pagamento> listarTodos(){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.listaPagamentos();
    }
    
    public static List<Pagamento> listar(int id){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.listaPagamentosAlunos(id);
    }
    
    public static String getData(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        return fmt.format(new Date());
    }

    public static double totalRecebido() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        PagamentoDAO dao = new PagamentoDAO();
        String data = fmt.format(new Date());
        return dao.totalRecebido(data.substring(0, 8) + "01", data.substring(0, 8) + "31");
    }
    
    public static double totalRecebido(String data_inicio, String data_fim){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.totalRecebido(data_inicio, data_fim);
    }
    
    public static double totalJaRecebido() {
        PagamentoDAO dao = new PagamentoDAO();
        return dao.totalRecebido();
    }
    
    public static double totalRecebidoMes() {
        PagamentoDAO dao = new PagamentoDAO();
        return dao.recebidoMes();
    }
    
    public static double totalRecebidoAno(){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.totalRecebido();
    }

            
}
