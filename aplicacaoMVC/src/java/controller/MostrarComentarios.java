package controller;

import entidade.Categoria;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ComentarioDAO;
import entidade.Comentario;
import entidade.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CategoriaDAO;
import model.UsuarioDAO;


@WebServlet(name = "MostrarComentarios", urlPatterns = {"/MostrarComentarios"})
public class MostrarComentarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   ComentarioDAO comentarioDAO = new ComentarioDAO();
            try {
                ArrayList<Comentario> listaComentarios = comentarioDAO.getAll();
                UsuarioDAO usuDAO = new UsuarioDAO();
                CategoriaDAO catDAO = new CategoriaDAO();
                
                for (Comentario item : listaComentarios) {
              
                    Categoria categoria = catDAO.get(item.getIdcategoria());
                    item.setNomeCategoria(categoria.getDescricao());
                    
                    
                    try {
                        
                        Usuario usu = usuDAO.getUsuario(item.getIdusuario());
                        item.setNomeususario(usu.getNome());
                        
                    } catch (Exception ex) {
                        
                        Logger.getLogger(MostrarComentarios.class.getName()).log(Level.SEVERE, null, ex);
                    
                    }
                    
                }
                
                request.setAttribute("listaComentarios", listaComentarios);
                
                
                ArrayList<Categoria> listaCategoria = catDAO.getAll();
                request.setAttribute("listaCategoria", listaCategoria);

                


                
                RequestDispatcher rd = request.getRequestDispatcher("/views/public/comentarios.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar usuarios (mostrarComentarios) ");
            }
    }

}
