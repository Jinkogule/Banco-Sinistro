package servlet;

import app.Categoria;
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

@WebServlet(name = "CadastrarCategoria", urlPatterns = {"/CadastrarCategoria"})
public class CadastrarCategoria extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descricao = request.getParameter("descricao");
        
        if ((!descricao.isEmpty())) {

            Categoria categoria = new Categoria();

            categoria.setId(0);
            categoria.setDescricao(descricao);
  
            AdministradorDAO admindao = new AdministradorDAO();

            if (verifCatCadastrada(descricao) == true){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroCadastrarCategoriaExistente.jsp");
                rd.forward(request, response);
            }
            else{
                admindao.cadastrarCategoria(categoria);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoCategoria.jsp");
                rd.forward(request, response);
            }
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
    
    private Connection conexao;
    //função auxiliar para verificar se a categoria já havia sido cadastrado    
    public boolean verifCatCadastrada(String descricao) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias");
            while( rs.next() ) {
                Categoria cat = new Categoria();
                cat.setDescricao(rs.getString("descricao") );
               
                
                //usuário com cpf e senha válidos, porém suspenso
                if (descricao.equals(rs.getString("descricao"))){
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