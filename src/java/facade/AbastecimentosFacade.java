/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Abastecimento;
import beans.Dispesa;
import beans.Veiculo;
import daos.AbastecimentoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author julio
 */
public class AbastecimentosFacade {
    
    public static List<Abastecimento> listar(Veiculo v){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.listaAbastecimentosVeiculo(v);
    }
    
    public static List<Abastecimento> listar(){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.listaAbastecimentos();
    }
    
    public static Abastecimento listar(int id_dispesa){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.listaAbastecimento(id_dispesa);
    }
    
    public static Abastecimento registrar(Abastecimento a){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.registra(a);
    }
    
    public static void remover(int id){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        dao.remove(id);
    }
    
    public static double totalAbastecido(){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalAbastecido("2015-01-01", getData());
    }
    
    public static double totalAbastecidoMes(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        String data = fmt.format(new Date());
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalAbastecido(data.substring(0, 8) + "01", data.substring(0, 8) + "31");
    }
    
    public static double totalAbastecido(String inicio, String fim){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalAbastecido(inicio, fim);
    }
    
    public static double totalAbastecido(String inicio, String fim, Veiculo v){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalAbastecidoVeiculo(inicio, fim, v);
    }
    
    public static int totalRodado(Veiculo v){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalRodadoVeiculo("2015-01-01", getData(), v);
    }
    
    public static int totalRodado(String inicio, String fim, Veiculo v){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalRodadoVeiculo(inicio, fim, v);
    }
    
    public static int totalRodadoMes(Veiculo v) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        String data = fmt.format(new Date());
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalRodadoVeiculo(data.substring(0, 8) + "01", data.substring(0, 8) + "31", v);
    }
    
    public static String getData(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        return fmt.format(new Date());
    }

    public static double mediaMes(Veiculo v){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        AbastecimentoDAO dao = new AbastecimentoDAO();
        String data = fmt.format(new Date());
        return totalRodadoMes(v)/ totalAbastecido(data.substring(0, 8) + "01", data.substring(0, 8) + "31", v);
    }
    
    
}
