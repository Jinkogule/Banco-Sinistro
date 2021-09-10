<%@page import="model.AdministradorDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<%@ page import="java.util.*,app.*" %>
<%@page import="java.util.ArrayList"%>
<%@page import="app.Administrador"%>                   
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Lista de Administradores</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-home centralizar">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Lista de Administradores</h2>
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
                                </tr>
                            </thead>
                            <tbody>
                            <%
                                AdministradorDAO admindao = new AdministradorDAO();
                                ArrayList<Administrador> ListaAdmin = admindao.getListaAdministradores();
                                for (int i = 0; i < ListaAdmin.size(); i++) {
                                    Administrador aux = ListaAdmin.get(i);
                            %>                      
                            <tr>                     
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getNome()%></td>
                                <td><%=aux.getCpf()%></td>
                                <td><%=aux.getSenha()%></td>
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


