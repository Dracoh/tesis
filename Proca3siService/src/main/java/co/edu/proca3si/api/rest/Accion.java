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
import co.edu.proca3si.ejb.business.dto.AccionDTO;
import co.edu.proca3si.ejb.common.exception.ExcepcionAplicacion;
import co.edu.proca3si.ejb.common.exception.ExceptionDAO;

/**
 * @author hellequin
 *
 */
@Provider
@Path("/accion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Accion {

	@EJB
	private FacadeAdministracionBe facadeAdministracionBe;

	@POST
	public void crearAccion(AccionDTO accionDTO) throws ExceptionDAO {
		facadeAdministracionBe.crearAccion(accionDTO);
	}

	@GET
	@Path("/{id: \\d+}")
	public AccionDTO consultarAccionXId(@PathParam("id") Long id) throws ExceptionDAO {
		AccionDTO accionDTO = facadeAdministracionBe.consultarAccionXId(id);
		return accionDTO;
	}

	@GET
	public List<AccionDTO> consultarAccionesTodas() throws ExceptionDAO {
		List<AccionDTO> lsAccionDTOs = facadeAdministracionBe.consultarAccionesTodas();
		return lsAccionDTOs;
	}

	@GET
	@Path("/{id: \\d+}/roles")
	public List<AccionDTO> consultarAccionesXIdRol(@PathParam("id") Long id) throws ExceptionDAO {
		List<AccionDTO> lsAccionDTOs = facadeAdministracionBe.consultarAccionesXIdRol(id);
		return lsAccionDTOs;
	}

	@PUT
	public void actualizarAccion(AccionDTO accionDTO) throws ExceptionDAO {
		facadeAdministracionBe.actualizarAccion(accionDTO);
	}

	@DELETE
	@Path("/{id: \\d+}")
	public void eliminarAccion(@PathParam("id") Long id) throws ExceptionDAO, ExcepcionAplicacion {
		facadeAdministracionBe.eliminarAccion(id);
	}

}
