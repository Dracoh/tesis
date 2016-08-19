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
public class GrupoDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long gpoCodigo;
	private String gpoNombre;
	private String gpoDescripcion;
	private boolean gpoEstado;
	@XmlElement
	private List<UsuarioDTO> lsUsuarioDTOs;
	@XmlElement
	private List<RolDTO> lsRolDTOs;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public GrupoDTO() {
		lsUsuarioDTOs = new ArrayList<UsuarioDTO>();
		lsRolDTOs = new ArrayList<RolDTO>();
	}

	/**
	 * @return the gpoCodigo
	 */
	public long getGpoCodigo() {
		return gpoCodigo;
	}

	/**
	 * @param gpoCodigo
	 *            the gpoCodigo to set
	 */
	public void setGpoCodigo(long gpoCodigo) {
		this.gpoCodigo = gpoCodigo;
	}

	/**
	 * @return the gpoNombre
	 */
	public String getGpoNombre() {
		return gpoNombre;
	}

	/**
	 * @param gpoNombre
	 *            the gpoNombre to set
	 */
	public void setGpoNombre(String gpoNombre) {
		this.gpoNombre = gpoNombre;
	}

	/**
	 * @return the gpoDescripcion
	 */
	public String getGpoDescripcion() {
		return gpoDescripcion;
	}

	/**
	 * @param gpoDescripcion
	 *            the gpoDescripcion to set
	 */
	public void setGpoDescripcion(String gpoDescripcion) {
		this.gpoDescripcion = gpoDescripcion;
	}

	/**
	 * @return the gpoEstado
	 */
	public boolean isGpoEstado() {
		return gpoEstado;
	}

	/**
	 * @param gpoEstado
	 *            the gpoEstado to set
	 */
	public void setGpoEstado(boolean gpoEstado) {
		this.gpoEstado = gpoEstado;
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
}
