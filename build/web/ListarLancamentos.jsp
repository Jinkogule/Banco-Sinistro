<%@page import="model.UsuarioDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<%@ page import="java.util.*,app.*" %>
<%@page import="java.util.ArrayList"%>                
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Minhas Movimentações</title>
    </head>
    <body>
        <%@include file="headerUsuario.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-lista-grande centralizar">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Minhas Movimentações</h2>
                    </div>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-borderless bg-light">
                            <thead class="thead-light">
                                <tr>
                                    <!--<th scope="col">ID do produto</th>-->
                                    <th scope="col">Conta</th>
                                    <th scope="col">Categoria</th>
                                    <th scope="col">Valor</th>
                                    <th scope="col">Operação</th>
                                    <th scope="col">Data</th>
                                    <th scope="col">Descrição</th>
                                </tr>
                            </thead>
                            <tbody>
                            <% 
                                Integer id_usu = (Integer) request.getSession().getAttribute("id_usuario");
                              
                                UsuarioDAO usuariodao = new UsuarioDAO();
                                ArrayList<Lancamento> ListaLancamento = usuariodao.getListaLancamentos(id_usu);
                                for (int i = 0; i < ListaLancamento.size(); i++) {
                                    Lancamento aux = ListaLancamento.get(i);
                            %>                     
                            <tr>                       
                                <td><%=usuariodao.getNomeContaPorId(aux.getId_conta())%></td>
                                <td><%=usuariodao.getNomeCategoriaPorId(aux.getId_categoria())%></td>
                                <td><%=aux.getValor()%></td>
                                <td><%=aux.getOperacao()%></td>
                                <td><%=aux.getData()%></td>
                                <td><%=aux.getDescricao()%></td>
                            </tr>
                            <%
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