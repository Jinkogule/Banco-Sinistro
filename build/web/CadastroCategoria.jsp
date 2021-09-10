<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Cadastro de Categoria</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Cadastro de Categoria</h2>
                    </div>
                    <hr>
                    <form method="POST" action="CadastrarCategoria" >                        
                        <div class="form-group mb-4">
                            <label for="descricao">Descrição:</label>
                            <input type="text" class="form-control"  name="descricao" required="required" maxlength="20" placeholder="">
                        </div>
                        
                        <div class="d-flex flex-row justify-content-center align-items-center">
                            <button type="submit" class="btn btn-primary" style="background-color: green;">Confirmar</button>
                        </div>
                    </form>         
                </div>
                <div class="d-flex flex-row justify-content-center align-items-center mt-3">
                    <a href="/BancoSinistro/homeAdmin.jsp" class="btn btn-primary btn-lg">Voltar</a>
                </div>    
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>