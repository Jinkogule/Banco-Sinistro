<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Cadastro de Conta</title>
    </head>
    <body>
        <%@include file="headerUsuario.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Cadastro de Conta</h2>
                    </div>
                    <hr>
                    <form method="POST" action="CadastrarConta" >
                        <div class="form-group">
                            <label for="nome_conta">Nome da conta:</label>
                            <input type="text" class="form-control"  name="nome_conta" required="required" maxlength="20"  placeholder="">
                            <span style="font-size: 0.8rem;">Ex: Conta Nubank</span>
                        </div>
                        
                        <div class="form-group">
                            <label for="banco">Banco:</label>
                            <input type="number" class="form-control"  name="banco" required="required" pattern=\d{3} placeholder="">
                            <span style="font-size: 0.8rem;">Ex: 290</span>
                        </div>
                        
                        <div class="form-group">
                            <label for="agencia">Agência:</label>
                            <input type="number" class="form-control"  name="agencia" required="required" max="999999" placeholder="">
                            <span style="font-size: 0.8rem;">Ex: 5554</span>
                        </div>
                        
                        <div class="form-group">
                            <label for="conta_corrente">Conta-corrente:</label>
                            <input type="text" class="form-control"  name="conta_corrente" required="required"  pattern=\d{4}-\d{1} placeholder="">
                            <span style="font-size: 0.8rem;">Ex: 8888-8</span>
                        </div><br>
                        
                        <div class="d-flex flex-row justify-content-center align-items-center">
                            <button type="submit" class="btn btn-primary" style="background-color: green;">Confirmar</button>
                        </div>
                    </form>         
                </div>
                <div class="d-flex flex-row justify-content-center align-items-center mt-3">
                    <a href="/BancoSinistro/homeUsuario.jsp" class="btn btn-primary btn-lg">Voltar</a>
                </div> 
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>