<%@page import="app.Categoria"%>
<%@page import="model.AdministradorDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="app.Conta"%>
<%@page import="model.UsuarioDAO"%>
<%@page contentType="text/html; encoding=UTF-8"%>
<!DOCTYPE html>
<%@include file="bootstrap.html" %>

<html lang="pt-br">
    <head>
        <%@include file="header.html" %>
        <title>Registro de Movimento</title>
    </head>
    <body>
        <%@include file="headerUsuario.html" %>
 
        <div class="container container-index">
            <div class="container container-texto">
                <br>
                <div class="container container-forms">
                    <div class="d-flex flex-row justify-content-center align-items-center">
                        <h2>Realizar Movimentação</h2>
                    </div>
                    <hr>
                    <form method="POST" action="CadastrarLancamento" >
                        <div class="form-group mb-2">
                            <label for="id_conta">Selecione uma de suas contas:</label>
                            <select class="form-control" id="id_conta" name="id_conta" required="required">
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
                        
                        <div class="form-group mb-2">
                            <label for="id_categoria">Selecione uma categoria:</label>
                            <select class="form-control" id="id_categoria" name="id_categoria" required="required">
                                <option></option>
                            <%
                                AdministradorDAO admindao = new AdministradorDAO();
                                ArrayList<Categoria> ListaCategoria = admindao.getListaCategorias();
                                for (int i = 0; i < ListaCategoria.size(); i++) {
                                    Categoria aux = ListaCategoria.get(i);
                            %>                       
                            <option value="<%=aux.getId()%>"><%=aux.getDescricao()%></option>
                            <%
                            }
                            %>
                            </select>
                        </div>
                        
                        <div class="form-group mb-2">
                            <label for="valor">Valor:</label>
                            <input type="number" class="form-control"  name="valor" required="required" step=".01" placeholder="">
                            <span style="font-size: 0.8rem;">Ex: 120,90</span>
                        </div>
                        
                        <div class="form-group  mb-2">
                            <label for="operacao">Operação - Crédito(C) / Débito(D):</label>
                            <select class="form-control" id="operacao" name="operacao" required="required">
                                <option></option>
                                <option>C</option>
                                <option>D</option>
                            </select>
                        </div>
                        
                        <div class="form-group mb-2">
                            <label for="data">Data:</label>
                            <input type="date" class="form-control"  name="data" required="required" placeholder="">
                        </div>
                        
                        <div class="form-group mb-4">
                            <label for="descricao">Descrição:</label>
                            <input type="text" class="form-control"  name="descricao" placeholder="">
                            <span style="font-size: 0.8rem;">Obs: campo opcional</span>
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