<%@page import="model.AdministradorDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<%@ page import="java.util.*,app.*" %>
<%@page import="java.util.ArrayList"%>
<%@page import="app.Usuario"%>                   
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Lista de Usuários</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-home centralizar">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Lista de Usuários</h2>
                    </div>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-borderless bg-light">
                            <thead class="thead-light">
                                <tr>
                                    <!--<th scope="col">ID do produto</th>-->
                                    <th scope="col">ID</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">CPF</th>
                                    <th scope="col">Senha</th>
                                    <th scope="col">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                                AdministradorDAO admindao = new AdministradorDAO();
                                ArrayList<Usuario> ListaUsuario = admindao.getListaUsuarios();
                                for (int i = 0; i < ListaUsuario.size(); i++) {
                                    Usuario aux = ListaUsuario.get(i);
                            %>                        
                            <tr>                     
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getNome()%></td>
                                <td><%=aux.getCpf()%></td>
                                <td><%=aux.getSenha()%></td>
                                <td><%=aux.getSuspenso()%></td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                <a href="/BancoSinistro/homeAdmin.jsp" class="btn btn-primary btn-lg">Voltar</a>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>
