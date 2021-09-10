<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Usuário excluído com sucesso!</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-home centralizar">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Usuário excluído com sucesso!</h2>
                    </div>
                    <hr>
                    <a href="/BancoSinistro/homeAdmin.jsp" class="btn btn-primary btn">Voltar à página principal</a>
                    <a href="/BancoSinistro/ExcluirUsuario.jsp" class="btn btn-primary btn">Excluir novamente</a>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>