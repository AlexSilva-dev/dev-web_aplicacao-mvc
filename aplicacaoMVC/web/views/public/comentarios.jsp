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
            <div class="mt-5">
                <h1>Comentários dos usuários</h1>

                <%
                    Usuario usuario = (Usuario)((HttpServletRequest) request).getSession().getAttribute("usuario");
                    ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>) request.getAttribute("listaComentarios");
                    for (Comentario comentario : listaComentarios) {%>

                <div class="card mb-2 col-sm-6">
                    <div class="card-body">
                        <%= comentario.getComentario()%>
                    </div>
                </div>



                <%  }%>

            </div>
            <div class="mt-5">
                <form action="ComentarioController" method="POST">

                    <div class="mb-3">
                        <label for="comentario" class="form-label">Escreva seu comentário:</label>
                        <textarea id="textarea" class="form-control form-control-sm" name="comentario" rows="5" cols="10"></textarea>
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

