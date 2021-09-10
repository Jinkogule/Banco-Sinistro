<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Você está suspenso! :(</title>
    </head>
    <body>
        <nav class="navbar fixed-top">
            <div class="container nav-bar">
                <a class="navbar-brand text-white" href="index.jsp"><b>BancoSinistro</b></a>
            </div>
        </nav>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-home centralizar">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Você está suspenso! :(</h2>
                    </div>
                    <hr>
                    <a href="/BancoSinistro/index.jsp" class="btn btn-primary btn">Voltar à página inicial</a>
                    <a href="/BancoSinistro/login.jsp" class="btn btn-primary btn">Voltar à página de login</a>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>