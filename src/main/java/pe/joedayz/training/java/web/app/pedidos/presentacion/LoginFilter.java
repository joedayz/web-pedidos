package pe.joedayz.training.java.web.app.pedidos.presentacion;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.joedayz.training.java.web.app.pedidos.entidad.Usuario;


@WebFilter("/*")
public class LoginFilter implements Filter, ServletContextListener {

	public LoginFilter() {
	}

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		//System.out.println("doFilter...");

		HttpSession session = request.getSession(false);

		Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
		
	   String loginURL = request.getContextPath()+ "/faces/index.xhtml";
	   String resourcesURL = request.getContextPath()+ "/resources";
	   String resourcesFaces=request.getContextPath() +"/faces"+ ResourceHandler.RESOURCE_IDENTIFIER;
	   
		boolean loginRequest = request.getRequestURI().startsWith(loginURL);
		boolean resourcesRequest = request.getRequestURI().startsWith(resourcesURL);
		boolean resourceRequest = request.getRequestURI().startsWith(resourcesFaces);
		
		if (usuario != null || loginRequest || resourceRequest||resourcesRequest) {
				chain.doFilter(request, response);
		} else {
			
			response.sendRedirect(loginURL);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}

}
