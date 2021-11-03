/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Aluno;
import daos.AlunoDAO;
import java.util.List;

/**
 *
 * @author julio
 */
public class AlunosFacade {
    
    public static Aluno inserir(Aluno a){
        AlunoDAO dao = new AlunoDAO();
        return dao.cadastraAluno(a);
    }
    public static void alterar(Aluno a){
        AlunoDAO dao = new AlunoDAO();
        dao.updateAluno(a);
    }
    public static Aluno buscar(int id){
        AlunoDAO dao = new AlunoDAO();
        return dao.listarAluno(id);
    }
    public static List<Aluno> buscar(){
        AlunoDAO dao = new AlunoDAO();
        return dao.listarTodos();
    }
    
    public static List<Aluno> buscar(String filtro) {
        AlunoDAO dao = new AlunoDAO();
        return dao.listarAlunos(filtro);
    }
    
    public static List<Aluno> buscarAlunosAtivos(){
        AlunoDAO dao = new AlunoDAO();
        return dao.listarAlunosAtivos();
    }
    public static void deletar(int id){
        AlunoDAO dao = new AlunoDAO();
        dao.desativarAluno(id);
    }

    public static double totalAReceber() {
        AlunoDAO dao = new AlunoDAO();
        return dao.totalMensalidades();
    }

}
