/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Abastecimento;
import beans.LoginBean;
import beans.Manutencao;
import beans.Motorista;
import beans.Veiculo;
import facade.AbastecimentosFacade;
import facade.ManutencaoFacade;
import facade.MotoristasFacade;
import facade.VeiculoFacade;
import java.io.IOException;
import java.util.Arrays;
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
@WebServlet(name = "Veiculos", urlPatterns = {"/Veiculos"})
public class Veiculos extends HttpServlet {

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
            request.setAttribute("red", "Veiculos");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
            rd.forward(request, response);

        }
        else{

            try {
                String action = (String) request.getParameter("action");
                if(action != null){

                    switch(action){
                        case "listar":
                            request.setAttribute("veiculos", VeiculoFacade.buscar());
                            RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/veiculoListar.jsp");
                            rd1.forward(request, response);
                            break;
                        case "form-novo":
                            RequestDispatcher rd2 = getServletContext().getRequestDispatcher("/veiculoNovo.jsp");
                            request.setAttribute("motoristas", MotoristasFacade.buscar());
                            rd2.forward(request, response);
                            break;
                        case "form-alterar":
                            int id_veiculo = Integer.parseInt(request.getParameter("id_veiculo"));
                            request.setAttribute("veiculo", VeiculoFacade.buscar(id_veiculo));
                            request.setAttribute("motoristas", MotoristasFacade.buscar());
                            RequestDispatcher rd3 = getServletContext().getRequestDispatcher("/veiculoAlterar.jsp");
                            rd3.forward(request, response);
                        case "novo":
                            String modelo_veiculo = (String)request.getParameter("modelo");
                            String cor_veiculo = (String)request.getParameter("cor");
                            String num_veiculo = (String)request.getParameter("numero");
                            String placa_veiculo = (String)request.getParameter("placa");
                            String lic_veiculo = (String)request.getParameter("licenca");
                            int cap_veiculo = Integer.parseInt(request.getParameter("capacidade"));
                            int id_motorista = Integer.parseInt(request.getParameter("motorista"));
                            Veiculo v1 = VeiculoFacade.inserir(new Veiculo(0, num_veiculo, placa_veiculo, lic_veiculo, cap_veiculo, modelo_veiculo, cor_veiculo, MotoristasFacade.buscar(id_motorista)));
                            response.sendRedirect("Veiculos?action=visualizar&id="+ v1.getId());
                            break;
                        case "alterar":
                            int id_veiculo_alterar = Integer.parseInt(request.getParameter("id_veiculo"));
                            Veiculo veiculo_alterar = VeiculoFacade.buscar(id_veiculo_alterar);                            
                            veiculo_alterar.setModelo((String)request.getParameter("modelo"));
                            veiculo_alterar.setCor((String)request.getParameter("cor"));
                            veiculo_alterar.setNumero((String)request.getParameter("numero"));
                            veiculo_alterar.setPlaca((String)request.getParameter("placa"));
                            veiculo_alterar.setLicenca((String)request.getParameter("licenca"));
                            veiculo_alterar.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
                            veiculo_alterar.setMotorista(MotoristasFacade.buscar(Integer.parseInt(request.getParameter("motorista"))));
                            VeiculoFacade.alterar(veiculo_alterar);
                            response.sendRedirect("Veiculos?action=visualizar&id="+ veiculo_alterar.getId());
                            break;
                         case "delete":
                            int id_veiculo_delete = Integer.parseInt(request.getParameter("id_veiculo"));
                            VeiculoFacade.deletar(id_veiculo_delete);
                            response.sendRedirect("Veiculos");
                            break;
                        case "visualizar":
                            int id_veiculo_visualizar = Integer.parseInt(request.getParameter("id"));
                            Veiculo v2 = VeiculoFacade.buscar(id_veiculo_visualizar);
                            List<Abastecimento> lista_abastecimentos = AbastecimentosFacade.listar(v2);
                            List<Manutencao> lista_manutencoes = ManutencaoFacade.buscar(v2);
                            int total_rodado = AbastecimentosFacade.totalRodado(v2);
                            double media = AbastecimentosFacade.mediaMes(v2);
                            request.setAttribute("veiculo", v2);
                            request.setAttribute("abastecimentos", lista_abastecimentos);
                            request.setAttribute("manutencoes", lista_manutencoes);
                            request.setAttribute("rodado", total_rodado);
                            request.setAttribute("media", media);
                            RequestDispatcher rd4 = getServletContext().getRequestDispatcher("/veiculoVisualizar.jsp");
                            rd4.forward(request, response);
                            break;
                        default:
                            request.setAttribute("veiculos", VeiculoFacade.buscar());
                            RequestDispatcher rd5 = getServletContext().getRequestDispatcher("/veiculoListar.jsp");
                            rd5.forward(request, response);
                    }
                }
                else {
                    request.setAttribute("veiculos", VeiculoFacade.buscar());
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/veiculoListar.jsp");
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
