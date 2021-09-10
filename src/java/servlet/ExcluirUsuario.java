package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AdministradorDAO;

@WebServlet(name = "ExcluirUsuario", urlPatterns = {"/ExcluirUsuario"})
public class ExcluirUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");

        if ((!cpf.isEmpty())) {

            AdministradorDAO admindao = new AdministradorDAO();

            if (admindao.excluirUsuario(cpf) == false){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroExcluirUsuarioReferenciado.jsp");
                rd.forward(request, response);       
            }
            else{
                admindao.excluirUsuario(cpf);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoExclusaoUsuario.jsp");
                rd.forward(request, response);
            }
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
}