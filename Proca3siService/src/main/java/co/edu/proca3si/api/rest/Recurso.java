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
import co.edu.proca3si.ejb.business.dto.RecursoDTO;
import co.edu.proca3si.ejb.common.exception.ExceptionDAO;

/**
 * @author hellequin
 *
 */
@Provider
@Path("/recurso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Recurso {

	@EJB
	private FacadeAdministracionBe facadeAdministracionBe;

	@POST
	public Long crearRecurso(RecursoDTO recursoDTO) throws ExceptionDAO {
		return facadeAdministracionBe.crearRecurso(recursoDTO);
	}

	@GET
	@Path("/{id: \\d+}")
	public RecursoDTO consultarAccionXId(@PathParam("id") long id) throws ExceptionDAO {
		return facadeAdministracionBe.consultarRecursoXId(id);
	}

	@GET
	public List<RecursoDTO> consultarRecursosTodos() throws ExceptionDAO {
		return facadeAdministracionBe.consultarRecursosTodos();
	}

	@GET
	@Path("/usuario/{id: \\d+}/recurso")
	public List<RecursoDTO> consultarRecursosxIdUser(@PathParam("id") Long idUser) throws ExceptionDAO {
		return facadeAdministracionBe.consultarRecursosXUsuario(idUser);
	}

	@PUT
	public boolean actualizarRecurso(RecursoDTO recursoDTO) throws ExceptionDAO {
		return facadeAdministracionBe.actualizarRecusrso(recursoDTO);
	}

	@DELETE
	public boolean eliminarRecurso(RecursoDTO recursoDTO) throws ExceptionDAO {
		return facadeAdministracionBe.eliminarRecurso(recursoDTO);
	}

}
