/**
 * 
 */
package co.edu.proca3si.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import co.edu.proca3si.ejb.business.FacadeAdministracionBe;
import co.edu.proca3si.ejb.business.dto.UsuarioDTO;
import co.edu.proca3si.ejb.common.exception.ExcepcionAplicacion;
import co.edu.proca3si.ejb.common.exception.ExceptionDAO;

/**
 * @author hellequin
 *
 */
@Provider
@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Usuario {

	@EJB
	private FacadeAdministracionBe facadeAdministracionBe;

	@POST
	public void crearUsuario(UsuarioDTO usuarioDTO) throws ExceptionDAO, ExcepcionAplicacion {
		facadeAdministracionBe.crearUsuario(usuarioDTO);
	}

	@GET
	@Path("/{id: \\d+}")
	public UsuarioDTO consultarUsuarioxId(@PathParam("id") long id) throws ExceptionDAO {
		UsuarioDTO usuarioDTO = facadeAdministracionBe.consultarUsuarioSimpleXid(id);
		return usuarioDTO;
	}

	@GET
	public List<UsuarioDTO> consultarUsuariosTodos() throws ExceptionDAO {
		List<UsuarioDTO> lsUsuarioDTOs = facadeAdministracionBe.consultarUsuariosTodos();
		return lsUsuarioDTOs;
	}

	@GET
	@Path("consulta")
	public List<UsuarioDTO> consultarUsuariosTodosXCriterios(@QueryParam("user") String user, @QueryParam("username") String name) throws ExceptionDAO {
		List<UsuarioDTO> lsUsuarioDTOs = facadeAdministracionBe.consultarUsuariosXParametros(user, name);
		return lsUsuarioDTOs;
	}

	@PUT
	public void actualizarUsuario(UsuarioDTO usuarioDTO) throws ExceptionDAO, ExcepcionAplicacion {
		facadeAdministracionBe.actualizarUsuario(usuarioDTO);
	}

	@PUT
	@Path("Password")
	public boolean updatePassword(@QueryParam("id") Long idUser, @QueryParam("oldPassword") String contrasenia, @QueryParam("newPassword") String nuevaContrasenia)
			throws ExcepcionAplicacion, ExceptionDAO {
		return facadeAdministracionBe.cambiarContrasenia(idUser, contrasenia, nuevaContrasenia);
	}

	@DELETE
	@Path("/{id: \\d+}")
	public void eliminarUsuario(@PathParam("id") Long id) throws ExceptionDAO {
		facadeAdministracionBe.eliminarUsuario(id);
	}
}
