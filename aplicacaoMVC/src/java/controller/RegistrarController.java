package controller;

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
import model.UsuarioDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "RegistrarController", urlPatterns = {"/RegistrarController"})
public class RegistrarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String senha2 = request.getParameter("senha2");

        if (senha.equals(senha2)) {
            Usuario usuario = new Usuario(nome, cpf, endereco, senha);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.contarUsuarios() == 0) {
                usuario.setAprovado("S");

            }
            try {
                usuarioDAO.inserir(usuario);
            } catch (Exception ex) {
                Logger.getLogger(RegistrarController.class.getName()).log(Level.SEVERE, null, ex);
            }

            RequestDispatcher rd;
            request.setAttribute("link", "home");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);

        } else {

            RequestDispatcher rd;
            request.setAttribute("msgError", "Senhas diferentes");
            request.setAttribute("link", "home");
            rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
            rd.forward(request, response);
        }

    }

}
