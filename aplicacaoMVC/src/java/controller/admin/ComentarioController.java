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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;
import model.ComentarioDAO;
import model.UsuarioDAO;

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

        int idComentario = Integer.parseInt(request.getParameter("idComentario"));
        CategoriaDAO catDAO = new CategoriaDAO();
        ComentarioDAO comnDAO = new ComentarioDAO();

        Comentario comn = comnDAO.get(idComentario);
        request.setAttribute("comentario", comn);

        Categoria cat = catDAO.get(comn.getIdcategoria());
        ArrayList<Categoria> listaCategoria = catDAO.getAll();
        request.setAttribute("listaCategoria", listaCategoria);
        request.setAttribute("categoria", cat);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/comentario/alterar.jsp");
        rd.forward(request, response);
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

        String descComentario = request.getParameter("comentario");
        String btEnviar = request.getParameter("btEnviar");
        Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");
        ComentarioDAO comnDAO = new ComentarioDAO();

        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataString = dataAtual.format(formatador);

        switch (btEnviar) {
            case "Enviar":

                int idCategoria = Integer.parseInt(request.getParameter("select"));

                Comentario comn = new Comentario(descComentario, dataString, usuario.getId(), idCategoria);
                comnDAO.insert(comn);

                ((HttpServletResponse) response).sendRedirect("http://localhost:8080/aplicacaoMVC/MostrarComentarios");
                break;

            case "Excluir":

                int idComentario = Integer.parseInt(request.getParameter("idComentario"));

                try {
                    comnDAO.delete(idComentario);
                } catch (Exception ex) {
                    Logger.getLogger(ComentarioController.class.getName()).log(Level.SEVERE, null, ex);
                }

                ((HttpServletResponse) response).sendRedirect("http://localhost:8080/aplicacaoMVC/MostrarComentarios");
                break;

            case "Alterar":
                int idComn = Integer.parseInt(request.getParameter("id"));
                int idCat = Integer.parseInt(request.getParameter("select"));
                Comentario antigComn = comnDAO.get(idComn);
                Comentario comnAlt = new Comentario(idComn, descComentario, dataString, antigComn.getIdusuario(), idCat );
                
                comnDAO.update(comnAlt);
                
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
