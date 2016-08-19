/**
 * 
 */
package co.edu.proca3si.ejb.business.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import co.edu.proca3si.ejb.persistence.entities.Recurso;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class RecursoDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long recCodigo;
	private String recNombre;
	private String recDescripccion;
	private String recPath;
	private boolean recEstado;
	@XmlElement
	private List<RolDTO> lstRolDTO;
	@XmlElement
	private List<AplicacionDTO> lsAplicacionDTOs;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public RecursoDTO() {
		lstRolDTO = new ArrayList<RolDTO>();
		lsAplicacionDTOs = new ArrayList<AplicacionDTO>();
	}

	/**
	 * 
	 * CONSTRUCTOR DOBRECARGADO
	 * 
	 * @param recurso
	 */
	public RecursoDTO(Recurso recurso) {
		this.recCodigo = recurso.getRecCodigo();
		this.recNombre = recurso.getRecNombre();
		this.recDescripccion = recurso.getRecDescripccion();
		this.recPath = recurso.getRecPath();
		this.recEstado = recurso.isRecEstado();

		/* Listas */
		lstRolDTO = new ArrayList<RolDTO>();
		lsAplicacionDTOs = new ArrayList<AplicacionDTO>();
	}

	/**
	 * @return the recCodigo
	 */
	public long getRecCodigo() {
		return recCodigo;
	}

	/**
	 * @param recCodigo
	 *            the recCodigo to set
	 */
	public void setRecCodigo(long recCodigo) {
		this.recCodigo = recCodigo;
	}

	/**
	 * @return the recNombre
	 */
	public String getRecNombre() {
		return recNombre;
	}

	/**
	 * @param recNombre
	 *            the recNombre to set
	 */
	public void setRecNombre(String recNombre) {
		this.recNombre = recNombre;
	}

	/**
	 * @return the recDescripccion
	 */
	public String getRecDescripccion() {
		return recDescripccion;
	}

	/**
	 * @param recDescripccion
	 *            the recDescripccion to set
	 */
	public void setRecDescripccion(String recDescripccion) {
		this.recDescripccion = recDescripccion;
	}

	/**
	 * @return the recPath
	 */
	public String getRecPath() {
		return recPath;
	}

	/**
	 * @param recPath
	 *            the recPath to set
	 */
	public void setRecPath(String recPath) {
		this.recPath = recPath;
	}

	/**
	 * @return the recEstado
	 */
	public boolean isRecEstado() {
		return recEstado;
	}

	/**
	 * @param recEstado
	 *            the recEstado to set
	 */
	public void setRecEstado(boolean recEstado) {
		this.recEstado = recEstado;
	}

	/**
	 * @return the lstRolDTO
	 */
	public List<RolDTO> getLstRolDTO() {
		return lstRolDTO;
	}

	/**
	 * @param lstRolDTO
	 *            the lstRolDTO to set
	 */
	public void setLstRolDTO(List<RolDTO> lstRolDTO) {
		this.lstRolDTO = lstRolDTO;
	}

	/**
	 * @return the lsAplicacionDTOs
	 */
	public List<AplicacionDTO> getLsAplicacionDTOs() {
		return lsAplicacionDTOs;
	}

	/**
	 * @param lsAplicacionDTOs
	 *            the lsAplicacionDTOs to set
	 */
	public void setLsAplicacionDTOs(List<AplicacionDTO> lsAplicacionDTOs) {
		this.lsAplicacionDTOs = lsAplicacionDTOs;
	}
}
