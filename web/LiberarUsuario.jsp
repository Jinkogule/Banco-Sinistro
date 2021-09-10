<%@page import="java.util.ArrayList"%>
<%@page import="app.Usuario"%>
<%@page import="model.AdministradorDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Liberar Usuário</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Liberar Usuário</h2>
                    </div>
                    <hr>
                    <form method="POST" action="LiberarUsuario" >
                        
                        <div class="form-group mb-4">
                            <label for="cpf">Selecione o usuário a ser liberado:</label>
                            <select class="form-control" id="cpf" name="cpf" required="required">
                                <option></option>
                            <%
                                AdministradorDAO admindao = new AdministradorDAO();
                                ArrayList<Usuario> ListaUsuario = admindao.getListaUsuariosSuspensos();
                                for (int i = 0; i < ListaUsuario.size(); i++) {
                                    Usuario aux = ListaUsuario.get(i);
                            %>  
                            %>                       
                            <option value="<%=aux.getCpf()%>"><%=aux.getNome()%> (<%=aux.getCpf()%>)</option>
                            <%
                            }
                            %>
                            </select>
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