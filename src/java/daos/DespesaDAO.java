package daos;


import bd.ConnectionFactory;
import beans.Despesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DespesaDAO {
    
    private final String selectDespesas = "SELECT * FROM despesa WHERE data_despesa BETWEEN ? AND ? ORDER BY data_despesa DESC";
    private final String selectDespesasAbastecimento = "SELECT * FROM despesa WHERE id_despesa IN (SELECT id_despesa_abastecimento FROM abastecimento) AND id_despesa NOT IN (SELECT id_despesa_manutencao FROM manutencao) AND data_despesa BETWEEN ? AND ? ORDER BY data_despesa DESC";
    private final String selectDespesasManutencao = "SELECT * FROM despesa WHERE id_despesa NOT IN (SELECT id_despesa_abastecimento FROM abastecimento) AND id_despesa IN (SELECT id_despesa_manutencao FROM manutencao) AND data_despesa BETWEEN ? AND ? ORDER BY data_despesa DESC";
    private final String selectDespesasOutras = "SELECT * FROM despesa WHERE id_despesa NOT IN (SELECT id_despesa_abastecimento FROM abastecimento) AND id_despesa NOT IN (SELECT id_despesa_manutencao FROM manutencao) AND data_despesa BETWEEN ? AND ? ORDER BY data_despesa DESC";
    private final String selectDespesa = "SELECT * FROM despesa WHERE id_despesa = ?";
    private final String selectTotalDespesas = "SELECT SUM(valor_despesa) FROM despesa";
    private final String selectTotalDespesasPeriodo = "SELECT SUM(valor_despesa) FROM despesa WHERE data_despesa BETWEEN ? AND ?";
    private final String selectTotalDespesasAbastecimento = "SELECT SUM(valor_despesa) FROM despesa INNER JOIN abastecimento ON id_despesa = id_despesa_abastecimento AND data_despesa BETWEEN ? AND ?";
    private final String selectTotalDespesasManutencao = "SELECT SUM(valor_despesa) FROM despesa INNER JOIN manutencao ON id_despesa = id_despesa_manutencao AND data_despesa BETWEEN ? AND ?";
    private final String selectTotalDespesasOutras = "SELECT SUM(valor_despesa) FROM despesa WHERE id_despesa NOT IN (SELECT id_despesa_abastecimento FROM abastecimento) AND id_despesa NOT IN (SELECT id_despesa_manutencao FROM manutencao) AND data_despesa BETWEEN ? AND ?";
    private final String insertDespesa = "INSERT INTO despesa (valor_despesa, data_despesa, descricao_despesa) VALUES (?, ?, ?)";
    private final String deleteDespesa = "DELETE FROM despesa WHERE id_despesa=?";
    
    public List<Despesa> listaDespesas(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Despesa> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDespesas);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Despesa d = new Despesa(rs.getInt("id_despesa"), rs.getDouble("valor_despesa"), rs.getString("data_despesa"), rs.getString("descricao_despesa"));
                list.add(d);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as Despesas entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public List<Despesa> listaDespesasAbastecimento(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Despesa> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDespesasAbastecimento);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Despesa d = new Despesa(rs.getInt("id_despesa"), rs.getDouble("valor_despesa"), rs.getString("data_despesa"), rs.getString("descricao_despesa"));
                list.add(d);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as Despesas de Abastecimento entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public List<Despesa> listaDespesasManutencao(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Despesa> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDespesasManutencao);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Despesa d = new Despesa(rs.getInt("id_despesa"), rs.getDouble("valor_despesa"), rs.getString("data_despesa"), rs.getString("descricao_despesa"));
                list.add(d);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as Despesas de Manutencao entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public List<Despesa> listaDespesasOutras(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Despesa> list = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDespesasOutras);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Despesa d = new Despesa(rs.getInt("id_despesa"), rs.getDouble("valor_despesa"), rs.getString("data_despesa"), rs.getString("descricao_despesa"));
                list.add(d);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar as outras Despesas entre " + inicio + " e " + fim + " ." + ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
        
    }
    
    public Despesa listaDespesa(int id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Despesa d = new Despesa();
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectDespesa);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                d = new Despesa(rs.getInt("id_despesa"), rs.getDouble("valor_despesa"), rs.getString("data_despesa"), rs.getString("descricao_despesa"));
            }
            return d;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar uma Despesa. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Despesa registra(Despesa d){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertDespesa, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, d.getValor());
            stmt.setString(2, d.getData());
            stmt.setString(3, d.getDescricao());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            d.setId(id);
            return d;
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao registar uma Despesa. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void excluir(int id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deleteDespesa);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir uma Despesa. "+ex.getMessage());
        }
        finally {
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDespesas() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDespesas);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total das Despesas. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDespesas(String inicio, String fim){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDespesasPeriodo);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total das Despesas entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDespesasAbastecimento(String inicio, String fim){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDespesasAbastecimento);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total das Despesas de Abastecimento entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDespesasManutencao(String inicio, String fim){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDespesasManutencao);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total das Despesas de Manutencao entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalDespesasOutras(String inicio, String fim){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalDespesasOutras);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            rs.next();
            return (double)rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total das outras Despesas entre " + inicio + " e " + fim + " ."+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

}
