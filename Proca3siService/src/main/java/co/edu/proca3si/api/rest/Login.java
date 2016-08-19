/**
 * 
 */
package co.edu.proca3si.api.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import co.edu.proca3si.ejb.business.FacadeApiBean;
import co.edu.proca3si.ejb.business.dto.UsuarioDTO;
import co.edu.proca3si.ejb.common.exception.ExcepcionAplicacion;
import co.edu.proca3si.ejb.common.exception.ExcepcionDataBaseConnection;
import co.edu.proca3si.ejb.common.exception.ExceptionDAO;

/**
 * @author hellequin
 *
 */
@Provider
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Login {

	@EJB
	private FacadeApiBean facadeApiBean;

	@POST
	public UsuarioDTO userAuth(@QueryParam("username") String username, @QueryParam("password") String password)
			throws ExceptionDAO, ExcepcionAplicacion, ExcepcionDataBaseConnection {
		return facadeApiBean.autenticarUsuario(username, password);
	}
}
