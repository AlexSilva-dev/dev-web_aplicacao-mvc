<%@page import="entidade.Comentario"%>
<%@page import="model.ComentarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Categoria"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Alterar Comentário</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <style>
        #textarea{
            resize: none;
        }
    </style>
    <body>

        <%
            Comentario comn = (Comentario) request.getAttribute("comentario");
            Categoria cat = (Categoria) request.getAttribute("categoria");

        %>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />

            <h1 class="display-4 mt-5 mb-5">Alterar Comentário</h1>
            <div class="col">

                <form action="ComentarioController" method="POST">
                    <div class="row">
                        <div class="col-8 mb-3">
                            <label for="comentario" class="form-label">Digite a alteração</label>
                            <textarea id="textarea" class="form-control form-control-sm" name="comentario" rows="5" cols="10"><%=comn.getComentario()%></textarea>
                        </div>

                        <div class="col">
                            <input type="hidden" name="id" value="<%=comn.getId()%>"
                                   <label for="categoria" class="form-label">Escolha a categoria do comentario:</label>
                            <select name="select" class="form-select">

                                <%
                                    ArrayList<Categoria> listaCategoria = (ArrayList<Categoria>) request.getAttribute("listaCategoria");
                                    listaCategoria.remove(cat);
                                %>

                                <option value='<%=cat.getId()%>'>
                                    <%=cat.getDescricao()%>
                                </option>
                                <%
                                    for (Categoria item : listaCategoria) {
                                %>
                                <option value='<%=item.getId()%>'><%=item.getDescricao()%></option>
                                <%}%>

                            </select>
                        </div>
                    </div>
                    <div><!-- comment -->
                        <input class="btn btn-warning" name="btEnviar" type="submit" value="Alterar" >
                        <a href="/aplicacaoMVC/MostrarComentarios" class="btn btn-danger">Retornar</a>

                    </div>


                </form>

            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>