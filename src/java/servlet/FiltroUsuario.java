package servlet;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltroUsuario implements Filter  {
    Login login = new Login();
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request2 = (HttpServletRequest) request;

        String nome_logado = (String) request2.getSession().getAttribute("nome");
        String verif = (String) request2.getSession().getAttribute("verif");
        String existencia_status = (String) request2.getSession().getAttribute("existencia_status");

        if (verif == null && (existencia_status != null)){
            if (nome_logado != null){
                filterChain.doFilter(request, response);
            }
            else{
                request2.getSession().invalidate();
                ((HttpServletResponse)response).sendRedirect("index.jsp");
            }
        }
        else{
            request2.getSession().invalidate();
            ((HttpServletResponse)response).sendRedirect("index.jsp");
        }
    }


    public void destroy() {
    }
}