<%@page import="model.UsuarioDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<%@ page import="java.util.*,app.*" %>
<%@page import="java.util.ArrayList"%>                
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Minhas contas</title>
    </head>
    <body>
        <%@include file="headerUsuario.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-lista-grande centralizar">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Minhas Contas</h2>
                    </div>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-borderless bg-light">
                            <thead class="thead-light">
                                <tr>
                                    <!--<th scope="col">ID do produto</th>-->
                                    <th scope="col">Nome</th>
                                    <th scope="col">Banco</th>
                                    <th scope="col">Agência</th>
                                    <th scope="col">Conta-corrente</th>
                                    <th scope="col">Saldo</th>
                                </tr>
                            </thead>
                            <tbody>
                            <% 
                                Integer id_usu = (Integer) request.getSession().getAttribute("id_usuario");
                              
                                UsuarioDAO usuariodao = new UsuarioDAO();
                                ArrayList<Conta> ListaConta = usuariodao.getListaContas(id_usu);
                                for (int i = 0; i < ListaConta.size(); i++) {
                                    Conta aux = ListaConta.get(i);
                            %>                    
                            <tr>
                                <td><%=aux.getNome_conta()%></td>
                                <td><%=aux.getBanco()%></td>
                                <td><%=aux.getAgencia()%></td>
                                <td><%=aux.getConta_corrente()%></td>
                                <td><%=usuariodao.getSaldoContas(id_usu, aux.getId())%></td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                        <table class="table table-borderless bg-light">
                            <thead class="thead-light">
                                <tr>
                                    <!--<th scope="col">ID do produto</th>-->
                                    <th scope="col">Saldo Total</th>
                                </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><%=usuariodao.getSaldoContasTotal(id_usu)%></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <a href="/BancoSinistro/homeUsuario.jsp" class="btn btn-primary btn-lg">Voltar</a>
                </div>
            </div>
        </div>      
        <%@include file="footer.jsp" %>       
    </body>
</html>
