<%@page import="java.util.ArrayList"%>
<%@page import="app.Usuario"%>
<%@page import="model.AdministradorDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Edição de Usuário</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Edição de Usuário</h2>
                    </div>
                    <hr>
                    <form method="POST" action="EditarUsuario">
                      
                        <div class="form-group mb-4">
                            <label for="cpf">Selecione o usuário a ser editado:</label>
                            <select class="form-control" id="cpf" name="cpf" required="required">
                                <option></option>
                            <%
                                AdministradorDAO admindao = new AdministradorDAO();
                                ArrayList<Usuario> ListaUsuario = admindao.getListaUsuarios();
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
                        <hr>
                        <div class="form-group mb-2">
                            <label for="exampleInputEmail1">Novo nome:</label>
                            <input type="text" class="form-control"  name="nome" required="required" maxlength="20"  placeholder="">
                        </div>
                        
                        <div class="form-group mb-2">
                            <label for="exampleInputPassword1">Nova senha:</label>
                            <input type="password" class="form-control"  name="senha" required="required" maxlength="255" placeholder="">
                        </div>
                        
                        <div class="form-group mb-4">
                            <label for="exampleInput">Novo status - Suspenso(S) / Não suspenso(N):</label>
                            <select class="form-control" id="status" name="suspenso" required="required">
                                <option></option>
                                <option>S</option>
                                <option>N</option>
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
