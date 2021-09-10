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
                        <h2>Balancete Total</h2>
                    </div>
                    <hr>
                    <span>Somatório do balancete por categoria de cada uma das contas deste usuário.</span>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-borderless bg-light">
                        
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
                                Integer id_usu = (Integer) request.getSession().getAttribute("id_usuario");
                                UsuarioDAO usuariodao = new UsuarioDAO();
                                
                                AdministradorDAO admindao = new AdministradorDAO();
                                ArrayList<Categoria> ListaCategoria = admindao.getListaCategorias();
                                for (int j = 0; j < ListaCategoria.size(); j++) {
                                    Categoria aux2 = ListaCategoria.get(j);
                            %>                    
                            <tr>
                                <td><%=aux2.getDescricao()%></td>
                                
                                <td><%=usuariodao.getCreditoPorCategoriaTotal(id_usu, aux2.getId())%> (<%=usuariodao.getPercentualCreditoPorCategoriaTotal(id_usu, aux2.getId())%>%)</td>
                                <td><%=usuariodao.getDebitoPorCategoriaTotal(id_usu, aux2.getId())%> (<%=usuariodao.getPercentualDebitoPorCategoriaTotal(id_usu, aux2.getId())%>%)</td>
                                <td><%=usuariodao.getSaldoPorCategoriaTotal(id_usu, aux2.getId())%></td>
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
                                    <th scope="col">Creditado Total</th>
                                    <th scope="col">Debitado Total</th>
                                    <th scope="col">Saldo Total</th>
                                </tr>
                            </thead>
                            <tbody>
                            <tr><td><%=usuariodao.getCreditadoContasTotal(id_usu)%></td>
                                <td><%=usuariodao.getDebitadoContasTotal(id_usu)%></td>
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