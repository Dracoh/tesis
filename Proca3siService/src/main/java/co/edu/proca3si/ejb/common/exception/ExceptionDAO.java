/*
 * Aplicación: 						PROCA3SI
 * Nombre del archivo: 				ExcepcionDAO.java
 * Descripción: 					Clase encargada de manejar las excepciones 
 * 									que se presentan cuando se realizan 
 * 									operaciones de acceso a la base de datos
 * Autor: 							Oscar Enrique Pineros Ovalle - Corporación Universidad Piloto de Colombia.                              
 * Empresa: 						Roldan Logistica - Serteport
 * Fecha de creación: 				Abril 12, 2016
 * Fecha de la ultima Modificación:	Abril 12, 2016 
 */
package co.edu.proca3si.ejb.common.exception;

import javax.ejb.ApplicationException;

import org.apache.log4j.Logger;

/**
 * @author hellequin
 *
 */
@SuppressWarnings("serial")
@ApplicationException(rollback = true)
public class ExceptionDAO extends Exception {

	// Log general de la aplicacion
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public ExceptionDAO() {
		super();
	}

	/**
	 * 
	 * CONSTRUCTOR SOBRECARGADO
	 * 
	 * @param message
	 */
	public ExceptionDAO(String message) {
		super(message);
		logger.error("ERROR PROCA3SI - PERSISTENCIA: " + message);
	}

	/**
	 * 
	 * CONSTRUCTOR SOBRECARGADO
	 * 
	 * @param unMensaje
	 * @param unComponente
	 */
	public ExceptionDAO(String unMensaje, String unComponente) {
		super(unMensaje + " : " + unComponente);
		logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO " + unComponente + ": " + unMensaje);
	}

	/**
	 * 
	 * CONSTRUCTOR SOBRECARGADO
	 * 
	 * @param unMensaje
	 * @param unComponente
	 * @param unComentario
	 */
	public ExceptionDAO(String unMensaje, String unComponente, String unComentario) {
		super(unMensaje + " : " + unComponente + " - '" + unComentario + "'");
		logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO " + unComponente + ": " + unMensaje);
	}
}
