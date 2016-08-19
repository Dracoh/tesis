/**
 * 
 */
package co.edu.proca3si.ejb.business.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import co.edu.proca3si.ejb.persistence.entities.Aplicacion;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class AplicacionDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long appCodigo;
	private String appNombre;
	private String appToken;
	private String appEmail;
	private boolean appEmailConfirmed;
	@XmlElement
	private List<RecursoDTO> lsRecursoDTOs;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public AplicacionDTO() {
		lsRecursoDTOs = new ArrayList<RecursoDTO>();
	}

	/**
	 * 
	 * CONSTRUCTOR SONRECARGADO
	 */
	public AplicacionDTO(Aplicacion aplicacion) {

		this.appCodigo = aplicacion.getAppCodigo();
		this.appNombre = aplicacion.getAppNombre();
		this.appToken = aplicacion.getAppToken();
		this.appEmailConfirmed = aplicacion.isAppEmailConfirmed();
		this.appEmail = aplicacion.getAppEmail();

		/* Listas */
		lsRecursoDTOs = new ArrayList<RecursoDTO>();
	}

	/**
	 * @return the appCodigo
	 */
	public long getAppCodigo() {
		return appCodigo;
	}

	/**
	 * @param appCodigo
	 *            the appCodigo to set
	 */
	public void setAppCodigo(long appCodigo) {
		this.appCodigo = appCodigo;
	}

	/**
	 * @return the appNombre
	 */
	public String getAppNombre() {
		return appNombre;
	}

	/**
	 * @param appNombre
	 *            the appNombre to set
	 */
	public void setAppNombre(String appNombre) {
		this.appNombre = appNombre;
	}

	/**
	 * @return the appToken
	 */
	public String getAppToken() {
		return appToken;
	}

	/**
	 * @param appToken
	 *            the appToken to set
	 */
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	/**
	 * @return the appEmailConfirmed
	 */
	public boolean isAppEmailConfirmed() {
		return appEmailConfirmed;
	}

	/**
	 * @param appEmailConfirmed
	 *            the appEmailConfirmed to set
	 */
	public void setAppEmailConfirmed(boolean appEmailConfirmed) {
		this.appEmailConfirmed = appEmailConfirmed;
	}

	/**
	 * @return the appEmail
	 */
	public String getAppEmail() {
		return appEmail;
	}

	/**
	 * @param appEmail
	 *            the appEmail to set
	 */
	public void setAppEmail(String appEmail) {
		this.appEmail = appEmail;
	}

	/**
	 * @return the lsRecursoDTOs
	 */
	public List<RecursoDTO> getLsRecursoDTOs() {
		return lsRecursoDTOs;
	}

	/**
	 * @param lsRecursoDTOs
	 *            the lsRecursoDTOs to set
	 */
	public void setLsRecursoDTOs(List<RecursoDTO> lsRecursoDTOs) {
		this.lsRecursoDTOs = lsRecursoDTOs;
	}
}
