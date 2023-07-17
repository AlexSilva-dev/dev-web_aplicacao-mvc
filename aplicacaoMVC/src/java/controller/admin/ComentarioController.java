package controller.admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import entidade.Categoria;
import entidade.Comentario;
import entidade.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;
import model.ComentarioDAO;

/**
 *
 * @author lek
 */
@WebServlet(urlPatterns = {"/ComentarioController"})
public class ComentarioController extends HttpServlet {

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
        

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComentarioController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComentarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        

                String comentario = request.getParameter("comentario");
                
                String btEnviar = request.getParameter("btEnviar");
                
                
                switch (btEnviar) {
            case "Enviar":
                
                LocalDate dataAtual = LocalDate.now();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dataString = dataAtual.format(formatador);
                Usuario usuario = (Usuario)((HttpServletRequest) request).getSession().getAttribute("usuario");


                Comentario comm = new Comentario(2, comentario, dataString, usuario.getId(), 1);
                ComentarioDAO commDAO = new ComentarioDAO();
                commDAO.insert(comm);
                
                ((HttpServletResponse) response).sendRedirect("http://localhost:8080/aplicacaoMVC/MostrarComentarios");   
                break;
                
            case "Excluir":
                System.out.println("fjapf");
                ((HttpServletResponse) response).sendRedirect("http://localhost:8080/aplicacaoMVC/MostrarComentarios");   
                break;
                
            case "Alterar":
                System.out.println("fjapf");
                ((HttpServletResponse) response).sendRedirect("http://localhost:8080/aplicacaoMVC/MostrarComentarios");   
                break;
            default:
                throw new AssertionError();
        }
        
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
