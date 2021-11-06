/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Dispesa;
import daos.DispesaDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author julio
 */
public class DispesaFacade {
    
    public static List<Dispesa> buscarTodas(){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesas("2010-01-01", getData());
    }
    
    public static List<Dispesa> buscarTodas(String inicio, String fim){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesas(inicio, fim);
    }
    
    public static List<Dispesa> buscarDispesasAbastecimento(){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesasAbastecimento("2010-01-01", getData());
    }
    
    public static List<Dispesa> buscarDispesasAbastecimento(String inicio, String fim){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesasAbastecimento(inicio, fim);
    }
    
    public static List<Dispesa> buscarDispesasManutencao(){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesasManutencao("2010-01-01", getData());
    }
    
    public static List<Dispesa> buscarDispesasManutencao(String inicio, String fim){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesasManutencao(inicio, fim);
    }
    
    public static List<Dispesa> buscarOutrasDispesas(){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesasOutras("2010-01-01", getData());
    }
    
    public static List<Dispesa> buscarOutrasDispesas(String inicio, String fim){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesasOutras(inicio, fim);
    }
    
    public static Dispesa buscar(int id){
        DispesaDAO dao = new DispesaDAO();
        return dao.listaDispesa(id);
    }
    
    public static Dispesa registrar(Dispesa d){
        DispesaDAO dao = new DispesaDAO();
        return dao.registra(d);
    }
    
    public static void deletar(int id){
        DispesaDAO dao = new DispesaDAO();
        AbastecimentosFacade.removerAbastecimento(id);
        ManutencaoFacade.remover(id);
        dao.excluir(id);
    }
    
    public static double totalDispesasMes(){
        DispesaDAO dao = new DispesaDAO();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        String data = fmt.format(new Date());
        return dao.totalDispesas(data.substring(0, 8) + "01", data.substring(0, 8) + "31");
    }
   
    public static double totalDispesas() {
        DispesaDAO dao = new DispesaDAO();
        return dao.totalDispesas();
    }
    
    public static double totalDispesas(String inicio, String fim){
        DispesaDAO dao = new DispesaDAO();
        return dao.totalDispesas(inicio, fim);
    }
    
    public static double totalDispesasAbastecimento(String inicio, String fim){
        DispesaDAO dao = new DispesaDAO();
        return dao.totalDispesasAbastecimento(inicio, fim);
    }
    
    public static double totalDispesasManutencao(String inicio, String fim){
        DispesaDAO dao = new DispesaDAO();
        return dao.totalDispesasManutencao(inicio, fim);
    }
    
    public static double totalDispesasOutras(String inicio, String fim){
        DispesaDAO dao = new DispesaDAO();
        return dao.totalDispesasOutras(inicio, fim);
    }
    
    public static String getData(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        return fmt.format(new Date());
    }

}
