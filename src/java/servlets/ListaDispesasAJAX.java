/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.Dispesa;
import beans.Pagamento;
import java.io.IOException;
import com.google.gson.Gson;
import facade.DispesaFacade;
import facade.PagamentosFacade;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author julio
 */
@WebServlet(name = "ListaDispesasAJAX", urlPatterns = {"/ListaDispesasAJAX"})
public class ListaDispesasAJAX extends HttpServlet {

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
        
        String inicio = (String)request.getParameter("inicio");
        String fim = (String)request.getParameter("fim");
        String tipo = (String)request.getParameter("tipo");
        
        List<Dispesa> lista = new ArrayList();
        
        switch(tipo){
            case "todas":
                lista = DispesaFacade.buscarTodas(inicio, fim);
                break;
            case "abastecimento":
                lista = DispesaFacade.buscarDispesasAbastecimento(inicio, fim);
                break;
            case "manutencao":
                lista = DispesaFacade.buscarDispesasManutencao(inicio, fim);
                break;
            case "outras":
                lista = DispesaFacade.buscarOutrasDispesas(inicio, fim);
                break;
            default:
                lista = DispesaFacade.buscarTodas(inicio, fim);
                break;
        }
            
        
        String jsonObject = new Gson().toJson(lista);
        
        response.setContentType("application/json");
        
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject); 
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
