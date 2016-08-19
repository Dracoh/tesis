/*
 * Aplicación: 						PROCA3SI
 * Nombre del archivo: 				ExcepcionDataBaseConnection.java
 * Descripción: 					Clase encargada de manejar las excepciones 
 * 									que se presentan cuando se autentica en la base de datos
 * Autor: 							Oscar Enrique Pineros Ovalle - Corporación Universidad Piloto de Colombia.                              
 * Empresa: 						Universidad Piloto de Colombia
 * Fecha de creación: 				Abril 24, 2016
 * Fecha de la ultima Modificación:	Abril 24, 2016 
 */
package co.edu.proca3si.ejb.common.exception;

import javax.ejb.ApplicationException;

import org.apache.log4j.Logger;

/**
 * Clase encargada de manejar las excepciones a nivel de la aplicacion. Fallo en
 * al tratar de autenticarse con la base de datos de seguridad.
 */
@SuppressWarnings("serial")
@ApplicationException(rollback = true)
public class ExcepcionDataBaseConnection extends Exception {

	// Log general de la aplicacion
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Constructor
	 */
	public ExcepcionDataBaseConnection() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param unMensaje
	 *            Mensaje que se muestra al usuario.
	 */
	public ExcepcionDataBaseConnection(String unMensaje) {
		super(unMensaje);
		logger.error("ERROR PROCA3SI - SECURITY AUTENTICATION: " + unMensaje);
	}

	/**
	 * Constructor
	 * 
	 * @param unMensaje
	 *            Mensaje que se muestra al usuario.
	 * @param unComponente
	 *            Componente donde se genera la excepcion.
	 */
	public ExcepcionDataBaseConnection(String unMensaje, String unComponente) {
		super(unMensaje + " : " + unComponente);
		logger.error("ERROR PROCA3SI - SECURITY AUTENTICATION - METODO " + unComponente + ": " + unMensaje);
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
	public ExcepcionDataBaseConnection(String unMensaje, String unComponente, String unComentario) {
		super(unMensaje + " : " + unComponente + " - '" + unComentario + "'");
		logger.error("ERROR PROCA3SI - SECURITY AUTENTICATION - METODO " + unComponente + ": " + unMensaje);
	}
}
