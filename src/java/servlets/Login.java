package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bd.ConnectionFactory;
import beans.LoginBean;
import beans.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            if(autentica(usuario, senha))
            {
                Usuario user = getUsuario(usuario);
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(604800);
                LoginBean lb = new LoginBean(user.getId(), user.getNome());
                session.setAttribute("usuario", lb);
                String red = "Alunos";
                if(!request.getParameter("red").isEmpty()){
                    red = request.getParameter("red");
                }
                response.sendRedirect(red);
            }
            else
            {   
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "<strong> Erro! </strong>  Usuário/Senha inválidos.");
                rd.forward(request, response);
            }
            
        }catch(IOException | ServletException ex){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
            request.setAttribute("msg", ex.getMessage());
            request.setAttribute("sktrc", Arrays.toString(ex.getStackTrace()));
            rd.forward(request, response);
        }
        
    }
    
    private boolean autentica (String usuario, String senha)
    {
        Usuario autenticar = getUsuario(usuario);
        if(autenticar != null){
            return autenticar.getLogin().equalsIgnoreCase(usuario) && autenticar.getSenha().equals(senha);
        }
        else
        {
            return false;
        }
    }
    
    private Usuario getUsuario (String usuario)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login_usuario = ?");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();
            if(rs.next()){
                Usuario response = new Usuario(rs.getInt("id_usuario"), rs.getString("login_usuario"), rs.getString("senha_usuario"), rs.getString("nome_usuario"));
                return response;
            }
            else
            {
                return null;
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao procurar usuario. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());}
            try{stmt.close();}catch(SQLException ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());}               
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



