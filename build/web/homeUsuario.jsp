<%@page import="model.UsuarioDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Home - Usuário</title>
    </head>
    <body>
        <%@include file="headerUsuario.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                
                <div class="container container-home centralizar">
                    <div class="d-flex flex-row justify-content-center align-items-center mt-5 mb-3">
                        <h1>Bem-vindo(a), ${nome}!</h1>
                    </div>
                    <% 
                        Integer id_usu = (Integer) request.getSession().getAttribute("id_usuario");

                        UsuarioDAO usuariodao = new UsuarioDAO();
                        if (usuariodao.verifExistenciaSaldoNegativo(id_usu) == true){
                    %>
                    
                    <h4 style="color: yellow; font-size: 1.0rem" class="mb-2">ALERTA: você possui conta(s) no negativo. Para mais detalhes verificar sua lista de contas ou o "balancete das contas".</h4>
                    <%
                    }
                    %>
                    <% 
                        if (usuariodao.verifSaldoTotalNegativo(id_usu) == true){
                    %>
                    <h4 style="color: red; font-size: 1.0rem" class="mb-2">ALERTA: Seu saldo total está no negativo. Para mais detalhes verificar sua lista de contas ou o ou o "balancete total".</h4>
                    <%
                    }
                    %>
                    <hr><h4>CADASTRAR</h4><hr>
                    <a href="CadastroConta.jsp" class="btn btn-primary btn mb-2">Cadastrar Conta</a>
                    <a href="CadastroMovimento.jsp" class="btn btn-primary btn mb-2">Cadastrar Movimento</a>
                    
                    <hr><h4>EDITAR</h4><hr>
                    <a href="EditarConta.jsp" class="btn btn-primary btn mb-2">Editar Conta</a>
                    
                    <hr><h4>EXCLUIR</h4><hr>
                    <a href="ExcluirConta.jsp" class="btn btn-primary btn mb-2">Excluir Conta</a>
                    
                    <hr><h4>LISTAS</h4><hr>
                    <a href="ListarContas.jsp" class="btn btn-primary btn mb-2">Minhas Contas</a>
                    <a href="ListarLancamentos.jsp" class="btn btn-primary btn mb-2">Minhas movimentações</a>
                    <a href="ListarSaldosPorCategoria.jsp" class="btn btn-primary btn mb-2">Balancete das Contas</a>
                    <a href="ListarSaldosPorCategoriaTotal.jsp" class="btn btn-primary btn mb-2">Balancete Total</a>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>
   