<%@page import="java.util.ArrayList"%>
<%@page import="app.Conta"%>
<%@page import="model.UsuarioDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Edição de Conta</title>
    </head>
    <body>
        <%@include file="headerUsuario.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Edição de - Conta</h2>
                    </div>
                    <hr>
                    <form method="POST" action="EditarConta" >
                        <div class="form-group mb-4">
                            <label for="banco">Selecione a conta a ser editada:</label>
                            <select class="form-control" id="banco" name="banco" required="required">
                                <option></option>
                            <% 
                                Integer id_usu = (Integer) request.getSession().getAttribute("id_usuario");
                              
                                UsuarioDAO usuariodao = new UsuarioDAO();
                                ArrayList<Conta> ListaConta = usuariodao.getListaContas(id_usu);
                                for (int i = 0; i < ListaConta.size(); i++) {
                                    Conta aux = ListaConta.get(i);
                            %>                     
                            <option value="<%=aux.getBanco()%>"><%=aux.getNome_conta()%> - Banco: <%=aux.getBanco()%>, Ag: <%=aux.getAgencia()%>, C/C: <%=aux.getConta_corrente()%></option>
                            <%
                            }
                            %>
                            </select>
                        </div>
                        <hr>
                        <div class="form-group mb-2">
                            <label for="nome_conta">Novo nome da conta:</label>
                            <input type="text" class="form-control"  name="nome_conta" required="required" maxlength="20"  placeholder="">
                            <span style="font-size: 0.8rem;">Ex: Conta BB</span>
                        </div>
                        
                        <div class="form-group mb-2">
                            <label for="agencia">Nova agência:</label>
                            <input type="number" class="form-control"  name="agencia" required="required" max="999999" placeholder="">
                            <span style="font-size: 0.8rem;">Ex: 3463</span>
                        </div>
                        
                        <div class="form-group mb-4">
                            <label for="conta_corrente">Nova conta-corrente:</label>
                            <input type="text" class="form-control"  name="conta_corrente" required="required"  pattern=\d{4}-\d{1} placeholder="">
                            <span style="font-size: 0.8rem;">Ex: 5676-8</span>
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