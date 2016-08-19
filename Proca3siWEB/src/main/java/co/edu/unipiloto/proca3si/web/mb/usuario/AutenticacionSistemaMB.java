/**
 * 
 */
package co.edu.unipiloto.proca3si.web.mb.usuario;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.edu.unipiloto.proca3si.conecionCliente.AdminAdministracionWR;
import co.edu.unipiloto.proca3si.conecionCliente.AdminAuthenticateWR;
import co.edu.unipiloto.proca3si.web.DTO.RecursoDTO;
import co.edu.unipiloto.proca3si.web.DTO.UsuarioDTO;
import co.edu.unipiloto.proca3si.web.util.ManejadorSesion;

@ManagedBean(name = "autenticacionSistemaMB")
public class AutenticacionSistemaMB {
	/**
	 * PARAMETROS
	 */
	private String login;
	@NotNull
	@Size(min = 6, max = 12)
	private String password;

	/**
	 * 
	 */
	public AutenticacionSistemaMB() {
		login = "";
	}

	/**
	 * Envia el proceso de autenticacion y revisa a que servicios tiene acceso
	 * dependiendo del rol
	 * 
	 * @return
	 */
	public String autenticar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			ManejadorSesion manejadorSesion = new ManejadorSesion();
			AdminAuthenticateWR adminAuthenticateWR = new AdminAuthenticateWR();
			UsuarioDTO usuarioDTO = adminAuthenticateWR.autenticarUsuario(login, password);
			// Se traen los recursos asociados a el usuario
			AdminAdministracionWR adminAdministracionWR = new AdminAdministracionWR();
			/* Se consultan los recursos del usuario en secion */
			List<RecursoDTO> lsRecursoVMs = adminAdministracionWR.consultarRecursosXidUsusario(usuarioDTO.getUsuCodigo());
			/* Se agrega el usuario a sesion y sus recursos */
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			manejadorSesion.crearSesion(request);
			manejadorSesion.agregarObjetoSesion(request, "userSession", usuarioDTO);
			manejadorSesion.agregarObjetoSesion(request, "resources", lsRecursoVMs);
			// Se redirecciona a el panel central
			String url = fc.getExternalContext().getRequestContextPath() + "/jsf/dashboard.xhtml";
			fc.getExternalContext().redirect(url);
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ex.getMessage()));
		}
		return null;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
