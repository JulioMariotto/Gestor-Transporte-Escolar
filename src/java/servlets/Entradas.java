/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Aluno;
import beans.LoginBean;
import beans.Pagamento;
import facade.AlunosFacade;
import facade.DispesaFacade;
import facade.PagamentosFacade;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static servlets.Saidas.converteStringData;
import static servlets.Saidas.getMes;

/**
 *
 * @author julio
 */
@WebServlet(name = "Entradas", urlPatterns = {"/Entradas"})
public class Entradas extends HttpServlet {

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
                request.setAttribute("red", "Entradas");
                request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
                rd.forward(request, response);
                
            }
            else{
                
                try {
                    String action = (String) request.getParameter("action");
                    if(action != null){

                        switch(action){
                            case "listar":
                                request.setAttribute("pagamentos", PagamentosFacade.listar());
                                request.setAttribute("total", PagamentosFacade.totalJaRecebido());
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pagamentosListar.jsp");
                                rd.forward(request, response);
                                break;
                            case "listar-todos":
                                request.setAttribute("pagamentos", PagamentosFacade.listarTodos());
                                request.setAttribute("total", PagamentosFacade.totalJaRecebido());
                                RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/pagamentosListar.jsp");
                                rd1.forward(request, response);
                                break;    
                            case "form-novo":
                                request.setAttribute("alunos", AlunosFacade.buscar());
                                request.setAttribute("data", new Date());
                                RequestDispatcher rd2 = getServletContext().getRequestDispatcher("/pagamentoNovo.jsp");
                                rd2.forward(request, response);
                                break;
                            case "novo":
                                int id_aluno = Integer.parseInt(request.getParameter("aluno"));
                                String mes_ref = (String)request.getParameter("mes") + "-01";
                                String valor_format = (String)request.getParameter("valor");
                                valor_format = valor_format.replace(".", "");
                                valor_format = valor_format.replace(",", ".");
                                double valor = Double.parseDouble(valor_format);
                                String data = (String)request.getParameter("data");
                                Aluno a = AlunosFacade.buscar(id_aluno);
                                Pagamento p = new Pagamento(0, a, valor, mes_ref, data);
                                PagamentosFacade.registrar(p);
                                response.sendRedirect("Entradas");
                                break;
                             case "delete":
                                int id_pagamento_delete = Integer.parseInt(request.getParameter("id"));
                                PagamentosFacade.remover(id_pagamento_delete);
                                response.sendRedirect("Entradas");
                                break;
                            default:
                                request.setAttribute("pagamentos", PagamentosFacade.listar());
                                request.setAttribute("total", PagamentosFacade.totalJaRecebido());
                                RequestDispatcher rd4 = getServletContext().getRequestDispatcher("/pagamentosListar.jsp");
                                rd4.forward(request, response);
                        }
                    }
                    else {
                        request.setAttribute("pagamentos", PagamentosFacade.listarTodos());
                        request.setAttribute("total", PagamentosFacade.totalJaRecebido());
                        RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/pagamentosListar.jsp");
                        rd1.forward(request, response);
                    }
                }
                catch(IOException | NumberFormatException | ServletException ex) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    request.setAttribute("msg", ex.getMessage());
                    request.setAttribute("sktrc", Arrays.toString(ex.getStackTrace()));
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
