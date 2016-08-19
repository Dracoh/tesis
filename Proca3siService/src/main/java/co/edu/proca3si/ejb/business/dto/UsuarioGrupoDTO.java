/**
 * 
 */
package co.edu.proca3si.ejb.business.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import co.edu.proca3si.ejb.persistence.entities.UsuarioGrupo;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class UsuarioGrupoDTO implements Serializable {
	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long usuarioGrupoCodigo;
	private boolean ugoEstado;
	@XmlElement
	private GrupoDTO grupoDTO;
	@XmlElement
	private UsuarioDTO usuarioDTO;

	/**
	 * 
	 * CONSTRUCTOR
	 * 
	 * @param usuarioGrupo
	 */
	public UsuarioGrupoDTO(UsuarioGrupo usuarioGrupo) {
		this.usuarioGrupoCodigo = usuarioGrupo.getUgoCodigo();

		/* Llaves Foraneas */
		// Grupo
		if (usuarioGrupo.getGrupo() != null && usuarioGrupo.getGrupo().getGpoCodigo() >= 0L) {
			grupoDTO = new GrupoDTO();
			grupoDTO.setGpoCodigo(usuarioGrupo.getGrupo().getGpoCodigo());
		}
		// Usuario
		if (usuarioGrupo.getUsuario() != null && usuarioGrupo.getUsuario().getUsuCodigo() > 0L) {
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setUsuCodigo(usuarioGrupo.getUsuario().getUsuCodigo());
		}
	}

	/**
	 * @return the usuarioGrupoCodigo
	 */
	public long getUsuarioGrupoCodigo() {
		return usuarioGrupoCodigo;
	}

	/**
	 * @param usuarioGrupoCodigo
	 *            the usuarioGrupoCodigo to set
	 */
	public void setUsuarioGrupoCodigo(long usuarioGrupoCodigo) {
		this.usuarioGrupoCodigo = usuarioGrupoCodigo;
	}

	/**
	 * @return the ugoEstado
	 */
	public boolean isUgoEstado() {
		return ugoEstado;
	}

	/**
	 * @param ugoEstado
	 *            the ugoEstado to set
	 */
	public void setUgoEstado(boolean ugoEstado) {
		this.ugoEstado = ugoEstado;
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
	 * @return the usuarioDTO
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	/**
	 * @param usuarioDTO
	 *            the usuarioDTO to set
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
}
