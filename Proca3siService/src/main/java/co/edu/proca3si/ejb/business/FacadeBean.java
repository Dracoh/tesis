/**
 * 
 */
package co.edu.proca3si.ejb.business;

import java.util.Properties;

import org.apache.log4j.Logger;

import co.edu.proca3si.ejb.util.enumerations.KindProperties;
import co.edu.proca3si.ejb.util.resources.Recursos;

/**
 * @author hellequin
 *
 */
public abstract class FacadeBean {

	// Recursos
	public Recursos recursos = new Recursos();
	// Propiedades del sistema
	protected Properties propertiesSystem = recursos.cargarPropiedades(KindProperties.mensajes_sistema.name());
	// Propiedades
	protected Properties propertiesResources = recursos.cargarPropiedades(KindProperties.recursos.name());
	// Log general de la aplicacion
	protected static Logger logger;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public FacadeBean() {
		logger = Logger.getLogger(this.getClass());
	}
}
