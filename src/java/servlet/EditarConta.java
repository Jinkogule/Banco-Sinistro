package servlet;

import app.Conta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsuarioDAO;
import model.conexaoBD;

@WebServlet(name = "EditarConta", urlPatterns = {"/EditarConta"})
public class EditarConta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_usuario = (int) request.getSession().getAttribute("id_usuario");
        
        String banco = request.getParameter("banco");
        String nome_conta = request.getParameter("nome_conta");
        String agencia = request.getParameter("agencia");
        String conta_corrente = request.getParameter("conta_corrente");
        
        if ((!banco.isEmpty()) && (!agencia.isEmpty()) && (!conta_corrente.isEmpty())) {

            Conta conta = new Conta();

            conta.setId_usuario(id_usuario);
            conta.setBanco(banco);
            conta.setNome_conta(nome_conta);
            conta.setAgencia(agencia);
            conta.setConta_corrente(conta_corrente);
     
            UsuarioDAO usuario = new UsuarioDAO();

            if (verifContaCadastradaEdicao(banco, agencia, conta_corrente, id_usuario) == true){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroEditarContaViraExistente.jsp");
                rd.forward(request, response);
            }
            else if (verifNaoHouveAlteracao(banco, nome_conta, agencia, conta_corrente, id_usuario) == true){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroEditarContaSemAlteracao.jsp");
                rd.forward(request, response);
            }
            else{
                usuario.editarConta(conta);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoEditarConta.jsp");
                rd.forward(request, response);
            }
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
    
    private Connection conexao;
    
    //função para verificar se a edição de uma conta iguala corra-corrente e agência já existente no banco da conta sendo editada (para impedir que isso ocorra)
    public boolean verifContaCadastradaEdicao(String banco, String agencia, String conta_corrente, int id_usuario) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contas");
            while( rs.next() ) {
                Conta conta = new Conta();
                conta.setBanco(rs.getString("banco"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setConta_corrente(rs.getString("conta_corrente"));
                conta.setId_usuario(rs.getInt("id_usuario"));              
                //banco, agência e conta-corrente iguais, porém com usuário diferente
                if (banco.equals(rs.getString("banco")) && agencia.equals(rs.getString("agencia")) && conta_corrente.equals(rs.getString("conta_corrente")) && id_usuario != (rs.getInt("id_usuario"))){
                    return true;
                }
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }
    
    //função para verificar se a edição de uma conta não possui nenhuma alteração
    private boolean verifNaoHouveAlteracao(String banco, String nome_conta, String agencia, String conta_corrente, int id_usuario) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contas WHERE id_usuario ="+id_usuario);
            while( rs.next() ) {
            
                //banco, agência e conta-corrente iguais, porém com usuário diferente
                if (banco.equals(rs.getString("banco")) && nome_conta.equals(rs.getString("nome_conta")) && agencia.equals(rs.getString("agencia")) && conta_corrente.equals(rs.getString("conta_corrente"))){
                    return true;
                }
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }
}