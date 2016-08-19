/*
 * Aplicación: 						PROCA3SI
 * Nombre del archivo: 				ExcepcionDAO.java
 * Descripción: 					Clase encargada de manejar las excepciones 
 * 									que se presentan cuando se realizan 
 * 									operaciones de acceso a la base de datos
 * Autor: 							Oscar Enrique Pineros Ovalle - Corporación Universidad Piloto de Colombia.                              
 * Empresa: 						Universidad Piloto de Colombia
 * Fecha de creación: 				Abril 12, 2016
 * Fecha de la ultima Modificación:	Abril 12, 2016 
 */
package co.edu.proca3si.ejb.common.exception;

import javax.ejb.ApplicationException;

import org.apache.log4j.Logger;

/**
 * Clase encargada de manejar las excepciones a nivel de la aplicacion. Fallo en
 * algun componente del servidor de aplicaciones o similar.
 */
@SuppressWarnings("serial")
@ApplicationException(rollback = true)
public class ExcepcionAplicacion extends Exception {

	// Log general de la aplicacion
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Constructor
	 */
	public ExcepcionAplicacion() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param unMensaje
	 *            Mensaje que se muestra al usuario.
	 */
	public ExcepcionAplicacion(String unMensaje) {
		super(unMensaje);
		logger.error("ERROR PROCA3SI - APLICACION: " + unMensaje);
	}

	/**
	 * Constructor
	 * 
	 * @param unMensaje
	 *            Mensaje que se muestra al usuario.
	 * @param unComponente
	 *            Componente donde se genera la excepcion.
	 */
	public ExcepcionAplicacion(String unMensaje, String unComponente) {
		super(unMensaje + " : " + unComponente);
		logger.error("ERROR PROCA3SI - APLICACION - METODO " + unComponente + ": " + unMensaje);
	}

	/**
	 * Constructor
	 * 
	 * @param unMensaje
	 *            Mensaje que se muestra al usuario.
	 * @param unComponente
	 *            Componente donde se genera la excepcion.
	 * @param unComentario
	 *            Informacion adicional.
	 */
	public ExcepcionAplicacion(String unMensaje, String unComponente, String unComentario) {
		super(unMensaje + " : " + unComponente + " - '" + unComentario + "'");
		logger.error("ERROR PROCA3SI - APLICACION - METODO " + unComponente + ": " + unMensaje);
	}
}
