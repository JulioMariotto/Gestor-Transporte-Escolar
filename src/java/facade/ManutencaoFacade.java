/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Dispesa;
import beans.Manutencao;
import beans.Veiculo;
import daos.ManutencaoDAO;
import java.util.List;

/**
 *
 * @author julio
 */
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
    
    
}
