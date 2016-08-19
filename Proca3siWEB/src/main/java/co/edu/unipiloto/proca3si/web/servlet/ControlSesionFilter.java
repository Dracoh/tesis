package co.edu.unipiloto.proca3si.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.unipiloto.proca3si.web.DTO.UsuarioDTO;
import co.edu.unipiloto.proca3si.web.util.ManejadorSesion;

/**
 * Filtro que permite conocer si se esta dentro de una sesion o no como control
 * de seguridad de la aplicacion
 */
public class ControlSesionFilter implements Filter {
	private FilterConfig filterConfig = null;

	/**
	 * Constructor
	 */
	public ControlSesionFilter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		ManejadorSesion manejadorSesion = new ManejadorSesion();

		/*
		 * Examinar si estan tratando de pasar por otra petición que no sea jsf
		 * para no validar la sesión
		 */

		if (req.getRequestURI().equals("/rologistica/") || req.getRequestURI().endsWith("ingreso.xhtml") || req.getRequestURI().endsWith("index.xhtml")) {
			chain.doFilter(request, response);
		} else {
			String extension = req.getRequestURI().substring(req.getRequestURI().length() - 3).toUpperCase();

			// Si se presenta una excepcion al tratar de restaurar la pagina
			// lo manda a la pagina de inicio
			if (extension.equals("JPG") || extension.equals("GIF") || extension.equals("PNG") || extension.equals(".JS") || extension.equals("CSS") || extension.equals("TML")
					|| req.getRequestURI().endsWith("jsf.js.xhtml") || req.getRequestURI().endsWith("styles.css.xhtml") || req.getRequestURI().endsWith("ingreso.xhtml")
					|| req.getRequestURI().endsWith("index.xhtml") || req.getRequestURI().endsWith("proca3si/")) {
				try {
					chain.doFilter(request, response);
				} catch (Exception e) {
					manejadorSesion.crearSesion(req);
					filterConfig.getServletContext().getRequestDispatcher("/index.xhtml").forward(request, response);
					return;
				}
			} else {
				// Validar si la sesion existe sino envia al index
				HttpSession sesion = manejadorSesion.obtenerSesion(req);
				if (sesion != null) {
					// Pedir el usuario de la sesion
					UsuarioDTO usuario = (UsuarioDTO) manejadorSesion.extraerObjetoSesion("userSession", req);

					// No se han logueado en la aplicacion le manda un mensaje
					if (usuario == null || usuario.getUsuUsuario() == null) {
						manejadorSesion.crearSesion(req);
						filterConfig.getServletContext().getRequestDispatcher("/index.xhtml").forward(request, response);
						return;
					} else {
						chain.doFilter(request, response);
					}
				} else {
					manejadorSesion.crearSesion(req);
					filterConfig.getServletContext().getRequestDispatcher("/index.xhtml").forward(request, response);
					return;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		this.filterConfig = null;
	}
}
