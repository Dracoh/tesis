/**
 * 
 */
package co.edu.proca3si.ejb.business.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import co.edu.proca3si.ejb.persistence.entities.GrupoRol;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class GrupoRolDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long grupoRolCodigo;
	@XmlElement
	private GrupoDTO grupoDTO;
	@XmlElement
	private RolDTO rolDTO;

	/**
	 * 
	 * CONSTRUCTOR SOBRECARGADO
	 * 
	 * @param grupoRol
	 */
	public GrupoRolDTO(GrupoRol grupoRol) {
		this.grupoRolCodigo = grupoRol.getGrlCodigo();

		/* Llaves Foraneas */
		// Grupo
		if (grupoRol.getGrupo() != null && grupoRol.getGrupo().getGpoCodigo() > 0L) {
			grupoDTO = new GrupoDTO();
			grupoDTO.setGpoCodigo(grupoRol.getGrupo().getGpoCodigo());
		}
		// Rol
		if (grupoRol.getRol() != null && grupoRol.getRol().getRolCodigo() > 0L) {
			rolDTO = new RolDTO();
			rolDTO.setRolCodigo(grupoRol.getRol().getRolCodigo());
		}
	}

	/**
	 * @return the grupoRolCodigo
	 */
	public long getGrupoRolCodigo() {
		return grupoRolCodigo;
	}

	/**
	 * @param grupoRolCodigo
	 *            the grupoRolCodigo to set
	 */
	public void setGrupoRolCodigo(long grupoRolCodigo) {
		this.grupoRolCodigo = grupoRolCodigo;
	}

	/**
	 * @return the grupoDTO
	 */
	public GrupoDTO getGrupoDTO() {
		return grupoDTO;
	}

	/**
	 * @param grupoDTO
	 *            the grupoDTO to set
	 */
	public void setGrupoDTO(GrupoDTO grupoDTO) {
		this.grupoDTO = grupoDTO;
	}

	/**
	 * @return the rolDTO
	 */
	public RolDTO getRolDTO() {
		return rolDTO;
	}

	/**
	 * @param rolDTO
	 *            the rolDTO to set
	 */
	public void setRolDTO(RolDTO rolDTO) {
		this.rolDTO = rolDTO;
	}
}
