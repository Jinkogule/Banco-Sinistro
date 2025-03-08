package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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