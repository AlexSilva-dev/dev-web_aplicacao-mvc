<%-- 
    Document   : minhaConta
    Created on : Jul 19, 2023, 4:22:37 PM
    Author     : lek
--%>

<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <link href="views/bootstrap/bootstrap.min.css"  rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">

            <jsp:include page="../../comum/menu.jsp" />


            <%
                //Usuario
                Usuario usuario = (Usuario) request.getAttribute("usuario");
            %>

            <div class="d-flex justify-content-center mt-5">




                <div class="col-8  ">

                    <h1 class="mb-4">Deseja deletar sua conta?</h1>


                    <div>
                        <form action="MinhaConta" method="POST">
                            <div class="row">
                                <input type="hidden" name="id" value="<%=usuario.getId()%>">
                                <div class="col-sm-2">

                                    <input type="submit" name="btEnviar" value="Excluir" class="btn btn-danger">
                                </div>

                                <div class="col-sm-2">
                                    <a href="/aplicacaoMVC/MinhaConta?acao=minha-conta" class="btn btn-primary">Retornar</a>
                                </div>
                            </div>

                        </form>
                    </div>



                </div>

            </div>




        </div>
    </body>
    <script src="views/bootstrap/bootstrap.bundle.min.js"></script>

</html>
