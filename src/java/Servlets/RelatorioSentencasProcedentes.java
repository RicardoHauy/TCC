/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author RicardoHauy
 */
@WebServlet(name = "RelatorioSentencasProcedentes", urlPatterns = {"/RelatorioSentencasProcedentes"})
public class RelatorioSentencasProcedentes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     private Connection getConnection(){
    
        Connection con = null;
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:tcc/tcc@//localhost:1521/XE");
            
        } catch (Exception ex) {
            System.out.println("Erro na Conexão --> "+ex.getMessage());
        }
            
        return con;
    
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletOutputStream servletOuputStream = response.getOutputStream();

        HttpSession session = request.getSession(true);
        
        String rel = session.getAttribute("relatorio").toString();
        
        String caminho = "/WEB-INF/relatorios/";
        String relatorio = caminho+rel;
        
        InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream(relatorio);
        
        ServletContext context = getServletContext();
        
        try {

            Connection connection = getConnection();
            
            HashMap map = new HashMap();
            
            JasperRunManager.runReportToPdfStream(reportStream, servletOuputStream, map, connection);
           
            response.setHeader("application/pdf", "Content-Type");
            response.setContentType("application/pdf");
            
            servletOuputStream.flush();

            connection.close();
            
        } catch (Exception e) {
            System.out.println("ERRO AO GERAR RELATÒRIO --> "+e.getMessage());
        } finally {

            if (servletOuputStream != null) {
                servletOuputStream.close();
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
