
package servlets;

import bd.ConnectionFactory;
import beans.LoginBean;
import facade.RelatoriosFacade;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperRunManager;


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
            request.setAttribute("red", "Relatorios");
            rd.forward(request, response);
        }
        else{
            
            try{
                
                String tipo = (String) request.getParameter("tipo");
                if(tipo != null){

                    switch(tipo){
                        case "alunos":
                            String mes_ref = (String)request.getParameter("mes_ref") + "-01";
                            if(mes_ref.isEmpty()){
                                mes_ref = getMesAno() + "-01";
                            }
                            String host_alunos = "http://localhost:8080/Gestor%20Escolar/Jasper";
                            String jasper_alunos = "/Alunos.jasper";
                            URL jasperURL_alunos = new URL(host_alunos + jasper_alunos);
                      
                            HashMap params_alunos = new HashMap();
                            params_alunos.put("mes_ref", mes_ref);
                            
                            byte[] bytes_alunos = JasperRunManager.runReportToPdf(jasperURL_alunos.openStream(), params_alunos, ConnectionFactory.getConnection());

                            if (bytes_alunos != null) {
                                response.setContentType("application/pdf");
                                OutputStream ops_alunos = response.getOutputStream();
                                ops_alunos.write(bytes_alunos);
                            }
                            break;
                        case "entradas":
                            String data_inicio_entradas = (String)request.getParameter("data_inicio");
                            String data_fim_entradas = (String)request.getParameter("data_fim");
                            if(data_inicio_entradas.isEmpty()){
                                if(data_fim_entradas.isEmpty()){
                                    data_fim_entradas = getData();
                                }
                                data_inicio_entradas = "2000-01-01";
                            }
                            String host_entradas = "http://localhost:8080/Gestor%20Escolar/Jasper";
                            String jasper__entradas = "/Entradas.jasper";
                            URL jasperURL = new URL(host_entradas + jasper__entradas);
                      
                            HashMap params_entradas = new HashMap();
                            params_entradas.put("data_inicio", data_inicio_entradas);
                            params_entradas.put("data_fim", data_fim_entradas);
                            
                            byte[] bytes_entradas = JasperRunManager.runReportToPdf(jasperURL.openStream(), params_entradas, ConnectionFactory.getConnection());

                            if (bytes_entradas != null) {
                                response.setContentType("application/pdf");
                                OutputStream ops_entradas = response.getOutputStream();
                                ops_entradas.write(bytes_entradas);
                            }
                            break;
                        case "saidas":
                            String data_inicio_saidas = (String)request.getParameter("data_inicio");
                            String data_fim_saidas = (String)request.getParameter("data_fim");
                            if(data_inicio_saidas.isEmpty()){
                                if(data_fim_saidas.isEmpty()){
                                    data_fim_saidas = getData();
                                }
                                data_inicio_saidas = "2000-01-01";
                            }
                            String host_saidas = "http://localhost:8080/Gestor%20Escolar/Jasper";
                            String jasper__saidas = "/Saidas.jasper";
                            URL jasperURL_saidas = new URL(host_saidas + jasper__saidas);
                      
                            HashMap params_saidas = new HashMap();
                            params_saidas.put("data_inicio", data_inicio_saidas);
                            params_saidas.put("data_fim", data_fim_saidas);
                            
                            byte[] bytes_saidas = JasperRunManager.runReportToPdf(jasperURL_saidas.openStream(), params_saidas, ConnectionFactory.getConnection());

                            if (bytes_saidas != null) {
                                response.setContentType("application/pdf");
                                OutputStream ops_saidas = response.getOutputStream();
                                ops_saidas.write(bytes_saidas);
                            }
                            break;
                        default:
                            request.setAttribute("entradas", RelatoriosFacade.getEntradas());
                            request.setAttribute("saidas", RelatoriosFacade.getSaidas());
                            request.setAttribute("ano", RelatoriosFacade.getAno());
                            request.setAttribute("novos", RelatoriosFacade.getNovosAlunos());
                            request.setAttribute("cancelados", RelatoriosFacade.getAlunosCancelados());
                            request.setAttribute("km", RelatoriosFacade.getKilometragem());
                            request.setAttribute("data", getData());
                            RequestDispatcher rd5 = getServletContext().getRequestDispatcher("/relatorio.jsp");
                            rd5.forward(request, response);
                            break;
                    }
                }
                else {

                    request.setAttribute("entradas", RelatoriosFacade.getEntradas());
                    request.setAttribute("saidas", RelatoriosFacade.getSaidas());
                    request.setAttribute("ano", RelatoriosFacade.getAno());
                    request.setAttribute("novos", RelatoriosFacade.getNovosAlunos());
                    request.setAttribute("cancelados", RelatoriosFacade.getAlunosCancelados());
                    request.setAttribute("km", RelatoriosFacade.getKilometragem());
                    request.setAttribute("data", new Date());
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
    
    public static String getMesAno(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM"); 
        return fmt.format(new Date());
    }
}
