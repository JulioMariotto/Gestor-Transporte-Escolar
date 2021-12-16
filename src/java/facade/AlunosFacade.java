
package facade;

import beans.Aluno;
import beans.Veiculo;
import daos.AlunoDAO;
import java.util.List;

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
        return dao.listarAlunosMarcandoPagamentosMes();
    }
    public static void deletar(int id){
        AlunoDAO dao = new AlunoDAO();
        dao.desativarAluno(id);
    }

    public static double totalAReceber() {
        AlunoDAO dao = new AlunoDAO();
        return dao.totalMensalidades();
    }

    public static List<Aluno> buscarAlunosVeiculo(Veiculo v) {
        AlunoDAO dao = new AlunoDAO();
        return dao.listarAlunosVeiculo(v.getId());
    }

   
    public static double totalMensalidadesVeiculo(Veiculo v) {
        AlunoDAO dao = new AlunoDAO();
        return dao.totalMensalidadesVeiculo(v);
    }

    public static void ativar(int id_ativar) {
        AlunoDAO dao = new AlunoDAO();
        dao.ativarAluno(id_ativar);
    }

}
