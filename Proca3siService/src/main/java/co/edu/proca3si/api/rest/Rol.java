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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import co.edu.proca3si.ejb.business.FacadeAdministracionBe;
import co.edu.proca3si.ejb.business.dto.RolDTO;
import co.edu.proca3si.ejb.common.exception.ExceptionDAO;

/**
 * @author hellequin
 *
 */
@Provider
@Path("/rol")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Rol {

	@EJB
	private FacadeAdministracionBe facadeAdministracionBe;

	@POST
	@Path("/crear")
	public void crearRol(RolDTO rolDTO) throws ExceptionDAO {
		facadeAdministracionBe.crearRol(rolDTO);
	}

	@GET
	@Path("/consultar/{id}")
	public RolDTO consultarRolXId(@PathParam("id") long id) throws ExceptionDAO {
		return facadeAdministracionBe.consultarRolXId(id);
	}

	@GET
	@Path("/consultarTodos/")
	public List<RolDTO> consultarRolesTodos() throws ExceptionDAO {
		return facadeAdministracionBe.consultarRolesTodos();
	}

	@PUT
	@Path("/actualizar/")
	public void actualizarRol(RolDTO rolDTO) throws ExceptionDAO {
		facadeAdministracionBe.actualizarRol(rolDTO);
	}

	@DELETE
	@Path("/eliminar/")
	public void eliminarRol(RolDTO rolDTO) throws ExceptionDAO {
		facadeAdministracionBe.eliminarRol(rolDTO);
	}

}
