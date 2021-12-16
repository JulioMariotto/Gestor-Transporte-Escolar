
package daos;

import bd.ConnectionFactory;
import beans.Abastecimento;
import beans.Veiculo;
import facade.DespesaFacade;
import facade.VeiculoFacade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbastecimentoDAO {
    
    private final String selectAbastecimentosPorVeiculo = "SELECT * FROM abastecimento INNER JOIN despesa on id_despesa_abastecimento = id_despesa and id_veiculo_abastecimento = ? ORDER BY data_despesa DESC";
    private final String selectTodosAbastecimentos = "SELECT * FROM abastecimento INNER JOIN despesa on id_despesa_abastecimento = id_despesa ORDER BY data_despesa DESC";
    private final String selectAbastecimento = "SELECT * FROM abastecimento WHERE id_despesa_abastecimento = ?";
    private final String selectTotalAbastecidoPeriodo = "SELECT SUM(litros_abastecimento) FROM abastecimento INNER JOIN despesa on id_despesa_abastecimento = id_despesa AND data_despesa BETWEEN ? AND ?";
    private final String selectTotalAbastecidoVeiculoPeriodo = "SELECT SUM(litros_abastecimento) FROM abastecimento INNER JOIN despesa on id_despesa_abastecimento = id_despesa AND id_veiculo_abastecimento = ? AND data_despesa BETWEEN ? AND ?";
    private final String selectValorTotalAbastecidoPeriodo = "SELECT SUM(valor_despesa) FROM abastecimento INNER JOIN despesa on id_despesa_abastecimento = id_despesa AND data_despesa BETWEEN ? AND ?";
    private final String selectTotalRodadoVeiculoPeriodo = "SELECT MAX(km_abastecimento) - MIN(km_abastecimento) FROM abastecimento INNER JOIN despesa on id_despesa_abastecimento = id_despesa AND id_veiculo_abastecimento = ? AND data_despesa BETWEEN ? AND ?";
    private final String insertAbastecimento = "INSERT INTO abastecimento (id_despesa_abastecimento, id_veiculo_abastecimento, km_abastecimento, litros_abastecimento, posto_abastecimento) VALUES (?, ?, ?, ?, ?)";
    private final String deleteAbastecimento = "DELETE FROM abastecimento WHERE id_despesa_abastecimento = ?";
    
    
    
    public List<Abastecimento> listaAbastecimentosVeiculo(Veiculo v) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Abastecimento> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAbastecimentosPorVeiculo);
            stmt.setInt(1, v.getId());
            rs = stmt.executeQuery();
            DespesaDAO dao = new DespesaDAO();
            while(rs.next()){
                Abastecimento aba = new Abastecimento(v, dao.listaDespesa(rs.getInt("id_despesa_abastecimento")), rs.getInt("km_abastecimento"), rs.getDouble("litros_abastecimento"), rs.getString("posto_abastecimento"));
                lista.add(aba);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os abastecimentos de um veículo. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public List<Abastecimento> listaAbastecimentos() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Abastecimento> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTodosAbastecimentos);
            rs = stmt.executeQuery();
            DespesaDAO dDAO = new DespesaDAO();
            VeiculoDAO vDAO = new VeiculoDAO();
            while(rs.next()){
                Abastecimento aba = new Abastecimento(vDAO.listarVeiculo(rs.getInt("id_veiculo_abastecimento")), dDAO.listaDespesa(rs.getInt("id_despesa_abastecimento")), rs.getInt("km_abastecimento"), rs.getDouble("litros_abastecimento"), rs.getString("posto_abastecimento"));
                lista.add(aba);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os abastecimentos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Abastecimento listaAbastecimento(int id_despesa) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAbastecimento);
            stmt.setInt(1, id_despesa);
            rs = stmt.executeQuery();
            Abastecimento aba = null;
            while(rs.next()){
                aba = new Abastecimento(VeiculoFacade.buscar(rs.getInt("id_veiculo_abastecimento")), DespesaFacade.buscar(id_despesa), rs.getInt("km_abastecimento"), rs.getDouble("litros_abastecimento"), rs.getString("posto_abastecimento"));
            }
            return aba;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar um abastecimento. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Abastecimento registra(Abastecimento a){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertAbastecimento);
            stmt.setInt(1, a.getDespesa().getId());
            stmt.setInt(2, a.getVeiculo().getId());
            stmt.setInt(3, a.getKilometragem());
            stmt.setDouble(4, a.getLitros());
            stmt.setString(5, a.getPosto());
            stmt.executeUpdate();
            return a;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao registrar um abastecimento. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void remove(int id){
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteAbastecimento);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover um abastecimento. "+ex.getMessage());
        }finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    
    public double getTotalAbastecido(String inicio, String fim) {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalAbastecidoPeriodo);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total abastecido. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double getTotalAbastecidoVeiculo(String inicio, String fim, Veiculo v) {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalAbastecidoVeiculoPeriodo);
            stmt.setInt(1, v.getId());
            stmt.setString(2, inicio);
            stmt.setString(3, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total abastecido de um Veículo. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public int getTotalRodadoVeiculo(String inicio, String fim, Veiculo v) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalRodadoVeiculoPeriodo);
            stmt.setInt(1, v.getId());
            stmt.setString(2, inicio);
            stmt.setString(3, fim);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular a quilometragem total de um Veículo. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public double valorTotalAbastecidoPeriodo(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectValorTotalAbastecidoPeriodo);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total abastecido entre " + inicio + " e " + fim + ". " +ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

}
