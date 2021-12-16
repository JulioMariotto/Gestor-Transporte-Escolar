package servlets;

import beans.Despesa;
import java.io.IOException;
import com.google.gson.Gson;
import facade.DespesaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ListaDespesasAJAX", urlPatterns = {"/ListaDespesasAJAX"})
public class ListaDespesasAJAX extends HttpServlet {

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
        
        List<Despesa> lista = new ArrayList();
        
        switch(tipo){
            case "todas":
                lista = DespesaFacade.buscarTodas(inicio, fim);
                break;
            case "abastecimento":
                lista = DespesaFacade.buscarDespesasAbastecimento(inicio, fim);
                break;
            case "manutencao":
                lista = DespesaFacade.buscarDespesasManutencao(inicio, fim);
                break;
            case "outras":
                lista = DespesaFacade.buscarOutrasDespesas(inicio, fim);
                break;
            default:
                lista = DespesaFacade.buscarTodas(inicio, fim);
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
