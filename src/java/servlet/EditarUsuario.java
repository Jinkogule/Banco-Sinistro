package servlet;

import app.Usuario;
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
import model.AdministradorDAO;
import model.conexaoBD;

@WebServlet(name = "EditarUsuario", urlPatterns = {"/EditarUsuario"})
public class EditarUsuario extends HttpServlet {

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

            if (verifNaoHouveAlteracao(nome, senha, suspenso, cpf) == true){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroEditarUsuarioSemAlteracao.jsp");
                rd.forward(request, response);
            }
            else{
                admindao.editarUsuario(usuario);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoEditarUsuario.jsp");
                rd.forward(request, response);
            }          
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
    
    private Connection conexao;
    //função para verificar se a edição de um usuário não possui nenhuma alteração
    private boolean verifNaoHouveAlteracao(String nome, String senha, String suspenso, String cpf) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE cpf ='"+cpf+"'");
            while( rs.next() ) {
            
                if (nome.equals(rs.getString("nome")) && senha.equals(rs.getString("senha")) && suspenso.equals(rs.getString("suspenso"))){
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