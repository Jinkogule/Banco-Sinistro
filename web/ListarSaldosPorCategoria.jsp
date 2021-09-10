<%@page contentType="text/html; encoding=UTF-8"%>
<%@ page import="java.util.*,app.*" %>
<%@page import="model.AdministradorDAO"%>
<%@page import="model.UsuarioDAO"%>
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
                        <h2>Balancete das Contas</h2>
                    </div>
                    <hr>
                    <span>Balancete por categoria de cada uma das contas deste usuário.</span>   
                    <% 
                        Integer id_usu = (Integer) request.getSession().getAttribute("id_usuario");

                        UsuarioDAO usuariodao = new UsuarioDAO();
                        ArrayList<Conta> ListaConta = usuariodao.getListaContas(id_usu);
                        for (int i = 0; i < ListaConta.size(); i++) {
                            Conta aux = ListaConta.get(i);
                    %>
                    <div class="table-responsive">
                        <table class="table table-borderless bg-light">
                            <hr><h2 style="background-color: rgb(201, 201, 201); color: black;">Conta: <%=aux.getNome_conta()%> - Saldo: <%=usuariodao.getSaldoContas(id_usu, aux.getId())%></h2>
                            <thead class="thead-light">
                                <tr>
                                    <!--<th scope="col">ID do produto</th>-->
                                    <th scope="col">Categoria</th>
                                    <th scope="col">Creditado</th>
                                    <th scope="col">Debitado</th>
                                    <th scope="col">Saldo</th>
                                </tr>
                            </thead>
                            <tbody>                               
                            <%
                                AdministradorDAO admindao = new AdministradorDAO();
                                ArrayList<Categoria> ListaCategoria = admindao.getListaCategorias();
                                for (int j = 0; j < ListaCategoria.size(); j++) {
                                    Categoria aux2 = ListaCategoria.get(j);
                            %>                    
                            <tr>
                                <td><%=aux2.getDescricao()%></td>
                                
                                <td><%=usuariodao.getCreditoPorCategoria(id_usu, aux.getId(), aux2.getId())%> (<%=usuariodao.getPercentualCreditoPorCategoria(id_usu, aux.getId(), aux2.getId())%>%)</td>
                                <td><%=usuariodao.getDebitoPorCategoria(id_usu, aux.getId(), aux2.getId())%> (<%=usuariodao.getPercentualDebitoPorCategoria(id_usu, aux.getId(), aux2.getId())%>%)</td>
                                <td><%=usuariodao.getSaldoPorCategoria(id_usu, aux.getId(), aux2.getId())%></td>
                            </tr>
                            <%                             
                                }
                            }
                            %>
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
