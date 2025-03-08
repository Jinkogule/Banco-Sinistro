package servlet;

import app.Lancamento;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.UsuarioDAO;

@WebServlet(name = "CadastrarLancamento", urlPatterns = {"/CadastrarLancamento"})
public class CadastrarLancamento extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_conta = Integer.parseInt(request.getParameter("id_conta"));
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
        Float valor = Float.parseFloat(request.getParameter("valor"));
        String operacao = (String) request.getParameter("operacao");
        String data = (String) request.getParameter("data");
        String descricao = (String) request.getParameter("descricao");
        
        if ((id_conta != 0) && (id_categoria != 0) && (valor != 0) && (!operacao.isEmpty()) && (!data.isEmpty())) {

            Lancamento lancamento = new Lancamento();

            lancamento.setId(0);
            lancamento.setId_conta(id_conta);
            lancamento.setId_categoria(id_categoria);
            lancamento.setValor(valor);
            lancamento.setOperacao(operacao);
            lancamento.setData(data);
            lancamento.setDescricao(descricao);
  
            UsuarioDAO usuario = new UsuarioDAO();

            usuario.cadastrarLancamento(lancamento);
            RequestDispatcher rd = request.getRequestDispatcher("/SucessoLancamento.jsp");
            rd.forward(request, response);
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
}