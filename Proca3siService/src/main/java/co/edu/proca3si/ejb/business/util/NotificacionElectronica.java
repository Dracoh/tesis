package co.edu.proca3si.ejb.business.util;

import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.edu.proca3si.ejb.util.enumerations.KindProperties;
import co.edu.proca3si.ejb.util.resources.Recursos;

/**
 * 
 * @author hellequin
 *
 */
public class NotificacionElectronica {
	// Recursos
	protected Recursos recursos = new Recursos();
	// Propiedades del sistema
	protected Properties propertiesSystem = recursos.cargarPropiedades(KindProperties.mensajes_sistema.name());
	// Log general de la aplicacion
	protected static Logger logger;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public NotificacionElectronica() {

	}

	/**
	 * Envia un correo al destinatario definido, con las caracteristicas
	 * establecidas
	 * 
	 * @author hellequin
	 * @param destinatario
	 * @param asunto
	 * @param mensaje
	 * @throws NamingException
	 * @throws MessagingException
	 * @date Apr 17, 2016
	 */
	public void enviarCorreo(String destinatario, String asunto, String mensaje) throws NamingException, MessagingException {
		try {
			Properties props = new Properties();
			InitialContext ictx = new InitialContext(props);
			Session mailSession = (Session) ictx.lookup("java:jboss/mail/proca3si");

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(asunto);
			message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(destinatario, false));
			message.setText(mensaje);
			message.saveChanges();

			Transport.send(message);
		} catch (NamingException e) {
			logger.error("ERROR PROCA3SI - NOTIFICACIONES DE CORREO - METODO enviarCorreo: " + e.getMessage());
			throw new NamingException(propertiesSystem.getProperty("ERROR_SERVIDOR_CORREO_ELECTRONICO"));
		} catch (MessagingException e2) {
			logger.error("ERROR PROCA3SI - NOTIFICACIONES DE CORREO - METODO enviarCorreo: " + e2.getMessage());
			throw new MessagingException(propertiesSystem.getProperty("ERROR_CORREO_ELECTRONICO"));
		}
	}

	/**
	 * Envia un correo a una lista de destinatarios definidos, con las
	 * caracteristicas
	 * 
	 * @author hellequin
	 * @param destinatarios
	 * @param destinatariosCC
	 * @param destinatariosCCO
	 * @param asunto
	 * @param mensaje
	 * @throws NamingException
	 * @throws MessagingException
	 * @date Apr 17, 2016
	 */
	public void enviarCorreo(List<String> destinatarios, List<String> destinatariosCC, List<String> destinatariosCCO, String asunto, String mensaje)
			throws NamingException, MessagingException {
		try {
			Properties props = new Properties();
			InitialContext ictx = new InitialContext(props);
			Session mailSession = (Session) ictx.lookup("java:jboss/mail/proca3si");

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(asunto);
			for (String destinatario : destinatarios) {
				message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(destinatario, false));
			}
			if (destinatariosCC != null) {
				for (String destinatario : destinatariosCC) {
					message.setRecipients(javax.mail.Message.RecipientType.CC, javax.mail.internet.InternetAddress.parse(destinatario, false));
				}
			}
			message.setText(mensaje, "UTF-8");
			message.saveChanges();

			Transport.send(message);
		} catch (NamingException e) {
			logger.error("ERROR PROCA3SI - NOTIFICACIONES DE CORREO - METODO enviarCorreo: " + e.getMessage());
			throw new NamingException(propertiesSystem.getProperty("ERROR_SERVIDOR_CORREO_ELECTRONICO"));
		} catch (MessagingException e2) {
			logger.error("ERROR PROCA3SI - NOTIFICACIONES DE CORREO - METODO enviarCorreo: " + e2.getMessage());
			throw new MessagingException(propertiesSystem.getProperty("ERROR_CORREO_ELECTRONICO"));
		}
	}

	/**
	 * Se encarga de eniar un correo en formato HTML en el asunto
	 * 
	 * @author hellequin
	 * @param destinatario
	 * @param asunto
	 * @param mensaje
	 * @throws NamingException
	 * @throws MessagingException
	 * @date Apr 17, 2016
	 */
	public void enviarCorreoHTML(String destinatario, String asunto, String mensaje) throws NamingException, MessagingException {
		try {
			Properties props = new Properties();
			InitialContext ictx = new InitialContext(props);
			Session mailSession = (Session) ictx.lookup("java:jboss/mail/proca3si");

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(asunto);
			message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(destinatario, false));
			// Agregar como HTML el cuerpo del mensaje
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mensaje, "text/html; charset=UTF-8");

			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			// ----------------------------------------
			message.saveChanges();
			Transport.send(message);
		} catch (NamingException e) {
			logger.error("ERROR PROCA3SI - NOTIFICACIONES DE CORREO - METODO enviarCorreo: " + e.getMessage());
			throw new NamingException(propertiesSystem.getProperty("ERROR_SERVIDOR_CORREO_ELECTRONICO"));
		} catch (MessagingException e2) {
			logger.error("ERROR PROCA3SI - NOTIFICACIONES DE CORREO - METODO enviarCorreo: " + e2.getMessage());
			throw new MessagingException(propertiesSystem.getProperty("ERROR_CORREO_ELECTRONICO"));
		}
	}

	/**
	 * Metodo para enviar correos enriquecidos (HTML) sin incluir el origen
	 * 
	 * @param destinatarios
	 * @param destinatariosCC
	 * @param destinatariosCCO
	 * @param asunto
	 * @param mensaje
	 * @throws NamingException
	 * @throws MessagingException
	 */
	public void enviarCorreoHTML(List<String> destinatarios, List<String> destinatariosCC, List<String> destinatariosCCO, String asunto, String mensaje)
			throws NamingException, MessagingException {
		enviarCorreoHTML(null, destinatarios, destinatariosCC, destinatariosCCO, asunto, mensaje);
	}

	/**
	 * Metodo para enviar correos enriquecidos (HTML)
	 * 
	 * @param desde
	 * @param destinatarios
	 * @param destinatariosCC
	 * @param destinatariosCCO
	 * @param asunto
	 * @param mensaje
	 * @param adjuntos
	 * @throws NamingException
	 * @throws MessagingException
	 */
	public void enviarCorreoHTML(String desde, List<String> destinatarios, List<String> destinatariosCC, List<String> destinatariosCCO, String asunto, String mensaje)
			throws NamingException, MessagingException {
		try {
			Properties props = new Properties();
			InitialContext ictx = new InitialContext(props);
			Session mailSession = (Session) ictx.lookup("java:jboss/mail/proca3si");

			MimeMessage message = new MimeMessage(mailSession);
			if (desde != null)
				message.setFrom(new InternetAddress(desde));
			message.setSubject(asunto);
			for (String destinatario : destinatarios) {
				message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(destinatario, false));
			}
			if (destinatariosCC != null) {
				for (String destinatario : destinatariosCC) {
					message.addRecipients(javax.mail.Message.RecipientType.CC, javax.mail.internet.InternetAddress.parse(destinatario, false));
				}
			}
			// Agregar como HTML el cuerpo del mensaje
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mensaje, "text/html");

			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			message.saveChanges();

			Transport.send(message);
		} catch (NamingException e) {
			logger.error("ERROR PROCA3SI - NOTIFICACIONES DE CORREO - METODO enviarCorreo: " + e.getMessage());
			throw new NamingException(propertiesSystem.getProperty("ERROR_SERVIDOR_CORREO_ELECTRONICO"));
		} catch (MessagingException e2) {
			logger.error("ERROR PROCA3SI - NOTIFICACIONES DE CORREO - METODO enviarCorreo: " + e2.getMessage());
			throw new MessagingException(propertiesSystem.getProperty("ERROR_CORREO_ELECTRONICO"));
		}
	}

}
