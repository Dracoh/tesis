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
