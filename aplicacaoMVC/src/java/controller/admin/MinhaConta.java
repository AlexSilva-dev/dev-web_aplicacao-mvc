/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entidade.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ComentarioDAO;
import model.UsuarioDAO;

/**
 *
 * @author lek
 */
@WebServlet(name = "MinhaConta", urlPatterns = {"/MinhaConta"})
public class MinhaConta extends HttpServlet {

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

        String acao = request.getParameter("acao");
        Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");

        request.setAttribute("usuario", usuario);

        switch (acao) {
            case "minha-conta":

                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/usuario/minhaConta.jsp");
                rd.forward(request, response);
                break;

            case "alterar":
                RequestDispatcher rd1 = request.getRequestDispatcher("/views/admin/usuario/alterar-dados.jsp");
                rd1.forward(request, response);
                break;

            case "excluir":

                RequestDispatcher rd2 = request.getRequestDispatcher("/views/admin/usuario/deletar.jsp");
                rd2.forward(request, response);
                break;

            default:
                throw new AssertionError();
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

        String acao = request.getParameter("btEnviar");
        System.out.println(acao);
        int id = Integer.parseInt(request.getParameter("id"));
        UsuarioDAO usuDAO = new UsuarioDAO();

        switch (acao) {
            case "Alterar":

                String nome = request.getParameter("nome");
                String end = request.getParameter("endereco");
                String cpf = request.getParameter("cpf");
                String senha = request.getParameter("senha");

                System.out.println(id + " estou");
                Usuario usuAlt = new Usuario();
                usuAlt.setId(id);
                usuAlt.setNome(nome);
                usuAlt.setEndereco(end);
                usuAlt.setCpf(cpf);
                usuAlt.setSenha(senha);

                try {
                    usuDAO.alterar(usuAlt);
                } catch (Exception ex) {
                    Logger.getLogger(MinhaConta.class.getName()).log(Level.SEVERE, null, ex);
                }
                //request.setAttribute("usuario", usuAlt);
                RequestDispatcher rd = request.getRequestDispatcher("/AutenticaController");
                rd.forward(request, response);
                break;

            case "Excluir":

                
                ComentarioDAO comnDAO = new ComentarioDAO();
                comnDAO.ExcluirComentarioAssociado(id);
                try {

                    usuDAO.excluir(id);
                } catch (Exception ex) {
                    Logger.getLogger(MinhaConta.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                response.sendRedirect("http://localhost:8080/aplicacaoMVC/admin/logOut");
 
                break;
            default:
                throw new AssertionError();
        }
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
