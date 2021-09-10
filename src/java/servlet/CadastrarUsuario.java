package servlet;

import app.Usuario;
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
import model.AdministradorDAO;
import model.conexaoBD;

@WebServlet(name = "CadastrarUsuario", urlPatterns = {"/CadastrarUsuario"})
public class CadastrarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String suspenso = request.getParameter("suspenso");
        
        if ((!nome.isEmpty()) && (!cpf.isEmpty()) && (!senha.isEmpty()) &&(!suspenso.isEmpty())) {

            Usuario usuario = new Usuario();

            usuario.setId(0);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setSenha(senha);
            usuario.setSuspenso(suspenso);
     
            AdministradorDAO admindao = new AdministradorDAO();

            if (verifCpfCadastrado(cpf) == true){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroCadastrarUsuarioExistente.jsp");
                rd.forward(request, response);
            }
            else{
                admindao.cadastrarUsuario(usuario);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoUsuario.jsp");
                rd.forward(request, response);
            }
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
    
    private Connection conexao;
    //função auxiliar para verificar se o usuário ja havia sido cadastrado    
    public boolean verifCpfCadastrado(String cpf) {
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
                usuario.setCpf(rs.getString("cpf"));
               
                if (cpf.equals(rs.getString("cpf"))){
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