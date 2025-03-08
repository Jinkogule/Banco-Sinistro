package servlet;

import app.Conta;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.UsuarioDAO;
import model.conexaoBD;

@WebServlet(name = "CadastrarConta", urlPatterns = {"/CadastrarConta"})
public class CadastrarConta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_usuario = (int) request.getSession().getAttribute("id_usuario");   
        String nome_conta = (String) request.getParameter("nome_conta");
        String banco = (String) request.getParameter("banco");
        String agencia = (String) request.getParameter("agencia");
        String conta_corrente = (String) request.getParameter("conta_corrente");
        
        if ((!nome_conta.isEmpty()) && (!banco.isEmpty()) && (!agencia.isEmpty()) && (!conta_corrente.isEmpty())) {

            Conta conta = new Conta();

            conta.setId(0);
            conta.setId_usuario(id_usuario);
            conta.setNome_conta(nome_conta);
            conta.setBanco(banco);
            conta.setAgencia(agencia);
            conta.setConta_corrente(conta_corrente);
  
            UsuarioDAO usuario = new UsuarioDAO();

            if (verifContaCadastrada(banco, agencia, conta_corrente) == true){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroCadastrarContaExistente.jsp");
                rd.forward(request, response);
            }
            else if (verifContaCadastrada2(id_usuario, banco) == true){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroCadastrarContaJaPossui.jsp");
                rd.forward(request, response);
            }
            else{
                usuario.cadastrarConta(conta);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoConta.jsp");
                rd.forward(request, response);
            }
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
    
    private Connection conexao;
    //função auxiliar para verificar se a conta ja havia sido cadastrado    
    public boolean verifContaCadastrada(String banco, String agencia, String conta_corrente) {
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
                conta.setConta_corrente(rs.getString("conta_corrente") );
               
                if (banco.equals(rs.getString("banco")) && agencia.equals(rs.getString("agencia")) && conta_corrente.equals(rs.getString("conta_corrente"))){
                    return true;
                }
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }
    
    //função auxiliar para verificar se o usuário já havia cadastrado uma conta usando o banco inserido
    public boolean verifContaCadastrada2(int id_usuario, String banco) {
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
                conta.setId_usuario(rs.getInt("id_usuario"));
                conta.setBanco(rs.getString("banco"));
               
                if (id_usuario == (rs.getInt("id_usuario")) && banco.equals(rs.getString("banco"))){
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