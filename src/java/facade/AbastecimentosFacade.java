/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Abastecimento;
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
    
    public static List<Abastecimento> listarAbastecimentosVeiculo(Veiculo v){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.listaAbastecimentosVeiculo(v);
    }
    
    public static List<Abastecimento> listarTodosAbastecimentos(){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.listaAbastecimentos();
    }
    
    public static Abastecimento listarAbastecimento(int id_dispesa){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.listaAbastecimento(id_dispesa);
    }
    
    public static Abastecimento registrarAbastecimento(Abastecimento a){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.registra(a);
    }
    
    public static void removerAbastecimento(int id){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        dao.remove(id);
    }
    
    public static double totalAbastecido(){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalAbastecido("2015-01-01", getData());
    }
    
    public static double totalAbastecidoMes(){
        String data = getData();
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalAbastecido(data.substring(0, 8) + "01", data.substring(0, 8) + "31");
    }
    
    
    
    public static double totalAbastecidoPeriodo(String inicio, String fim){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalAbastecido(inicio, fim);
    }
    
    public static double totalAbastecidoVeiculo(Veiculo v){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalAbastecidoVeiculo("2000-01-01", getData(), v);
    }
    
    public static int totalRodadoVeiculo(Veiculo v){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalRodadoVeiculo("2000-01-01", getData(), v);
    }
    
    public static int totalRodadoMes() {
        String data = getData();
        List<Veiculo> veiculos = VeiculoFacade.buscar();
        int total = 0;
        for(Veiculo v : veiculos){
            total = total + totalRodadoPeriodoVeiculo(data.substring(0, 8) + "01", data.substring(0, 8) + "31", v);
        }
        return total;
    }

    public static int totalRodadoPeriodoVeiculo(String inicio, String fim, Veiculo v){
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.getTotalRodadoVeiculo(inicio, fim, v);
    }
       
    public static double mediaMes(Veiculo v){
        String data = getData();
        return totalRodadoPeriodoVeiculo(data.substring(0, 8) + "01", data.substring(0, 8) + "31", v)/ totalAbastecidoVeiculo(v);
    }
     
    public static double media(Veiculo v){
        return totalRodadoVeiculo(v)/ totalAbastecidoVeiculo(v);
    }
    
    public static double valorAbastecidoMes() {
        String data = getData();
        AbastecimentoDAO dao = new AbastecimentoDAO();
        return dao.valorTotalAbastecidoPeriodo(data.substring(0, 8) + "01", data.substring(0, 8) + "31");
    }
    
    public static String getData(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        return fmt.format(new Date());
    }



    
    
    
    
}
