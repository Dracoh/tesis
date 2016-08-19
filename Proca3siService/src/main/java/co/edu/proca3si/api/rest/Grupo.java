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
import co.edu.proca3si.ejb.business.dto.GrupoDTO;
import co.edu.proca3si.ejb.common.exception.ExcepcionAplicacion;
import co.edu.proca3si.ejb.common.exception.ExceptionDAO;

/**
 * @author hellequin
 *
 */
@Provider
@Path("/grupo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Grupo {
	@EJB
	private FacadeAdministracionBe facadeAdministracionBe;

	@POST
	@Path("/crear")
	public void crearGrupo(GrupoDTO grupoDTO) throws ExceptionDAO {
		facadeAdministracionBe.crearGrupo(grupoDTO);
	}

	@GET
	@Path("/consultar/{id}")
	public GrupoDTO consultarGrupoXId(@PathParam("id") long id) throws ExceptionDAO {
		GrupoDTO grupoDTO = facadeAdministracionBe.consultarGrupoXId(id);
		return grupoDTO;
	}

	@GET
	@Path("/consultarTodos/")
	public List<GrupoDTO> consultarGruposTodos() throws ExceptionDAO {
		List<GrupoDTO> lsGrupoDTOs = facadeAdministracionBe.consultarGruposTodos();
		return lsGrupoDTOs;
	}

	@GET
	@Path("/consultar/completo/{id}")
	public GrupoDTO consultarGrupoCompletoXId(@PathParam("id") long id) throws ExceptionDAO {
		GrupoDTO grupoDTO = facadeAdministracionBe.consultarGrupoCompletoXId(id);
		return grupoDTO;
	}

	@PUT
	@Path("/actualizar/")
	public void actualizarGrupo(GrupoDTO grupoDTO) throws ExceptionDAO {
		facadeAdministracionBe.actualizarGrupo(grupoDTO);
	}

	@PUT
	@Path("/actualizar/completo/")
	public void actualizarGrupoCompleto(GrupoDTO grupoDTO) throws ExceptionDAO, ExcepcionAplicacion {
		facadeAdministracionBe.actualizarGrupoCompleto(grupoDTO);
	}

	@DELETE
	@Path("/eliminar/")
	public void eliminarGrupo(GrupoDTO grupoDTO) throws ExceptionDAO {
		facadeAdministracionBe.eliminarGrupo(grupoDTO);
	}
}
