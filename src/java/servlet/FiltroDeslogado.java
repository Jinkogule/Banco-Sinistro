package servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroDeslogado implements Filter  {
    Login login = new Login();
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request2 = (HttpServletRequest) request;

        String nome_logado = (String) request2.getSession().getAttribute("nome");
        
        if (nome_logado != null){
            filterChain.doFilter(request, response);
        }
        else{
            ((HttpServletResponse)response).sendRedirect("index.jsp");
        }
    }
    public void destroy() {
    }
}