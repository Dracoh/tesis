package co.edu.unipiloto.proca3si.web.sistema;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.unipiloto.proca3si.conecionCliente.AdminAdministracionWR;
import co.edu.unipiloto.proca3si.web.DTO.AccionDTO;
import co.edu.unipiloto.proca3si.web.DTO.RecursoDTO;
import co.edu.unipiloto.proca3si.web.DTO.UsuarioDTO;

@ManagedBean(name = "menuMB")
@SessionScoped
public class MenuMB {
	//
	AdminAdministracionWR adminAdministracionWR;
	private FacesContext fc;
	// Lista de recurso que estan asociados a el usuario
	private List<RecursoDTO> lsRecursoDTOs;
	// Acciones que puede ejecutar el usuario en determinada funcionalidad
	private List<AccionDTO> lsAccionDTOs;
	//
	private UsuarioDTO usuarioDTO;

	/**
	 * CONDTRUCTOR
	 */
	@SuppressWarnings("unchecked")
	public MenuMB() {
		fc = FacesContext.getCurrentInstance();
		adminAdministracionWR = new AdminAdministracionWR();
		// Usuario en secion
		HttpSession sesion = ((HttpServletRequest) fc.getExternalContext().getRequest()).getSession(false);
		usuarioDTO = (UsuarioDTO) sesion.getAttribute("userSession");
		lsRecursoDTOs = (List<RecursoDTO>) sesion.getAttribute("resources");
	}

	/**
	 * Metodo que redirecciona a el recurso solicitado en el muni
	 * 
	 * @author racso
	 * @param recursoVM
	 * @return
	 */
	public String redreccionarRecurso(RecursoDTO recursoDTO) {
		fc = FacesContext.getCurrentInstance();
		try {
			// bajarMbDeSecion();

			// Se consultan las acciones del recurso al que tiene acceso el
			// usuario
			lsAccionDTOs = adminAdministracionWR.consultarAccionesRecursoUsuario(usuarioDTO.getUsuCodigo(), recursoDTO.getRecCodigo());

			String url = fc.getExternalContext().getRequestContextPath() + recursoDTO.getRecPath();
			fc.getExternalContext().redirect(url);
		} catch (IOException e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la consulta de los perfiles de usuario", e.getMessage()));
		}
		return null;
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
