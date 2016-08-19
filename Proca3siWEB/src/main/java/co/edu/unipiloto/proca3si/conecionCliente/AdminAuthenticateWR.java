/**
 * 
 */
package co.edu.unipiloto.proca3si.conecionCliente;

import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import co.edu.unipiloto.proca3si.web.DTO.UsuarioDTO;
import co.edu.unipiloto.proca3si.web.util.enumerations.KindProperties;
import co.edu.unipiloto.proca3si.web.util.resources.Recursos;

/**
 * @author Oscar Pineros / Tesis UPC Date: 8/05/2016
 */
public class AdminAuthenticateWR {

	// Recursos
	protected Recursos recursos = new Recursos();
	// Propiedades del sistema
	protected Properties propertiesSystem = recursos.cargarPropiedades(KindProperties.recursos.name());
	/**
	 * PARAMETROS
	 */
	private static String urlProca3si;

	/**
	 * CONTRUCTOR
	 */
	public AdminAuthenticateWR() {
		urlProca3si = propertiesSystem.getProperty("urlProca3si") + "login";
	}

	/**
	 * 
	 * Autor: hellequin
	 * 
	 * @param user
	 * @param password
	 * @return Fecha de Cracion: May 9, 2016
	 * @throws Exception
	 */
	public UsuarioDTO autenticarUsuario(String user, String password) throws Exception {
		try {
			Client client = ClientBuilder.newClient();
			System.out.println("autenticando en ...."+urlProca3si);
			return client.target(urlProca3si).queryParam("username", user).queryParam("password", password).request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(null, MediaType.APPLICATION_JSON), UsuarioDTO.class);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
}
