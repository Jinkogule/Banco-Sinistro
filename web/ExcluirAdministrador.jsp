<%@page import="java.util.ArrayList"%>
<%@page import="app.Administrador"%>
<%@page import="model.AdministradorDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Excluir Administrador</title>
    </head>
    <body>
        <%@include file="headerAdministrador.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Excluir Administrador</h2>
                    </div>
                    <hr>
                    <form method="POST" action="ExcluirAdministrador" >
                        
                        <div class="form-group mb-4">
                            <label for="cpf">Selecione o administrador a ser excluído:</label>
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