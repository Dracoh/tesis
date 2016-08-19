package co.edu.proca3si.ejb.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.naming.NamingException;

import org.apache.shiro.subject.Subject;

import co.edu.proca3si.ejb.business.dto.AplicacionDTO;
import co.edu.proca3si.ejb.business.dto.GeneralEntityConversion;
import co.edu.proca3si.ejb.business.dto.UsuarioDTO;
import co.edu.proca3si.ejb.business.util.NotificacionElectronica;
import co.edu.proca3si.ejb.business.util.security.ConnectionSecurity;
import co.edu.proca3si.ejb.common.exception.ExcepcionAplicacion;
import co.edu.proca3si.ejb.common.exception.ExcepcionDataBaseConnection;
import co.edu.proca3si.ejb.common.exception.ExceptionDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.AplicacionDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.UserMembershipDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.UsuarioDAO;
import co.edu.proca3si.ejb.persistence.entities.Aplicacion;
import co.edu.proca3si.ejb.persistence.entities.Usuario;

@Stateless
public class FacadeApiBean extends FacadeBean {

	@EJB
	private GeneralEntityConversion generalEntityConversion;
	@EJB
	private AplicacionDAO aplicacionDAO;
	@EJB
	private UsuarioDAO usuarioDAO;
	@EJB
	private UserMembershipDAO userMembershipDAO;

	/**
	 * Metodo que autentica el usuario
	 * 
	 * @author hellequin
	 * @param usuario
	 * @param contrasenia
	 * @return
	 * @throws ExceptionDAO
	 * @throws ExcepcionAplicacion
	 * @throws ExcepcionDataBaseConnection
	 * @date Apr 23, 2016
	 */
	@SuppressWarnings("static-access")
	public UsuarioDTO autenticarUsuario(String usuario, String contrasenia) throws ExceptionDAO, ExcepcionAplicacion, ExcepcionDataBaseConnection {
		ConnectionSecurity connectionSecurity = new ConnectionSecurity();
		Subject currentUser = connectionSecurity.autenticar(usuario, contrasenia);
		if (currentUser == null) {
			// Si se falla en la autenticación se procede a actualizar en la
			// base de
			// datos de seguridad que lleva más una falla para el
			// realizar el bloqueo por intentos reiterativos fallidos
			throw new ExcepcionAplicacion("Error al autenticar el usuario!");
		}
		/*
		 * Se consulta el usuario autenticado
		 */
		return new UsuarioDTO(usuarioDAO.consultarUsuarioXUsuario(usuario));
	}

	/**
	 * Metodo que registra una aplicacion
	 * 
	 * @author hellequin
	 * @param aplicacionDTO
	 * @param usuarioDTO
	 * @throws ExceptionDAO
	 * @throws NamingException
	 * @throws MessagingException
	 * @date Apr 17, 2016
	 */
	public void crearAplicacion(AplicacionDTO aplicacionDTO, UsuarioDTO usuarioDTO) throws ExceptionDAO, NamingException, MessagingException {
		// Se registra la aplicacion
		Aplicacion aplicacion = generalEntityConversion.AssembleEntityAplicacion(aplicacionDTO);
		aplicacionDAO.crear(aplicacion);
		// Se registra el usuario
		Usuario usuario = generalEntityConversion.AssembleEntityUsuario(usuarioDTO);
		// Se encripta la contrasenia
		String userPass = usuarioDTO.getUserMembershipDTO().getUmpPasswordhash();
		// usuario.getUserMembership().setUmpPasswordhash(EncryptionSecurity.Encriptar(userPass));
		usuarioDAO.crear(usuario);
		/* Se realiza el envio del correo */
		NotificacionElectronica notificacionElectronica = new NotificacionElectronica();
		String asunto = "Creacion de su nueva aplicacion en Proca3si";
		String mensaje = "Se ha creado su nueva aplicacion " + aplicacion.getAppNombre();
		mensaje = mensaje + " /n Diríjase a este link para poder confirmar su correo electrónico y seguir con el proceso de configuración de su aplicación.";
		mensaje = mensaje + "Url servicio";
		notificacionElectronica.enviarCorreo(aplicacion.getAppEmail(), asunto, mensaje);
		// Se agrega el id a la nueva aplicacion
		aplicacionDTO.setAppCodigo(aplicacion.getAppCodigo());
	}

	/**
	 * Metodo que confirma la creacion de una aplicacion
	 * 
	 * @author hellequin
	 * @param idApp
	 * @param pass
	 * @throws ExceptionDAO
	 * @date Apr 18, 2016
	 */
	public void confirmarAplicacion(long idApp, String pass) throws ExceptionDAO {
		// Se busca el aplicativo que se va a confirmar
		Aplicacion aplicacion = aplicacionDAO.consultarXId(idApp);
		// aplicacion.
	}

}
