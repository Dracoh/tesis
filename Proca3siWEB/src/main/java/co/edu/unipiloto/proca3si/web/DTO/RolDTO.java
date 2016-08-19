/**
 * 
 */
package co.edu.unipiloto.proca3si.web.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class RolDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long rolCodigo;
	private String rolNombre;
	private boolean rolAestado;
	@XmlElement
	private RolDTO rolDTO;
	@XmlElement
	private List<GrupoDTO> lsGrupoDTOs;
	@XmlElement
	private List<UsuarioDTO> lsUsuarioDTOs;
	@XmlElement
	private List<RecursoDTO> lsRecursoDTOs;
	@XmlElement
	private List<AccionDTO> lsAccionDTOs;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public RolDTO() {
		lsGrupoDTOs = new ArrayList<GrupoDTO>();
		lsUsuarioDTOs = new ArrayList<UsuarioDTO>();
		lsRecursoDTOs = new ArrayList<RecursoDTO>();
		lsAccionDTOs = new ArrayList<AccionDTO>();
	}

	/**
	 * @return the rolCodigo
	 */
	public long getRolCodigo() {
		return rolCodigo;
	}

	/**
	 * @param rolCodigo
	 *            the rolCodigo to set
	 */
	public void setRolCodigo(long rolCodigo) {
		this.rolCodigo = rolCodigo;
	}

	/**
	 * @return the rolNombre
	 */
	public String getRolNombre() {
		return rolNombre;
	}

	/**
	 * @param rolNombre
	 *            the rolNombre to set
	 */
	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	/**
	 * @return the rolAestado
	 */
	public boolean isRolAestado() {
		return rolAestado;
	}

	/**
	 * @param rolAestado
	 *            the rolAestado to set
	 */
	public void setRolAestado(boolean rolAestado) {
		this.rolAestado = rolAestado;
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

	/**
	 * @return the lsUsuarioDTOs
	 */
	public List<UsuarioDTO> getLsUsuarioDTOs() {
		return lsUsuarioDTOs;
	}

	/**
	 * @param lsUsuarioDTOs
	 *            the lsUsuarioDTOs to set
	 */
	public void setLsUsuarioDTOs(List<UsuarioDTO> lsUsuarioDTOs) {
		this.lsUsuarioDTOs = lsUsuarioDTOs;
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

	/**
	 * @return the lsAccionDTOs
	 */
	public List<AccionDTO> getLsAccionDTOs() {
		return lsAccionDTOs;
	}

	/**
	 * @param lsAccionDTOs
	 *            the lsAccionDTOs to set
	 */
	public void setLsAccionDTOs(List<AccionDTO> lsAccionDTOs) {
		this.lsAccionDTOs = lsAccionDTOs;
	}
}
