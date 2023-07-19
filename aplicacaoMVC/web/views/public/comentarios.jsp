<%@page import="entidade.Categoria"%>
<%@page import="entidade.Usuario"%>
<%@page import="entidade.Comentario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Login</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <style>
        #textarea{
            resize: none;
        }
    </style>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <h1 class="mt-5 mb-5">Comentários dos usuários</h1>

            <%
                String btEnviar;
                Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");
                ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>) request.getAttribute("listaComentarios");
                for (Comentario comentario : listaComentarios) {%>


            <div class="card mb-4 mr-5">
                <div class="card-body">
                    <h5 class="card-title"><%=comentario.getData()%> - Autor: <%=comentario.getNomeususario()%></h5>
                    <h6 class="card-subtitle mb-2 text-muted">Categoria: <%=comentario.getNomeCategoria()%></h6>
                    <p class="card-text">
                        <%= comentario.getComentario()%>


                    </p>

                    <%
                        if ((usuario != null) && (!((String) usuario.getNome()).isEmpty()) && comentario.getIdusuario() == usuario.getId()) {

                    %>
                    <div class="row ">
                        <div class="col-sm-1">
                            <form action="ComentarioController" method="POST">
                                <input type="hidden" name="idComentario" value="<%=comentario.getId()%>">
                                <input type="submit" class="btn btn-danger" name="btEnviar" value="Excluir">
                            </form>
                        </div>
                                

                        <div class="col-sm-1">
                            <a href="/aplicacaoMVC/ComentarioController?idComentario=<%=comentario.getId()%>&idCategoria=<%=comentario.getIdcategoria()%>" class="btn btn-warning">
                                Alterar
                            </a>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>


            <%  }%>

            <div class="mt-5">
                <form action="ComentarioController" method="POST">
                    <div class="row">
                        <div class="col-8 mb-3">
                            <label for="comentario" class="form-label">Escreva seu comentário:</label>
                            <textarea id="textarea" class="form-control form-control-sm" name="comentario" rows="5" cols="10"></textarea>
                        </div>

                        <div class="col">
                            <label for="categoria" class="form-label">Escolha a categoria do comentario:</label>

                            <select name="select" class="form-select">

                                <%
                                    ArrayList<Categoria> listaCategoria = (ArrayList<Categoria>) request.getAttribute("listaCategoria");
                                    for (Categoria item : listaCategoria) {
                                %>
                                <option value='<%=item.getId()%>'><%=item.getDescricao()%></option>
                                <%}%>

                            </select>
                        </div>
                    </div>
                    <div><!-- comment -->
                        <input class="btn btn-primary" name="btEnviar" type="submit" value="Enviar" >
                    </div>


                </form>
            </div>
        </div>


        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

