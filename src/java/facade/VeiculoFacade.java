/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Veiculo;
import daos.VeiculoDAO;
import java.util.List;

/**
 *
 * @author julio
 */
public class VeiculoFacade {
    
    public static Veiculo inserir(Veiculo v){
        
        VeiculoDAO dao = new VeiculoDAO();
        return dao.cadastraVeiculo(v);
    }
    public static void alterar(Veiculo v){
        VeiculoDAO dao = new VeiculoDAO();
        dao.alterarVeiculo(v);
    }
    public static Veiculo buscar(int id){
        VeiculoDAO dao = new VeiculoDAO();
        return dao.listarVeiculo(id);
    }
    public static List<Veiculo> buscar(){
        VeiculoDAO dao = new VeiculoDAO();
        return dao.listarVeiculos();
    }
    public static Veiculo buscarVeiculoMotorista(int id_motorista){
        VeiculoDAO dao = new VeiculoDAO();
        return dao.listarVeiculoMotorista(id_motorista);
    }
    public static void deletar(int id){
        VeiculoDAO dao = new VeiculoDAO();
        dao.excluirVeiculo(id);
    }
}
