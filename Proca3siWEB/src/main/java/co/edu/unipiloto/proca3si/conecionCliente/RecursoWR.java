package co.edu.unipiloto.proca3si.conecionCliente;

import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import co.edu.unipiloto.proca3si.web.DTO.RecursoDTO;
import co.edu.unipiloto.proca3si.web.DTO.UsuarioDTO;
import co.edu.unipiloto.proca3si.web.util.enumerations.KindProperties;
import co.edu.unipiloto.proca3si.web.util.enumerations.Operation;
import co.edu.unipiloto.proca3si.web.util.resources.Recursos;

public class RecursoWR {

	protected Recursos recursos = new Recursos();
	protected Properties propertiesSystem = recursos.cargarPropiedades(KindProperties.recursos.name());
	private static final String PATH_PROVEEDOR = "recurso"; 
	private static final String PATH_CONSULTAR = "/consultarTodos";
	private static final String PATH_CREAR = "/crear";
	private static final String PATH_ACTUALIZAR = "/actualizar";
	private static String urlProca3si;
	private static String urlService; 

	public RecursoWR(String method) {
		urlProca3si = propertiesSystem.getProperty("urlProca3si");
	    if(method.compareTo(Operation.CONSULTAR.getOperacion()) == 0){
	    	urlService = urlProca3si.concat(PATH_PROVEEDOR).concat(PATH_CONSULTAR); 
	    }else if(method.compareTo(Operation.CREAR.getOperacion()) == 0){
	    	urlService = urlProca3si.concat(PATH_PROVEEDOR).concat(PATH_CREAR);
	    }else if(method.compareTo(Operation.ACTUALIZAR.getOperacion()) == 0){
	    	urlService = urlProca3si.concat(PATH_PROVEEDOR).concat(PATH_ACTUALIZAR);
	    }		
	}

	public RecursoDTO crearRecurso(RecursoDTO recursoDTO) throws Exception {
		Client client = ClientBuilder.newClient();
		System.out.println("Ejecutando: "+urlService);
		return client.target(urlService).request(MediaType.APPLICATION_JSON)
				     .post(Entity.entity(recursoDTO, MediaType.APPLICATION_JSON), RecursoDTO.class);
	}        
	
	public RecursoDTO editarRecurso(RecursoDTO recursoDTO) throws Exception {
		Client client = ClientBuilder.newClient();
		System.out.println("Ejecutando: "+urlService);
		return client.target(urlService).request(MediaType.APPLICATION_JSON)
				     .put(Entity.entity(recursoDTO, MediaType.APPLICATION_JSON), RecursoDTO.class);
	}
	
	public List<RecursoDTO> getRecursos() throws Exception {
		try {
			Client client = ClientBuilder.newClient();
			System.out.println("Ejecutando: "+urlService);
			WebTarget messages = client.target(urlService);
			return messages.request(MediaType.APPLICATION_JSON_TYPE)
					       .get(new GenericType<List<RecursoDTO>>() {} );
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
}
