/**
 * 
 */
package co.edu.unipiloto.proca3si.web.DTO;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
