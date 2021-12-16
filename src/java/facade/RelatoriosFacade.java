package facade;

import beans.Veiculo;
import daos.AbastecimentoDAO;
import daos.AlunoDAO;
import daos.DespesaDAO;
import daos.PagamentoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RelatoriosFacade {

 
    public static double[] getEntradas() {
        String data = getDate();
        PagamentoDAO dao = new PagamentoDAO();
        double total = 0;
        double[] dados = new double[13];
        for(int i = 1; i < 13; i++){
            if(i < 10){
                dados[i] = dao.totalRecebido(data.substring(0, 5) + "0" + i + "-01", data.substring(0, 5) + "0" + i + "-31");
            }
            else{
                dados[i] = dao.totalRecebido(data.substring(0, 5) + i + "-01", data.substring(0, 5) + i + "-31");
            }
                total = total + dados[i];
        }
        dados[0] = total;
        return dados;
    }

    public static double[] getSaidas() {
        String data = getDate();
        DespesaDAO dao = new DespesaDAO();
        double total = 0;
        double[] dados = new double[13];
        for(int i = 1; i < 13; i++){
            if(i < 10){
                dados[i] = dao.totalDespesas(data.substring(0, 5) + "0" + i + "-01", data.substring(0, 5) + "0" + i + "-31");
            }
            else{
                dados[i] = dao.totalDespesas(data.substring(0, 5) + i + "-01", data.substring(0, 5) + i + "-31");
            }
                total = total + dados[i];
        }
        dados[0] = total;
        return dados;
    }
    
    private static String getDate(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        return fmt.format(new Date());
    }
    
    private static String getMes(){
        SimpleDateFormat fmt = new SimpleDateFormat("MM"); 
        return fmt.format(new Date());
    }

    public static String getAno() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy"); 
        return fmt.format(new Date());
    }

    public static int[] getNovosAlunos() {
        String data = getDate();
        AlunoDAO dao = new AlunoDAO();
        int total = 0;
        int[] dados = new int[13];
        for(int i = 1; i < 13; i++){
            if(i < 10){
                dados[i] = dao.totalAlunosNovosPeriodo(data.substring(0, 5) + "0" + i + "-01", data.substring(0, 5) + "0" + i + "-31");
            }
            else{
                dados[i] = dao.totalAlunosNovosPeriodo(data.substring(0, 5) + i + "-01", data.substring(0, 5) + i + "-31");
            }
                total = total + dados[i];
        }
        dados[0] = total;
        return dados;
    }

    public static int[] getAlunosCancelados() {
        String data = getDate();
        AlunoDAO dao = new AlunoDAO();
        int total = 0;
        int[] dados = new int[13];
        for(int i = 1; i < 13; i++){
            if(i < 10){
                dados[i] = dao.totalAlunosCanceladosPeriodo(data.substring(0, 5) + "0" + i + "-01", data.substring(0, 5) + "0" + i + "-31");
            }
            else{
                dados[i] = dao.totalAlunosCanceladosPeriodo(data.substring(0, 5) + i + "-01", data.substring(0, 5) + i + "-31");
            }
                total = total + dados[i];
        }
        dados[0] = total;
        return dados;
    }

    public static int[] getKilometragem() {
        String data = getDate();
        List<Veiculo> veiculos = VeiculoFacade.buscar();
        AbastecimentoDAO dao = new AbastecimentoDAO();
        int total = 0;
        int contagem = 0;
        int[] dados = new int[13];
        for(int i = 1; i < 13; i++){
            for(Veiculo v : veiculos){
                contagem = 0;
                if(i < 10){
                    contagem = dao.getTotalRodadoVeiculo(data.substring(0, 5) + "0" + i + "-01", data.substring(0, 5) + "0" + i + "-31", v);
                }
                else{
                    contagem = dao.getTotalRodadoVeiculo(data.substring(0, 5) + i + "-01", data.substring(0, 5) + i + "-31", v);
                }
                dados[i] = dados[i] + contagem;
                total = total + dados[i];
            }
        }
        dados[0] = total;
        
        return dados;
    }
}
