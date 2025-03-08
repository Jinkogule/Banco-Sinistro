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

@WebServlet(name = "EditarCategoria", urlPatterns = {"/EditarCategoria"})
public class EditarCategoria extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int idInt = Integer.parseInt(id);
      
        String descricao = request.getParameter("descricao");

        if ((!descricao.isEmpty()) && (!id.isEmpty())) {

            Categoria cat = new Categoria();

            cat.setId(idInt);
            cat.setDescricao(descricao);
     
            AdministradorDAO admindao = new AdministradorDAO();

            
            if (verifNaoHouveAlteracao(descricao) == true){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroEditarCategoriaSemAlteracao.jsp");
                rd.forward(request, response);
            }
            else{
                admindao.editarCategoria(cat);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoEditarCategoria.jsp");
                rd.forward(request, response);
            }   
            
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
    
    private Connection conexao;
    //função para verificar se a edição de uma categoria não possui nenhuma alteração
    private boolean verifNaoHouveAlteracao(String descricao) {
        try {
            conexao = conexaoBD.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
        try {            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias WHERE descricao ='"+descricao+"'");
            while( rs.next() ) {              

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