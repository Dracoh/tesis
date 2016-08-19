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
