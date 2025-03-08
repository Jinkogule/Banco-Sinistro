package model;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "Conexao", urlPatterns = {"/Conexao"})
public class conexaoBD extends HttpServlet {

    private static Connection conexao = null;
    
    public static Connection criaConexao() throws SQLException {
        if ( conexao == null ) {
            try {             
                Class.forName("com.mysql.cj.jdbc.Driver");                       
                System.out.println("Driver foi carregado!");
                
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/financeiro?useSSL=false&serverTimezone=UTC", "root", "12345");
                System.out.println("Conexão realizada com sucesso!");
            }
            catch( ClassNotFoundException e ) {
                System.out.println("Driver não foi localizado!");
            }
        }
        return conexao;
    }
}
