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

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">

                    <form action="ComentarioController" method="POST">

                        <div class="mb-3">
                            <input type="hidden" name="idComentario" value="implementar">
                            <label for="comentario" class="form-label">Escreva seu comentário:</label>
                            <textarea id="textarea" class="form-control form-control-sm" name="comentario" rows="5" cols="10"></textarea>
                        </div>

                        <div><!-- comment -->
                            <input class="btn btn-primary" name="btEnviar" type="submit" value="Alterar" >
                        </div>


                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>