<%@page import="entidade.Usuario"%>
<%@page import="entidade.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Categorias</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Aprovação de usuarios</h1>
                <h2>Lista de usuarios</h2>

                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">Endereço</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Usuario> listaUsuario = (ArrayList<Usuario>) request.getAttribute("listUsuario");

                                for (Usuario usuario : listaUsuario) {
                                    out.println("<tr>");
                                    out.println("<th>" + usuario.getId() + "</th>");
                                    out.println("<td>" + usuario.getNome()+ "</td>");
                                    out.println("<td>" + usuario.getEndereco()+ "</td>");
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/admin/CategoriaController?acao=Alterar&id=<%=usuario.getId()%>" class="btn btn-warning">Aprovar</a>
                            
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>

