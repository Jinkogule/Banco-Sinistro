<%@page import="app.Conta"%>
<%@page import="model.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="app.Categoria"%>
<%@page import="model.AdministradorDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Excluir Conta</title>
    </head>
    <body>
        <%@include file="headerUsuario.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Excluir Conta</h2>
                    </div>
                    <hr>
                    <form method="POST" action="ExcluirConta" >
                        
                        <div class="form-group mb-4">
                            <label for="cpf">Selecione a conta a ser excluída:</label>
                            <select class="form-control" id="id" name="id" required="required">
                                <option></option>
                            <% 
                                Integer id_usu = (Integer) request.getSession().getAttribute("id_usuario");
                              
                                UsuarioDAO usuariodao = new UsuarioDAO();
                                ArrayList<Conta> ListaConta = usuariodao.getListaContas(id_usu);
                                for (int i = 0; i < ListaConta.size(); i++) {
                                    Conta aux = ListaConta.get(i);
                            %>                         
                            <option value="<%=aux.getId()%>"><%=aux.getNome_conta()%> - Banco: <%=aux.getBanco()%>, Ag: <%=aux.getAgencia()%>, C/C: <%=aux.getConta_corrente()%></option>
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
                    <a href="/BancoSinistro/homeUsuario.jsp" class="btn btn-primary btn-lg">Voltar</a>
                </div>    
            </div>
        </div>
        <%@include file="footer.jsp" %>       
    </body>
</html>