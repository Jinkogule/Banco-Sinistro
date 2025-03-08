package servlet;

import app.Administrador;
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

@WebServlet(name = "EditarAdministrador", urlPatterns = {"/EditarAdministrador"})
public class EditarAdministrador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        
        if ((!nome.isEmpty()) && (!cpf.isEmpty()) && (!senha.isEmpty())) {

            Administrador admin = new Administrador();

            admin.setId(0);
            admin.setNome(nome);
            admin.setCpf(cpf);
            admin.setSenha(senha);
     
            AdministradorDAO admindao = new AdministradorDAO();

            if (verifNaoHouveAlteracao(nome, senha, cpf)){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroEditarAdministradorSemAlteracao.jsp");
                rd.forward(request, response);
            }
            else{
                admindao.editarAdministrador(admin);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoEditarAdministrador.jsp");
                rd.forward(request, response); 
            }
            
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
    
    private Connection conexao;
    //função para verificar se a edição de um administrador não possui nenhuma alteração
    private boolean verifNaoHouveAlteracao(String nome, String senha, String cpf) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM administradores WHERE cpf ='"+cpf+"'");
            while( rs.next() ) {              

                if (nome.equals(rs.getString("nome")) && senha.equals(rs.getString("senha"))){
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