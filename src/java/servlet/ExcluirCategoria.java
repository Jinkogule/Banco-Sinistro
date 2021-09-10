package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AdministradorDAO;

@WebServlet(name = "ExcluirCategoria", urlPatterns = {"/ExcluirCategoria"})
public class ExcluirCategoria extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descricao = request.getParameter("descricao");
        
        if ((!descricao.isEmpty())) {
            AdministradorDAO admindao = new AdministradorDAO();

            if (admindao.excluirCategoria(descricao) == false){
                RequestDispatcher rd = request.getRequestDispatcher("/ErroExcluirCategoriaReferenciada.jsp");
                rd.forward(request, response);       
            }
            else{
                admindao.excluirCategoria(descricao);
                RequestDispatcher rd = request.getRequestDispatcher("/SucessoExclusaoCategoria.jsp");
                rd.forward(request, response);
            }
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
}