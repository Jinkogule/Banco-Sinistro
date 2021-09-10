<%@page import="app.Administrador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="app.Usuario"%>
<%@page import="model.AdministradorDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Edição de Administrador</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Edição de Administrador</h2>
                    </div>
                    <hr>
                    <form method="POST" action="EditarAdministrador" >
                      
                        <div class="form-group mb-4">
                            <label for="cpf">Selecione o administrador a ser editado:</label>
                            <select class="form-control" id="cpf" name="cpf" required="required">
                                <option></option>
                            <%
                                AdministradorDAO admindao = new AdministradorDAO();
                                ArrayList<Administrador> ListaAdmin = admindao.getListaAdministradores();
                                for (int i = 0; i < ListaAdmin.size(); i++) {
                                    Administrador aux = ListaAdmin.get(i);
                            %>                       
                            <option value="<%=aux.getCpf()%>"><%=aux.getNome()%> (<%=aux.getCpf()%>)</option>
                            <%
                            }
                            %>
                            </select>
                        </div>
                        <hr>       
                        <div class="form-group mb-2">
                            <label for="nome">Novo nome:</label>
                            <input type="text" class="form-control"  name="nome" required="required" maxlength="20"  placeholder="">
                        </div>
                        
                        <div class="form-group mb-4">
                            <label for="exampleInputPassword1">Nova senha:</label>
                            <input type="password" class="form-control"  name="senha" required="required" maxlength="255" placeholder="">
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