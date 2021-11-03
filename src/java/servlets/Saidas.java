/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Abastecimento;
import beans.Dispesa;
import beans.LoginBean;
import beans.Manutencao;
import facade.AbastecimentosFacade;
import facade.DispesaFacade;
import facade.ManutencaoFacade;
import facade.VeiculoFacade;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "Saidas", urlPatterns = {"/Saidas"})
public class Saidas extends HttpServlet {

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
                request.setAttribute("red", "Saidas");
                rd.forward(request, response);
                
            }
            else{
                
                try {
                    String action = (String) request.getParameter("action");
                    if(action != null){

                        switch(action){
                            case "listar":
                                String data_inicio = (String)request.getParameter("data_inicio");
                                String data_fim = (String)request.getParameter("data_fim");
                                if(data_inicio == null){
                                    data_inicio = "2000-01-01";
                                }
                                if(data_fim == null){
                                    data_fim = DispesaFacade.getData();
                                }
                                request.setAttribute("titulo", "Dispesas");
                                request.setAttribute("dispesas", DispesaFacade.buscarTodas(data_inicio, data_fim));
                                request.setAttribute("total", DispesaFacade.totalDispesas(data_inicio, data_fim));
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/dispesasListar.jsp");
                                rd.forward(request, response);
                                break;
                            case "form-novo":
                                request.setAttribute("data", new Date());
                                request.setAttribute("veiculos", VeiculoFacade.buscar());
                                RequestDispatcher rd5 = getServletContext().getRequestDispatcher("/dispesaNovo.jsp");
                                rd5.forward(request, response);
                                break;
                            case "novo":
                                String tipo_novo = request.getParameter("tipo");
                                String valor_format = (String)request.getParameter("valor");
                                valor_format = valor_format.replace(".", "");
                                valor_format = valor_format.replace(",", ".");
                                double valor_novo = Double.parseDouble(valor_format);
                                String data_novo = request.getParameter("data");
                                String descricao_novo = request.getParameter("descricao");
                                Dispesa d = DispesaFacade.registrar(new Dispesa(0, valor_novo, data_novo, descricao_novo));
                                switch(tipo_novo){
                                    case "abastecimento":
                                        int id_veiculo = Integer.parseInt(request.getParameter("id_veiculo"));
                                        int km = Integer.parseInt(request.getParameter("kilometragem"));
                                        double litros = Double.parseDouble(request.getParameter("litros"));
                                        String posto = request.getParameter("posto");
                                        AbastecimentosFacade.registrar(new Abastecimento(VeiculoFacade.buscar(id_veiculo), d, km, litros, posto));
                                        break;
                                    case "manutencao":
                                        int id_veiculo1 = Integer.parseInt(request.getParameter("id_veiculo"));
                                        String problema = request.getParameter("problema");
                                        String km1 = request.getParameter("kilometragem");
                                        ManutencaoFacade.registrar(new Manutencao(VeiculoFacade.buscar(id_veiculo1), d, problema, km1));
                                        break;
                                }
                                response.sendRedirect("Saidas");
                                break;
                            case "delete":
                                int id_dispesa_delete = Integer.parseInt(request.getParameter("id_dispesa"));
                                DispesaFacade.deletar(id_dispesa_delete);
                                response.sendRedirect("Saidas");
                                break;
                             case "visualizar":
                                int id_visualizar = Integer.parseInt(request.getParameter("id_dispesa"));
                                Dispesa d8 = DispesaFacade.buscar(id_visualizar);
                                request.setAttribute("dispesa", d8);
                                request.setAttribute("manutencao", ManutencaoFacade.buscar(d8.getId()));
                                request.setAttribute("abastecimento", AbastecimentosFacade.listar(d8.getId()));
                                RequestDispatcher rd8 = getServletContext().getRequestDispatcher("/dispesaVisualizar.jsp");
                                rd8.forward(request, response);
                                break;    
                            default:
                                request.setAttribute("titulo", "Dispesas");
                                request.setAttribute("dispesas", DispesaFacade.buscarTodas());
                                request.setAttribute("total", DispesaFacade.totalDispesas());
                                RequestDispatcher rd9 = getServletContext().getRequestDispatcher("/dispesasListar.jsp");
                                rd9.forward(request, response);
                        }                        
                        
                    }
                    else {
                        request.setAttribute("titulo", "Dispesas");
                        request.setAttribute("dispesas", DispesaFacade.buscarTodas());
                        request.setAttribute("total", DispesaFacade.totalDispesas());
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/dispesasListar.jsp");
                        rd.forward(request, response);
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

    
    public static String converteStringData(String data){
        return data.substring(8) + "/" + data.substring(5, 7) + "/" + data.substring(0, 4);
        //return data;
    }
    
    public static String convertToSqlData(String data){
        /*if(data.length() > 1){
            data =  data.substring(6) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
        }*/
        return data;
    }
    
    public static String getMes(int mes){
        
        String mesf = "";
        switch(mes) {
            case 1:  mesf = "Janeiro"; break;
            case 2:  mesf = "Fevereiro"; break;
            case 3:  mesf = "Março"; break;
            case 4:  mesf = "Abril"; break;
            case 5:  mesf = "Maio"; break;
            case 6:  mesf = "Junho"; break;
            case 7:  mesf = "Julho"; break;
            case 8:  mesf = "Agosto"; break;
            case 9:  mesf = "Setembro"; break;
            case 10: mesf = "Outubro"; break;
            case 11: mesf = "Novembro"; break;
            case 12: mesf = "Dezembro"; break;
 	}
        return mesf;
    }
    
    /*
                                if("null".equals(request.getParameter("id_motorista")))
                                {
                                    request.setAttribute("rodagens", ManutencaoFacade.listarFiltro(data_inicio, data_fim));
                                    request.setAttribute("motoristas", MotoristasFacade.buscar());
                                    request.setAttribute("inicio_mes", data_inicio);
                                    request.setAttribute("fim_mes", data_fim);
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/rodagensListar.jsp");
                                    rd.forward(request, response);
                                }
                                else{
                                    int id_motorista = Integer.parseInt(request.getParameter("id_motorista"));
                                    request.setAttribute("rodagens", ManutencaoFacade.listarFiltroMotorista(data_inicio, data_fim, id_motorista));
                                    request.setAttribute("motoristas", MotoristasFacade.buscar());
                                    request.setAttribute("inicio_mes", data_inicio);
                                    request.setAttribute("fim_mes", data_fim);
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/rodagensListar.jsp");
                                    rd.forward(request, response);
    
                                request.setAttribute("abastecimentos", AbastecimentosFacade.listar());
                                request.setAttribute("media", String.format("%.2f", AbastecimentosFacade.totalAbastecidoMes()));
                                request.setAttribute("total", DispesaFacade.buscarDispesasAbastecimento());
                                request.setAttribute("total_abastecido", String.format("%.2f", AbastecimentosFacade.totalAbastecidoMes()));
                                RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/abastecimentosListar.jsp");
                                rd1.forward(request, response);                            
    
    }*/
}
