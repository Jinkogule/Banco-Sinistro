package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.AdministradorDAO;

@WebServlet(name = "LiberarUsuario", urlPatterns = {"/LiberarUsuario"})
public class LiberarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String cpf = request.getParameter("cpf");
        
        if ((!cpf.isEmpty())) {

            AdministradorDAO admindao = new AdministradorDAO();

            admindao.liberarUsuario(cpf);
            RequestDispatcher rd = request.getRequestDispatcher("/SucessoLiberarUsuario.jsp");
            rd.forward(request, response);
          
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
}