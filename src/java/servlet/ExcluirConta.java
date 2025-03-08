package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.UsuarioDAO;

@WebServlet(name = "ExcluirConta", urlPatterns = {"/ExcluirConta"})
public class ExcluirConta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
            if (id != 0) {

            UsuarioDAO usuariodao = new UsuarioDAO();

            if (usuariodao.excluirConta(id) == false){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroExcluirContaReferenciada.jsp");
                rd.forward(request, response);       
            }
            else{
                usuariodao.excluirConta(id);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoExclusaoConta.jsp");
                rd.forward(request, response);
            }
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
}