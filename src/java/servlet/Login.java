package servlet;

import app.Administrador;
import app.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.conexaoBD;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            String verif_admin = request.getParameter("administrador");

            Login login = new Login();
            
            //login de admin
            //tiramos a aceitação direta de "cpf = 249.252.810-38, senha = 111" para o "primeiro admin", pois o filtro barraria caso tais dados não estivessem no banco.
            //garantir que o banco iniciai possua esses dados inseridos
            if ("S".equals(verif_admin)){
                if (login.verifExistenciaAdmin(cpf, senha)) { 
                    request.getSession().setAttribute("nome", getNomeLogadoAdmin(cpf));          
                    request.getSession().setAttribute("verif", verif_admin);
                    response.sendRedirect("homeAdmin.jsp");
                }
                else{
                    response.sendRedirect("FalhaLogin.jsp");
                }
            }
            
            //login de usuário
            else if (verif_admin == null){
                if (login.verifExistenciaUsuario(cpf, senha)){
                    if (login.verifUsuarioSuspenso(cpf, senha)){
                        response.sendRedirect("MensagemSuspenso.jsp");
                    }
                    else{
                        request.getSession().setAttribute("id_usuario", getIdLogado(cpf));
                        request.getSession().setAttribute("nome", getNomeLogado(cpf));
                        request.getSession().setAttribute("existencia_status", login.getValorSuspensoLogado(cpf));
                        request.getSession().setAttribute("verif", verif_admin);
                        response.sendRedirect("homeUsuario.jsp");
                    }   
                }
                else{
                    response.sendRedirect("FalhaLogin.jsp");
                } 
            }
            else {
                response.sendRedirect("FalhaLogin.jsp");
            }
        }
        finally {
            out.close();
        }

    }
    private Connection conexao;
    //função auxiliar para verificar se o login de administrador é válido    
    public boolean verifExistenciaAdmin(String cpf, String senha) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM administradores");
            while( rs.next() ) {
                Administrador admin = new Administrador();
                admin.setCpf(rs.getString("cpf") );
                admin.setNome(rs.getString("senha") );

                if (cpf.equals(rs.getString("cpf")) && senha.equals(rs.getString("senha"))){
                    System.out.println("Sim");
                    return true;
                }
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }
    
    //função auxiliar para verificar se o login de usuário é válido
    public boolean verifExistenciaUsuario(String cpf, String senha) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while( rs.next() ) {
                Usuario usuario = new Usuario();
                usuario.setCpf(rs.getString("cpf") );
                usuario.setNome( rs.getString("senha") );
               
                if (cpf.equals(rs.getString("cpf")) && senha.equals(rs.getString("senha"))){
                    return true;
                }
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }
    
    //o usuário é válido, porém suspenso
    public boolean verifUsuarioSuspenso(String cpf, String senha) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while( rs.next() ) {
                Usuario usuario = new Usuario();
                usuario.setCpf(rs.getString("cpf") );
                usuario.setNome( rs.getString("senha") );
               
                if (cpf.equals(rs.getString("cpf")) && senha.equals(rs.getString("senha")) && rs.getString("suspenso").equals("S")){
                    return true;
                }
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }
    
    //função auxiliar para pegar o ID do usuário logado
    public int getIdLogado(String cpf1){
        int id = 0;
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {
            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while( rs.next() ) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setCpf(rs.getString("cpf"));
                
                if (cpf1.equals(rs.getString("cpf"))){
                    return rs.getInt("id");
                }
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return 0;
    }
    
    //função auxiliar para pegar o nome do usuário logado
    public String getNomeLogado(String cpf1){
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {
            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while( rs.next() ) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                
                if (cpf1.equals(rs.getString("cpf"))){
                    return rs.getString("nome");
                }
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }
    
    //função auxiliar para pegar o nome do administrador logado
    public String getNomeLogadoAdmin(String cpf1){
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {
            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM administradores");
            while( rs.next() ) {
                Administrador admin = new Administrador();
                admin.setNome(rs.getString("nome"));
                admin.setCpf(rs.getString("cpf"));
                
                if (cpf1.equals(rs.getString("cpf"))){
                    return rs.getString("nome");
                }
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }
    //função auxiliar para pegar o status logado. Usada para os filtros funcionarem corretamente, identificando quando é usuário e quando é admin
    public String getValorSuspensoLogado(String cpf1){
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {
            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while( rs.next() ) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSuspenso(rs.getString("suspenso"));
                
                if (cpf1.equals(rs.getString("cpf"))){
                    return rs.getString("suspenso");
                }
            }
        }
        catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }
}