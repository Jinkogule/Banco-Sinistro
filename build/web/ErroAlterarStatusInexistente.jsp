<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Erro ao alterar status</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="d-flex flex-row justify-content-center align-items-center m-5">
                    <h2>Não é possível alterar o status de um usuário inexistente. Certifique-se de inserir um CPF válido entre os usuários cadastrados.</h2>
                </div><br>
                <a href="/BancoSinistro/SuspenderUsuario.jsp" class="btn btn-primary btn-lg">Voltar</a>
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>

