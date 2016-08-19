/**
 * 
 */
package co.edu.unipiloto.proca3si.conecionCliente;

import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import co.edu.unipiloto.proca3si.web.DTO.AccionDTO;
import co.edu.unipiloto.proca3si.web.DTO.RecursoDTO;
import co.edu.unipiloto.proca3si.web.util.enumerations.KindProperties;
import co.edu.unipiloto.proca3si.web.util.resources.Recursos;

/**
 * @author Oscar Pineros / Tesis UPC Date: 8/05/2016
 */
public class AdminAdministracionWR {

	// Recursos
	protected Recursos recursos = new Recursos();
	// Propiedades del sistema
	protected Properties propertiesSystem = recursos.cargarPropiedades(KindProperties.recursos.name());

	/**
	 * PARAMETROS
	 */
	private String urlProca3si;

	/**
	 * CONTRUCTOR
	 */
	public AdminAdministracionWR() {
		urlProca3si = propertiesSystem.getProperty("urlProca3si");
	}

	/**
	 * Metodo que consulta todos los recursos del usuario
	 * 
	 * Autor: hellequin
	 * 
	 * @param idUser
	 * @return Fecha de Cracion: May 9, 2016
	 */
	public List<RecursoDTO> consultarRecursosXidUsusario(Long idUser) {
		Client client = ClientBuilder.newClient();

		WebTarget messages = client.target(urlProca3si + "recurso/usuario/{id}/recurso");
		return messages.resolveTemplate("id", idUser).request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<RecursoDTO>>() {
		});
	}

	/**
	 * Metodo que consulta todas las acciones que puede realizar un usuario en
	 * un recurso determinado
	 * 
	 * Autor: hellequin
	 * 
	 * @param idUser
	 * @param idRecurso
	 * @return Fecha de Cracion: May 9, 2016
	 */
	public List<AccionDTO> consultarAccionesRecursoUsuario(Long idUser, Long idRecurso) {
		Client client = ClientBuilder.newClient();
		WebTarget messages = client.target(urlProca3si + "");
		return messages.resolveTemplate("id", idUser).resolveTemplate("id", idRecurso).request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<AccionDTO>>() {
		});
	}
}
