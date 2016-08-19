/**
 * 
 */
package co.edu.unipiloto.proca3si.web.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class UsuarioDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long usuCodigo;
	private String usuUsuario;
	private String usuNombre;
	private String usuApellido;
	private String usuDireccion;
	private Date usuFechaNacimiento;
	private boolean usuEstado;
	@XmlElement
	private UserMembershipDTO userMembershipDTO;
	@XmlElement
	private List<RolDTO> lsRolDTOs;
	@XmlElement
	private List<GrupoDTO> lsGrupoDTOs;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public UsuarioDTO() {
		lsRolDTOs = new ArrayList<RolDTO>();
		lsGrupoDTOs = new ArrayList<GrupoDTO>();
	}

	/**
	 * @return the usuCodigo
	 */
	public long getUsuCodigo() {
		return usuCodigo;
	}

	/**
	 * @param usuCodigo
	 *            the usuCodigo to set
	 */
	public void setUsuCodigo(long usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	/**
	 * @return the usuUsuario
	 */
	public String getUsuUsuario() {
		return usuUsuario;
	}

	/**
	 * @param usuUsuario
	 *            the usuUsuario to set
	 */
	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

	/**
	 * @return the usuNombre
	 */
	public String getUsuNombre() {
		return usuNombre;
	}

	/**
	 * @param usuNombre
	 *            the usuNombre to set
	 */
	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	/**
	 * @return the usuApellido
	 */
	public String getUsuApellido() {
		return usuApellido;
	}

	/**
	 * @param usuApellido
	 *            the usuApellido to set
	 */
	public void setUsuApellido(String usuApellido) {
		this.usuApellido = usuApellido;
	}

	/**
	 * @return the usuDireccion
	 */
	public String getUsuDireccion() {
		return usuDireccion;
	}

	/**
	 * @param usuDireccion
	 *            the usuDireccion to set
	 */
	public void setUsuDireccion(String usuDireccion) {
		this.usuDireccion = usuDireccion;
	}

	/**
	 * @return the usuFechaNacimiento
	 */
	public Date getUsuFechaNacimiento() {
		return usuFechaNacimiento;
	}

	/**
	 * @param usuFechaNacimiento
	 *            the usuFechaNacimiento to set
	 */
	public void setUsuFechaNacimiento(Date usuFechaNacimiento) {
		this.usuFechaNacimiento = usuFechaNacimiento;
	}

	/**
	 * @return the usuEstado
	 */
	public boolean isUsuEstado() {
		return usuEstado;
	}

	/**
	 * @param usuEstado
	 *            the usuEstado to set
	 */
	public void setUsuEstado(boolean usuEstado) {
		this.usuEstado = usuEstado;
	}

	/**
	 * @return the userMembershipDTO
	 */
	public UserMembershipDTO getUserMembershipDTO() {
		return userMembershipDTO;
	}

	/**
	 * @param userMembershipDTO
	 *            the userMembershipDTO to set
	 */
	public void setUserMembershipDTO(UserMembershipDTO userMembershipDTO) {
		this.userMembershipDTO = userMembershipDTO;
	}

	/**
	 * @return the lsRolDTOs
	 */
	public List<RolDTO> getLsRolDTOs() {
		return lsRolDTOs;
	}

	/**
	 * @param lsRolDTOs
	 *            the lsRolDTOs to set
	 */
	public void setLsRolDTOs(List<RolDTO> lsRolDTOs) {
		this.lsRolDTOs = lsRolDTOs;
	}

	/**
	 * @return the lsGrupoDTOs
	 */
	public List<GrupoDTO> getLsGrupoDTOs() {
		return lsGrupoDTOs;
	}

	/**
	 * @param lsGrupoDTOs
	 *            the lsGrupoDTOs to set
	 */
	public void setLsGrupoDTOs(List<GrupoDTO> lsGrupoDTOs) {
		this.lsGrupoDTOs = lsGrupoDTOs;
	}
}
