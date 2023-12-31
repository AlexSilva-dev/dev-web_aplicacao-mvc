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

                    <h1 class="mb-4">Minha conta</h1>
                    <form action="RegistrarController" method="POST">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" class="form-control" placeholder="Seu nome" value="<%=usuario.getNome()%>" readonly="">
                        </div>
                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" name="endereco" class="form-control" placeholder="Seu endereço" value="<%=usuario.getEndereco()%>" readonly="">
                        </div>
                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" name="cpf" class="form-control" placeholder="999.999.999-99" value="<%=usuario.getCpf()%>" readonly="">
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha" value="111" class="form-control" readonly="">
                        </div>

                        <div class="row">
                            <div class="col-sm-2">
                                <a class="btn btn-warning" href="/aplicacaoMVC/MinhaConta?acao=alterar">
                                    Alterar
                                </a>
                            </div>

                            <div class="col-sm-4">
                                <a class="btn btn-danger" href="/aplicacaoMVC/MinhaConta?acao=excluir">
                                    Deletar conta
                                </a>
                            </div>

                        </div>
                    </form>

                </div>

            </div>




        </div>
    </body>
    <script src="views/bootstrap/bootstrap.bundle.min.js"></script>

</html>
