package daos;
import bd.ConnectionFactory;
import beans.Aluno;
import beans.Contato;
import beans.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AlunoDAO {
    
    private final String selectAlunos = "SELECT * FROM aluno ORDER BY nome_aluno";
    private final String selectAlunosAtivos = "SELECT * FROM aluno WHERE status_aluno = 1 ORDER BY nome_aluno";
    private final String selectAlunosVeiculo = "SELECT * FROM aluno WHERE status_aluno = 1 AND id_veiculo_aluno = ? ORDER BY nome_aluno";
    private final String selectAlunosFiltro = "SELECT * FROM aluno WHERE nome_aluno LIKE ? ORDER BY nome_aluno";
    
    private final String selectAlunosMarcandoPagamentoMes = "select *, case when aluno.id_aluno not in (select pagamento.id_aluno from pagamento where pagamento.mes_referencia = ?) THEN 3 else 2 end as pago from aluno where aluno.status_aluno = 1 order by nome_aluno";
    
    private final String selectAluno = "SELECT * FROM aluno WHERE id_aluno = ?";
    private final String selectTotalMensalidades = "	SELECT SUM(valor_mensalidade_aluno) as soma FROM aluno WHERE status_aluno = 1";
    private final String selectTotalMensalidadesVeiculo = "SELECT SUM(valor_mensalidade_aluno) as soma FROM aluno WHERE status_aluno = 1 AND id_veiculo_aluno = ?";
    private final String selectTotalAlunosNovosPeriodo = "SELECT COUNT(*) AS total FROM aluno WHERE data_inicio_aluno BETWEEN ? AND ?";
    private final String selectTotalAlunosCanceladosPeriodo = "SELECT COUNT(*) AS total FROM aluno WHERE data_fim_aluno BETWEEN ? AND ?";
    private final String desativarAluno = "UPDATE aluno SET status_aluno = 0, data_fim_aluno = ? WHERE id_aluno = ?";
    private final String ativarAluno = "UPDATE aluno SET status_aluno = 1, data_fim_aluno = '2000-01-01' WHERE id_aluno = ?";
    private final String insertAluno = "INSERT INTO aluno (nome_aluno, telefone_aluno, endereco_aluno,  nome_responsavel,  cpf_responsavel,  id_escola_aluno,  periodo_aluno,  turma_aluno,  horario_casa_ida,  horario_escola_ida,  horario_escola_volta,  horario_casa_volta, valor_mensalidade_aluno,  vencimento_aluno,  data_inicio_aluno, data_nasc_aluno, id_veiculo_aluno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateAluno = "UPDATE aluno SET nome_aluno = ?, telefone_aluno = ?, endereco_aluno = ?, nome_responsavel = ?, cpf_responsavel = ?, id_escola_aluno = ?, periodo_aluno = ?, turma_aluno = ?, horario_casa_ida = ?, horario_escola_ida = ?, horario_escola_volta = ? , horario_casa_volta = ?, valor_mensalidade_aluno = ?, vencimento_aluno = ?, data_nasc_aluno = ?, id_veiculo_aluno = ? WHERE id_aluno=?";
    
    public List<Aluno> listarTodos(){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAlunos);
            rs = stmt.executeQuery();
            
            ContatoDAO contato = new ContatoDAO();
            EscolaDAO escola = new EscolaDAO();
            VeiculoDAO veiculo = new VeiculoDAO();
            
            while(rs.next()){
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"), 
                        rs.getString("nome_aluno"), 
                        rs.getString("telefone_aluno"), 
                        rs.getString("endereco_aluno"), 
                        rs.getString("data_nasc_aluno"), 
                        rs.getString("nome_responsavel"), 
                        rs.getString("cpf_responsavel"),
                        contato.listarContatos(rs.getInt("id_aluno")),
                        veiculo.listarVeiculo(rs.getInt("id_veiculo_aluno")),
                        escola.listarEscola(rs.getInt("id_escola_aluno")),                          
                        rs.getString("periodo_aluno"), 
                        rs.getString("turma_aluno"), 
                        rs.getString("horario_casa_ida"), 
                        rs.getString("horario_escola_ida"), 
                        rs.getString("horario_escola_volta"), 
                        rs.getString("horario_casa_volta"), 
                        rs.getFloat("valor_mensalidade_aluno"), 
                        rs.getInt("vencimento_aluno"), 
                        rs.getString("data_inicio_aluno"), 
                        rs.getString("data_fim_aluno"), 
                        rs.getInt("status_aluno")
                         
                );
               
                lista.add(aluno);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar todos os Alunos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    public List<Aluno> listarAlunosVeiculo(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAlunosVeiculo);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            ContatoDAO contato = new ContatoDAO();
            EscolaDAO escola = new EscolaDAO();
            VeiculoDAO veiculo = new VeiculoDAO();
            
            while(rs.next()){
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"), 
                        rs.getString("nome_aluno"), 
                        rs.getString("telefone_aluno"), 
                        rs.getString("endereco_aluno"), 
                        rs.getString("data_nasc_aluno"), 
                        rs.getString("nome_responsavel"), 
                        rs.getString("cpf_responsavel"),
                        contato.listarContatos(rs.getInt("id_aluno")),
                        veiculo.listarVeiculo(id),
                        escola.listarEscola(rs.getInt("id_escola_aluno")),                          
                        rs.getString("periodo_aluno"), 
                        rs.getString("turma_aluno"), 
                        rs.getString("horario_casa_ida"), 
                        rs.getString("horario_escola_ida"), 
                        rs.getString("horario_escola_volta"), 
                        rs.getString("horario_casa_volta"), 
                        rs.getFloat("valor_mensalidade_aluno"), 
                        rs.getInt("vencimento_aluno"), 
                        rs.getString("data_inicio_aluno"), 
                        rs.getString("data_fim_aluno"), 
                        rs.getInt("status_aluno")
                );
               
                lista.add(aluno);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar todos os Alunos de um Veículo. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public List<Aluno> listarAlunosAtivos(){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAlunosAtivos);
            rs = stmt.executeQuery();
            
            ContatoDAO contato = new ContatoDAO();
            EscolaDAO escola = new EscolaDAO();
            VeiculoDAO veiculo = new VeiculoDAO();
            
            while(rs.next()){
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"), 
                        rs.getString("nome_aluno"), 
                        rs.getString("telefone_aluno"), 
                        rs.getString("endereco_aluno"), 
                        rs.getString("data_nasc_aluno"), 
                        rs.getString("nome_responsavel"), 
                        rs.getString("cpf_responsavel"),
                        contato.listarContatos(rs.getInt("id_aluno")),
                        veiculo.listarVeiculo(rs.getInt("id_veiculo_aluno")),
                        escola.listarEscola(rs.getInt("id_escola_aluno")),                          
                        rs.getString("periodo_aluno"), 
                        rs.getString("turma_aluno"), 
                        rs.getString("horario_casa_ida"), 
                        rs.getString("horario_escola_ida"), 
                        rs.getString("horario_escola_volta"), 
                        rs.getString("horario_casa_volta"), 
                        rs.getFloat("valor_mensalidade_aluno"), 
                        rs.getInt("vencimento_aluno"), 
                        rs.getString("data_inicio_aluno"), 
                        rs.getString("data_fim_aluno"), 
                        rs.getInt("status_aluno")
                );
               
                lista.add(aluno);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os Alunos ativos. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public List<Aluno> listarAlunosMarcandoPagamentosMes(){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> lista = new ArrayList();
        try{
            
            DateFormat fmt = new SimpleDateFormat("yyyy-MM"); 
            String data = fmt.format(new Date());
            
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAlunosMarcandoPagamentoMes);
            stmt.setString(1, data + "-01");
            
            rs = stmt.executeQuery();
            
            ContatoDAO contato = new ContatoDAO();
            EscolaDAO escola = new EscolaDAO();
            VeiculoDAO veiculo = new VeiculoDAO();
            
            while(rs.next()){
                
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"), 
                        rs.getString("nome_aluno"), 
                        rs.getString("telefone_aluno"), 
                        rs.getString("endereco_aluno"), 
                        rs.getString("data_nasc_aluno"), 
                        rs.getString("nome_responsavel"), 
                        rs.getString("cpf_responsavel"),
                        contato.listarContatos(rs.getInt("id_aluno")),
                        veiculo.listarVeiculo(rs.getInt("id_veiculo_aluno")),
                        escola.listarEscola(rs.getInt("id_escola_aluno")),                          
                        rs.getString("periodo_aluno"), 
                        rs.getString("turma_aluno"), 
                        rs.getString("horario_casa_ida"), 
                        rs.getString("horario_escola_ida"), 
                        rs.getString("horario_escola_volta"), 
                        rs.getString("horario_casa_volta"), 
                        rs.getFloat("valor_mensalidade_aluno"), 
                        rs.getInt("vencimento_aluno"), 
                        rs.getString("data_inicio_aluno"), 
                        rs.getString("data_fim_aluno"), 
                        rs.getInt("pago")
                );
                
                lista.add(aluno);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os Alunos junto com a confirmação dos pagamentos em dia. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public List<Aluno> listarAlunos(String filtro) {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> lista = new ArrayList();
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAlunosFiltro);
            filtro = "%" + filtro + "%";
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            
            ContatoDAO contato = new ContatoDAO();
            EscolaDAO escola = new EscolaDAO();
            VeiculoDAO veiculo = new VeiculoDAO();
            
            while(rs.next()){
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"), 
                        rs.getString("nome_aluno"), 
                        rs.getString("telefone_aluno"), 
                        rs.getString("endereco_aluno"), 
                        rs.getString("data_nasc_aluno"), 
                        rs.getString("nome_responsavel"), 
                        rs.getString("cpf_responsavel"),
                        contato.listarContatos(rs.getInt("id_aluno")),
                        veiculo.listarVeiculo(rs.getInt("id_veiculo_aluno")),
                        escola.listarEscola(rs.getInt("id_escola_aluno")),                          
                        rs.getString("periodo_aluno"), 
                        rs.getString("turma_aluno"), 
                        rs.getString("horario_casa_ida"), 
                        rs.getString("horario_escola_ida"), 
                        rs.getString("horario_escola_volta"), 
                        rs.getString("horario_casa_volta"), 
                        rs.getFloat("valor_mensalidade_aluno"), 
                        rs.getInt("vencimento_aluno"), 
                        rs.getString("data_inicio_aluno"), 
                        rs.getString("data_fim_aluno"), 
                        rs.getInt("status_aluno")
                );
               
                lista.add(aluno);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar os Alunos usuando o filtro = " + filtro + ". "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public Aluno listarAluno(int id) {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectAluno);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            ContatoDAO contato = new ContatoDAO();
            EscolaDAO escola = new EscolaDAO();
            VeiculoDAO veiculo = new VeiculoDAO();
            
            Aluno aluno = null;
            
            while(rs.next()){
                
                aluno = new Aluno(
                        rs.getInt("id_aluno"), 
                        rs.getString("nome_aluno"), 
                        rs.getString("telefone_aluno"), 
                        rs.getString("endereco_aluno"), 
                        rs.getString("data_nasc_aluno"), 
                        rs.getString("nome_responsavel"), 
                        rs.getString("cpf_responsavel"),
                        contato.listarContatos(rs.getInt("id_aluno")),
                        veiculo.listarVeiculo(rs.getInt("id_veiculo_aluno")),
                        escola.listarEscola(rs.getInt("id_escola_aluno")),                          
                        rs.getString("periodo_aluno"), 
                        rs.getString("turma_aluno"), 
                        rs.getString("horario_casa_ida"), 
                        rs.getString("horario_escola_ida"), 
                        rs.getString("horario_escola_volta"), 
                        rs.getString("horario_casa_volta"), 
                        rs.getFloat("valor_mensalidade_aluno"), 
                        rs.getInt("vencimento_aluno"), 
                        rs.getString("data_inicio_aluno"), 
                        rs.getString("data_fim_aluno"), 
                        rs.getInt("status_aluno")
                );


            }
            return aluno;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar um Aluno. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void desativarAluno(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(desativarAluno);
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
            String data = fmt.format(new Date()); 
            stmt.setString(1, data);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar um Aluno. "+ex.getMessage());
        }
        finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public void ativarAluno(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(ativarAluno); 
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao ativar um Aluno. "+ex.getMessage());
        }
        finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    
    public Aluno cadastraAluno(Aluno a)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(insertAluno, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getTelefone());
            stmt.setString(3, a.getEndereco());
            stmt.setString(4, a.getNomeResponsavel());
            stmt.setString(5, a.getCpfResponsavel());
            stmt.setInt(6, a.getEscola().getId());            
            stmt.setString(7, a.getPeriodo());
            stmt.setString(8, a.getTurma());
            stmt.setString(9, a.getHorarioCasaIda());
            stmt.setString(10, a.getHorarioEscolaIda());
            stmt.setString(11, a.getHorarioEscolaVolta());
            stmt.setString(12, a.getHorarioCasaVolta());
            stmt.setFloat(13, a.getMensalidade());
            stmt.setInt(14, a.getVencimento());
            stmt.setString(15, a.getDataInicio());
            stmt.setString(16, a.getDataNascimento());
            stmt.setInt(17, a.getVeiculo().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            a.setId(id);
            ContatoDAO contato = new ContatoDAO();
            for(Contato c : a.getContatos()){
               contato.registrarContato(c, id);
            }
            return a;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao registrar um Aluno. "+ex.getMessage());
        } finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    
    public void updateAluno(Aluno a)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(updateAluno);
            ContatoDAO dao = new ContatoDAO();
            dao.excluirContato(a.getId());
            for(Contato c: a.getContatos()){
                dao.registrarContato(c, a.getId());
            }
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getTelefone());
            stmt.setString(3, a.getEndereco());
            stmt.setString(4, a.getNomeResponsavel());
            stmt.setString(5, a.getCpfResponsavel());
            stmt.setInt(6, a.getEscola().getId());            
            stmt.setString(7, a.getPeriodo());
            stmt.setString(8, a.getTurma());
            stmt.setString(9, a.getHorarioCasaIda());
            stmt.setString(10, a.getHorarioEscolaIda());
            stmt.setString(11, a.getHorarioEscolaVolta());
            stmt.setString(12, a.getHorarioCasaVolta());
            stmt.setFloat(13, a.getMensalidade());
            stmt.setInt(14, a.getVencimento());
            stmt.setString(15, a.getDataNascimento());
            stmt.setInt(16, a.getVeiculo().getId());
            stmt.setInt(17, a.getId());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um Aluno. "+ex.getMessage());
        } finally{
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }
    
    public double totalMensalidades(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalMensalidades);
            rs = stmt.executeQuery();
            double total = 0.0;
            while(rs.next()){
                total = rs.getDouble("soma");
            }
            return total;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total das mensalidades. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }


    public double totalMensalidadesVeiculo(Veiculo v) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalMensalidadesVeiculo);
            stmt.setInt(1, v.getId());
            rs = stmt.executeQuery();
            double total = 0.0;
            while(rs.next()){
                total = rs.getDouble("soma");
            }
            return total;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar calcular o total das mensalidades dos Alunos de um Veículo. "+ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

      public int totalAlunosNovosPeriodo(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalAlunosNovosPeriodo);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            int total = 0;
            while(rs.next()){
                total = rs.getInt("total");
            }
            return total;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total de novos alunos entre " + inicio + " e " + fim + " ." +ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }

    public int totalAlunosCanceladosPeriodo(String inicio, String fim) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(selectTotalAlunosCanceladosPeriodo);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            rs = stmt.executeQuery();
            int total = 0;
            while(rs.next()){
                total = rs.getInt("total");
            }
            return total;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao calcular o total de alunos cancelados entre . " + inicio + " e " + fim + " ." +ex.getMessage());
        }finally{
            if(rs != null){try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar Result Set. Ex="+ex.getMessage());}}
            if(stmt != null){try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar o Prepared Statement. Ex="+ex.getMessage());}}
            if(con != null){try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar a Conexão. Ex="+ex.getMessage());}}
        }
    }


    
}
