/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.LoginBean;
import beans.Motorista;
import beans.Veiculo;
import facade.MotoristasFacade;
import facade.VeiculoFacade;
import java.io.IOException;
import java.util.Arrays;
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
@WebServlet(name = "Motoristas", urlPatterns = {"/Motoristas"})
public class Motoristas extends HttpServlet {

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
            request.setAttribute("red", "Motoristas");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
            rd.forward(request, response);

        }
        else{

            try {
                String action = (String) request.getParameter("action");
                if(action != null){

                    switch(action){
                        case "listar":
                            request.setAttribute("motoristas", MotoristasFacade.buscar());
                            RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/motoristaListar.jsp");
                            rd1.forward(request, response);
                            break;
                        case "form-novo":
                            RequestDispatcher rd2 = getServletContext().getRequestDispatcher("/motoristaNovo.jsp");
                            rd2.forward(request, response);
                            break;
                        case "form-alterar":
                            int id_motorista = Integer.parseInt(request.getParameter("id_motorista"));
                            Motorista m = MotoristasFacade.buscar(id_motorista);
                            request.setAttribute("motorista", m);
                            RequestDispatcher rd3 = getServletContext().getRequestDispatcher("/motoristaAlterar.jsp");
                            rd3.forward(request, response);
                            break;
                        case "novo":
                            String nome_motorista = (String)request.getParameter("nome");
                            String cnh_motorista = (String)request.getParameter("cnh");
                            String telefone_motorista = (String)request.getParameter("telefone");
                            String endereco_motorista = (String)request.getParameter("endereco");
                            Motorista m_novo = MotoristasFacade.inserir(new Motorista(0, nome_motorista, cnh_motorista, telefone_motorista, endereco_motorista));
                            response.sendRedirect("Motoristas?action=visualizar&id="+ m_novo.getId());
                            break;
                        case "alterar":
                            int id_motorista_alterar = Integer.parseInt(request.getParameter("id_motorista"));
                            Motorista motorista_alterar = MotoristasFacade.buscar(id_motorista_alterar);
                            motorista_alterar.setNome((String)request.getParameter("nome"));
                            motorista_alterar.setCnh((String)request.getParameter("cnh"));
                            motorista_alterar.setTelefone((String)request.getParameter("telefone"));
                            motorista_alterar.setEndereco((String)request.getParameter("endereco"));
                            MotoristasFacade.alterar(motorista_alterar);
                            response.sendRedirect("Motoristas?action=visualizar&id="+ motorista_alterar.getId());
                            break;
                         case "delete":
                            int id_motorista_delete = Integer.parseInt(request.getParameter("id_motorista"));
                            MotoristasFacade.deletar(id_motorista_delete);
                            response.sendRedirect("Motoristas");
                            break;
                        case "visualizar":
                            int id_motorista_visualizar = Integer.parseInt(request.getParameter("id"));
                            Motorista motorista_visualizar = MotoristasFacade.buscar(id_motorista_visualizar);
                            Veiculo v = VeiculoFacade.buscarVeiculoMotorista(id_motorista_visualizar);
                            request.setAttribute("veiculo", v);
                            request.setAttribute("motorista", motorista_visualizar);
                            RequestDispatcher rd4 = getServletContext().getRequestDispatcher("/motoristaVisualizar.jsp");
                            rd4.forward(request, response);
                            break;
                        default:
                            request.setAttribute("motoristas", MotoristasFacade.buscar());
                            RequestDispatcher rd5 = getServletContext().getRequestDispatcher("/motoristaListar.jsp");
                            rd5.forward(request, response);
                    }
                }
                else {
                    request.setAttribute("motoristas", MotoristasFacade.buscar());
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/motoristaListar.jsp");
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
