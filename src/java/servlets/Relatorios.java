/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Abastecimento;
import beans.Aluno;
import beans.LoginBean;
import beans.Pagamento;
import beans.Veiculo;
import facade.AlunosFacade;
import facade.RelatoriosFacade;
import facade.VeiculoFacade;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "Relatorios", urlPatterns = {"/Relatorios"})
public class Relatorios extends HttpServlet {

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
            rd.forward(request, response);
        }
        else{
            
            try{
                
                String tipo = (String) request.getParameter("tipo");
                if(tipo != null){

                    switch(tipo){
                        case "alunos":
                            String data_inicio_alunos = (String)request.getParameter("data_inicio");
                            String data_fim_alunos = (String)request.getParameter("data_fim");
                            if(data_inicio_alunos == null){
                                if(data_fim_alunos == null){
                                    data_fim_alunos = getData();
                                }
                                data_inicio_alunos = "2000-01-01";
                            }
                            List<Aluno> lista1 = RelatoriosFacade.gerarRelatorioAlunos(data_inicio_alunos, data_fim_alunos);
                            
                            // Falta gerar os relatorios na classe facede e criar a jsp para encaminhar.
                            request.setAttribute("alunos", lista1);
                            RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/relatorioAlunos.jsp");
                            rd1.forward(request, response);
                            break;
                        case "pagamentos":
                            String data_inicio_pagamentos = (String)request.getParameter("data_inicio");
                            String data_fim_pagamentos = (String)request.getParameter("data_fim");
                            if(data_inicio_pagamentos == null){
                                if(data_fim_pagamentos == null){
                                    data_fim_pagamentos = getData();
                                }
                                data_inicio_pagamentos = "2000-01-01";
                            }
                            List<Pagamento> lista2 = RelatoriosFacade.gerarRelatorioPagamentos(data_inicio_pagamentos, data_fim_pagamentos);
                            
                            // Falta gerar os relatorios na classe facede e criar a jsp para encaminhar.
                            request.setAttribute("pagamentos", lista2);
                            RequestDispatcher rd2 = getServletContext().getRequestDispatcher("/relatorioPagamentos.jsp");
                            rd2.forward(request, response);
                            break;
                        case "dispesas":
                            String data_inicio_abastecimentos = (String)request.getParameter("data_inicio");
                            String data_fim_abastecimentos = (String)request.getParameter("data_fim");
                            if(data_inicio_abastecimentos == null){
                                if(data_fim_abastecimentos == null){
                                    data_fim_abastecimentos = getData();
                                }
                                data_inicio_abastecimentos = "2000-01-01";
                            }
                            List<Abastecimento> lista3 = RelatoriosFacade.gerarRelatorioDispesas(data_inicio_abastecimentos, data_fim_abastecimentos);
                            
                            // Falta gerar os relatorios na classe facede e criar a jsp para encaminhar.
                            request.setAttribute("dispesas", lista3);
                            RequestDispatcher rd3 = getServletContext().getRequestDispatcher("/relatorio.jsp");
                            rd3.forward(request, response);
                            break;    
                        case "Veiculo":
                            String data_inicio_rodagens = (String)request.getParameter("data_inicio");
                            String data_fim_rodagens = (String)request.getParameter("data_fim");
                            int id_veiculo = Integer.parseInt(request.getParameter("id_veiculo"));
                            Veiculo v = VeiculoFacade.buscar(id_veiculo);
                            if(data_inicio_rodagens == null){
                                if(data_fim_rodagens == null){
                                    data_fim_rodagens = getData();
                                }
                                data_inicio_rodagens = "2000-01-01";
                            }
                            List<Abastecimento> lista4 = RelatoriosFacade.gerarRelatorioVeiculo(data_inicio_rodagens, data_fim_rodagens, v);
                            
                            // Falta gerar os relatorios na classe facede e criar a jsp para encaminhar.
                            request.setAttribute("veiculo", lista4);
                            RequestDispatcher rd4 = getServletContext().getRequestDispatcher("/relatorioAbastecimentos.jsp");
                            rd4.forward(request, response);
                            break;
                        default:
                            List<Aluno> listagem = AlunosFacade.buscar();
                            request.setAttribute("alunos", listagem);
                            RequestDispatcher rd5 = getServletContext().getRequestDispatcher("/relatorio.jsp");
                            rd5.forward(request, response);
                            break;
                    }
                }
                else {

                    List<Aluno> lista = AlunosFacade.buscar();
                    request.setAttribute("alunos", lista);
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/relatorio.jsp");
                    rd1.forward(request, response);
                }
            }
            catch(Exception ex) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", ex.getMessage());
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

        public static String getData(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        return fmt.format(new Date());
    }
}
