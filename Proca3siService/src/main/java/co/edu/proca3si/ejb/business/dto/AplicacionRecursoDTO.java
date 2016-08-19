/**
 * 
 */
package co.edu.proca3si.ejb.business.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import co.edu.proca3si.ejb.persistence.entities.AplicacionRecurso;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class AplicacionRecursoDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long aprCodigo;
	@XmlElement
	private AplicacionDTO aplicacionDTO;
	@XmlElement
	private RecursoDTO recursoDTO;

	/**
	 * 
	 * CONSTRUCTOR SOBRECARGADO
	 */
	public AplicacionRecursoDTO(AplicacionRecurso aplicacionRecurso) {

		this.aprCodigo = aplicacionRecurso.getAprCodigo();

		/* Llaves Foraneas */
		// Aplicacion
		if (aplicacionRecurso.getAplicacion() != null && aplicacionRecurso.getAplicacion().getAppCodigo() > 0L) {
			this.aplicacionDTO = new AplicacionDTO();
			this.aplicacionDTO.setAppCodigo(aplicacionRecurso.getAplicacion().getAppCodigo());
		}
		// Recurso
		if (aplicacionRecurso.getRecurso() != null && aplicacionRecurso.getRecurso().getRecCodigo() > 0L) {
			this.recursoDTO = new RecursoDTO();
			this.recursoDTO.setRecCodigo(aplicacionRecurso.getRecurso().getRecCodigo());
		}
	}

	/**
	 * @return the aprCodigo
	 */
	public long getAprCodigo() {
		return aprCodigo;
	}

	/**
	 * @param aprCodigo
	 *            the aprCodigo to set
	 */
	public void setAprCodigo(long aprCodigo) {
		this.aprCodigo = aprCodigo;
	}

	/**
	 * @return the aplicacionDTO
	 */
	public AplicacionDTO getAplicacionDTO() {
		return aplicacionDTO;
	}

	/**
	 * @param aplicacionDTO
	 *            the aplicacionDTO to set
	 */
	public void setAplicacionDTO(AplicacionDTO aplicacionDTO) {
		this.aplicacionDTO = aplicacionDTO;
	}

	/**
	 * @return the recursoDTO
	 */
	public RecursoDTO getRecursoDTO() {
		return recursoDTO;
	}

	/**
	 * @param recursoDTO
	 *            the recursoDTO to set
	 */
	public void setRecursoDTO(RecursoDTO recursoDTO) {
		this.recursoDTO = recursoDTO;
	}
}
