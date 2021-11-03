/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Aluno;
import beans.Contato;
import beans.LoginBean;
import beans.Escola;
import beans.Veiculo;
import facade.AlunosFacade;
import facade.EscolaFacade;
import facade.PagamentosFacade;
import facade.VeiculoFacade;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author julio
 */
@WebServlet(name = "ClientesServlet", urlPatterns = {"/Alunos"})
public class Alunos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        LoginBean login = (LoginBean)session.getAttribute("usuario");
        if(login == null){
            
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
            request.setAttribute("red", "Alunos");
            rd.forward(request, response);
        }
        else{
            
            try{
                
                String action = (String) request.getParameter("action");
                if(action != null){

                    switch(action){
                        case "listar":
                            List<Aluno> lista = AlunosFacade.buscarAlunosAtivos();
                            request.setAttribute("alunos", lista);
                            request.setAttribute("totalReceber", AlunosFacade.totalAReceber());
                            request.setAttribute("totalRecebido", PagamentosFacade.totalRecebido());
                            request.setAttribute("totalRecebidoMes", PagamentosFacade.totalRecebidoMes());
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/alunosListar.jsp");
                            rd.forward(request, response);
                            break;
                        case "listar-todos":
                            List<Aluno> listaTodos = AlunosFacade.buscar();
                            request.setAttribute("alunos", listaTodos);
                            request.setAttribute("totalReceber", AlunosFacade.totalAReceber());
                            request.setAttribute("totalRecebido", PagamentosFacade.totalRecebido());
                            request.setAttribute("totalRecebidoMes", PagamentosFacade.totalRecebidoMes());
                            RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/alunosListar.jsp");
                            rd1.forward(request, response);
                            break;    
                        case "visualizar":
                            int id_visualizar = Integer.parseInt(request.getParameter("id"));
                            Aluno a1 = AlunosFacade.buscar(id_visualizar);
                            request.setAttribute("aluno", a1);
                            request.setAttribute("pagamentos", PagamentosFacade.listar(a1.getId()));
                            RequestDispatcher rd2 = getServletContext().getRequestDispatcher("/alunosVisualizar.jsp");
                            rd2.forward(request, response);
                            break;
                        case "form-alterar":
                            int id_form_alterar = Integer.parseInt(request.getParameter("id"));
                            Aluno a2 = AlunosFacade.buscar(id_form_alterar);
                            request.setAttribute("aluno", a2);
                            List<Escola> escolas_alt = EscolaFacade.listar();
                            request.setAttribute("escolas", escolas_alt);
                            List<Veiculo> veiculos_alt = VeiculoFacade.buscar();
                            request.setAttribute("veiculos", veiculos_alt);
                            RequestDispatcher rd3 = getServletContext().getRequestDispatcher("/alunoAlterar.jsp");
                            rd3.forward(request, response);
                            break;    
                        case "alterar":
                            int id_alt = Integer.parseInt(request.getParameter("id"));
                            String nome_alt = (String)request.getParameter("nome");
                            String telefone_alt = (String)request.getParameter("telefone");
                            String endereco_alt = (String)request.getParameter("endereco");
                            String data_alt = (String)request.getParameter("data");
                            
                            String[] nome_contatos_alt = request.getParameterValues("nome_contato");
                            String[] telefone_contatos_alt = request.getParameterValues("telefone_contato");
                            String[] parentescos_alt = request.getParameterValues("parentesco");
                            List<Contato> lista_c_alt = new ArrayList();
                            if(nome_contatos_alt != null){
                                
                                for(int i=0;nome_contatos_alt.length > i; i++){
                                    lista_c_alt.add(new Contato(0, nome_contatos_alt[i], telefone_contatos_alt[i], parentescos_alt[i]));
                                }
                            }
                            
                            int escola_alt_id = Integer.parseInt((String)request.getParameter("escola"));
                            Escola escola_alt = EscolaFacade.buscar(escola_alt_id);
                            String periodo_alt = (String)request.getParameter("periodo");
                            String turma_alt = (String)request.getParameter("turma");
                            int veiculo_alt_id = Integer.parseInt((String)request.getParameter("veiculo"));
                            Veiculo veiculo_alt = VeiculoFacade.buscar(veiculo_alt_id);
                            
                            String horario_casa_ida_alt = (String)request.getParameter("horario_casa_ida");
                            String horario_escola_ida_alt = (String)request.getParameter("horario_escola_ida");
                            String horario_escola_volta_alt = (String)request.getParameter("horario_escola_volta");
                            String horario_casa_volta_alt = (String)request.getParameter("horario_casa_volta");
                            
                            String nome_responsavel_alt = (String)request.getParameter("nome_responsavel");
                            String cpf_responsavel_alt = (String)request.getParameter("cpf_responsavel");
                            
                           
                            Float mensalidade_alt = Float.parseFloat((String)request.getParameter("mensalidade").replace(',', '.'));
                            int vencimento_alt = Integer.parseInt((String)request.getParameter("vencimento"));
                            
                            SimpleDateFormat fmt_alt = new SimpleDateFormat("yyyy-MM-dd"); 
                            String data_fmt = fmt_alt.format(new Date()); 
            
                            Aluno alt = new Aluno(id_alt, nome_alt, telefone_alt, endereco_alt, data_alt, nome_responsavel_alt, cpf_responsavel_alt, lista_c_alt, veiculo_alt, escola_alt, periodo_alt, turma_alt, horario_casa_ida_alt, horario_escola_ida_alt, horario_escola_volta_alt, horario_casa_volta_alt, mensalidade_alt, vencimento_alt, data_fmt, "0000-00-00", 1);
                            
                            AlunosFacade.alterar(alt);
                            response.sendRedirect("Alunos?action=visualizar&id="+alt.getId());
                            break;
                        case "delete":
                            int id_deletar = Integer.parseInt(request.getParameter("id"));
                            AlunosFacade.deletar(id_deletar);
                            response.sendRedirect("Alunos");
                            break;
                        case "form-novo":
                            List<Escola> escolas = EscolaFacade.listar();
                            List<Veiculo> veiculos = VeiculoFacade.buscar();
                            request.setAttribute("escolas", escolas);
                            request.setAttribute("veiculos", veiculos);
                            RequestDispatcher rd4 = getServletContext().getRequestDispatcher("/alunoNovo.jsp");
                            rd4.forward(request, response);
                            break;
                        case "novo":
                            String nome_novo = (String)request.getParameter("nome");
                            String telefone_novo = (String)request.getParameter("telefone");
                            String endereco_novo = (String)request.getParameter("endereco");
                            String data_novo = (String)request.getParameter("data");
                            
                            String[] nome_contatos_novo = request.getParameterValues("nome_contato");
                            String[] telefone_contatos_novo = request.getParameterValues("telefone_contato");
                            String[] parentescos_novo = request.getParameterValues("parentesco");
                            List<Contato> lista_c = new ArrayList();
                            if(nome_contatos_novo != null){
                                
                                for(int i=0;nome_contatos_novo.length > i; i++){
                                    lista_c.add(new Contato(0, nome_contatos_novo[i], telefone_contatos_novo[i], parentescos_novo[i]));
                                }
                            }
                            
                            int escola_novo_id = Integer.parseInt((String)request.getParameter("escola"));
                            Escola escola_novo = EscolaFacade.buscar(escola_novo_id);
                            String periodo_novo = (String)request.getParameter("periodo");
                            String turma_novo = (String)request.getParameter("turma");
                            int veiculo_novo_id = Integer.parseInt((String)request.getParameter("veiculo"));
                            Veiculo veiculo_novo = VeiculoFacade.buscar(veiculo_novo_id);
                            
                            String horario_casa_ida_novo = (String)request.getParameter("horario_casa_ida");
                            String horario_escola_ida_novo = (String)request.getParameter("horario_escola_ida");
                            String horario_escola_volta_novo = (String)request.getParameter("horario_escola_volta");
                            String horario_casa_volta_novo = (String)request.getParameter("horario_casa_volta");
                            
                            String nome_responsavel_novo = (String)request.getParameter("nome_responsavel");
                            String cpf_responsavel_novo = (String)request.getParameter("cpf_responsavel");
                            
                           
                            Float mensalidade_novo = Float.parseFloat((String)request.getParameter("mensalidade").replace(',', '.'));
                            int vencimento_novo = Integer.parseInt((String)request.getParameter("vencimento"));
                            
                            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
                            String data = fmt.format(new Date()); 
            
                            Aluno novo = new Aluno(0, nome_novo, telefone_novo, endereco_novo, data_novo, nome_responsavel_novo, cpf_responsavel_novo, lista_c, veiculo_novo, escola_novo, periodo_novo, turma_novo, horario_casa_ida_novo, horario_escola_ida_novo, horario_escola_volta_novo, horario_casa_volta_novo, mensalidade_novo, vencimento_novo, data, "0000-00-00", 1);
                            novo = AlunosFacade.inserir(novo);
                            response.sendRedirect("Alunos?action=visualizar&id="+novo.getId());
                            break;
                        default:
                            List<Aluno> listagem = AlunosFacade.buscarAlunosAtivos();
                            request.setAttribute("alunos", listagem);
                            request.setAttribute("totalReceber", AlunosFacade.totalAReceber());
                            request.setAttribute("totalRecebido", PagamentosFacade.totalRecebido());
                            request.setAttribute("totalRecebidoMes", PagamentosFacade.totalRecebidoMes());
                            RequestDispatcher rd5 = getServletContext().getRequestDispatcher("/alunosListar.jsp");
                            rd5.forward(request, response);
                            break;
                    }
                }
                else {

                    List<Aluno> lista = AlunosFacade.buscarAlunosAtivos();
                    request.setAttribute("alunos", lista);
                    request.setAttribute("totalReceber", AlunosFacade.totalAReceber());
                    request.setAttribute("totalRecebido", PagamentosFacade.totalRecebido());
                    request.setAttribute("totalRecebidoMes", PagamentosFacade.totalRecebidoMes());
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/alunosListar.jsp");
                    rd1.forward(request, response);
                }
            }
            catch(Exception ex) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", ex.getMessage() + ex.toString());
                ex.printStackTrace();
                rd.forward(request, response);
            }                
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
