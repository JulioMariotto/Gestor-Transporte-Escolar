package servlets;


import beans.LoginBean;
import beans.Usuario;
import facade.UsuarioFacade;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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

        try {

            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            
            Usuario usuario = UsuarioFacade.buscar(login);
            if(usuario != null){
                if(usuario.getLogin().equalsIgnoreCase(login) && usuario.getSenha().equals(senha)){
                    
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(604800);
                    LoginBean lb = new LoginBean(usuario.getId(), usuario.getNome());
                    session.setAttribute("usuario", lb);
                    String red = "Alunos";
                    if(!request.getParameter("red").isEmpty()){
                        red = request.getParameter("red");
                    }
                    response.sendRedirect(red);
                }
                else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                    request.setAttribute("msg", "<strong> Erro! </strong>  Login/Senha inválidos.");
                    rd.forward(request, response);
                }
            }
            else
            {   
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "<strong> Erro! </strong>  Login/Senha inválidos.");
                rd.forward(request, response);
            }
            
        }catch(IOException | ServletException ex){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("msg", ex.getMessage());
            rd.forward(request, response);
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



