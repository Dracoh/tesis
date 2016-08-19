/**
 * 
 */
package co.edu.proca3si.ejb.business.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import co.edu.proca3si.ejb.persistence.entities.UsuarioRol;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class UsuarioRolDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long usuarioRolCodigo;
	@XmlElement
	private RolDTO rolDTO;
	@XmlElement
	private UsuarioDTO usuarioDTO;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public UsuarioRolDTO() {
	}

	/**
	 * 
	 * CONSTRUCTOR SOBRECARGADO
	 * 
	 * @param usuarioRol
	 */
	public UsuarioRolDTO(UsuarioRol usuarioRol) {
		this.usuarioRolCodigo = usuarioRol.getUroCodigo();

		/* Llaves Foraneas */
		// Rol
		if (usuarioRol.getRol() != null && usuarioRol.getRol().getRolCodigo() > 0L) {
			rolDTO = new RolDTO();
			rolDTO.setRolCodigo(usuarioRol.getRol().getRolCodigo());
		}
		// Usuario
		if (usuarioRol.getUsuario() != null && usuarioRol.getUsuario().getUsuCodigo() > 0L) {
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setUsuCodigo(usuarioRol.getUsuario().getUsuCodigo());
		}
	}

	/**
	 * @return the usuarioRolCodigo
	 */
	public long getusuarioRolCodigo() {
		return usuarioRolCodigo;
	}

	/**
	 * @param usuarioRolCodigo
	 *            the usuarioRolCodigo to set
	 */
	public void setusuarioRolCodigo(long usuarioRolCodigo) {
		this.usuarioRolCodigo = usuarioRolCodigo;
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
