<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Dados inválidos! :(</title>
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
                    <div class="d-flex flex-row justify-content-center align-items-center m-5">
                        <h4>Dados inválidos! :(</h4>
                    </div>
                    <a href="/BancoSinistro/login.jsp" class="btn btn-primary btn">Tentar novamente</a>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>
