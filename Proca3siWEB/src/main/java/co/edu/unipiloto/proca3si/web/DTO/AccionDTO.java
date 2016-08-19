/**
 * 
 */
package co.edu.unipiloto.proca3si.web.DTO;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class AccionDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long acnCodigo;
	private String acnNombre;
	private String acnDescripcion;
	private boolean acnEstado;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public AccionDTO() {
	}

	/**
	 * @return the acnCodigo
	 */
	public long getAcnCodigo() {
		return acnCodigo;
	}

	/**
	 * @param acnCodigo
	 *            the acnCodigo to set
	 */
	public void setAcnCodigo(long acnCodigo) {
		this.acnCodigo = acnCodigo;
	}

	/**
	 * @return the acnNombre
	 */
	public String getAcnNombre() {
		return acnNombre;
	}

	/**
	 * @param acnNombre
	 *            the acnNombre to set
	 */
	public void setAcnNombre(String acnNombre) {
		this.acnNombre = acnNombre;
	}

	/**
	 * @return the acnDescripcion
	 */
	public String getAcnDescripcion() {
		return acnDescripcion;
	}

	/**
	 * @param acnDescripcion
	 *            the acnDescripcion to set
	 */
	public void setAcnDescripcion(String acnDescripcion) {
		this.acnDescripcion = acnDescripcion;
	}

	/**
	 * @return the acnEstado
	 */
	public boolean isAcnEstado() {
		return acnEstado;
	}

	/**
	 * @param acnEstado
	 *            the acnEstado to set
	 */
	public void setAcnEstado(boolean acnEstado) {
		this.acnEstado = acnEstado;
	}
}
