/**
 * 
 */
package co.edu.proca3si.ejb.business.util.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.Factory;

import co.edu.proca3si.ejb.common.exception.ExcepcionDataBaseConnection;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.config.IniSecurityManagerFactory;

/**
 * @author hellequin
 *
 */
public class ConnectionSecurity {

	/**
	 * Metodo que autentica a un usuario en la base de datos
	 * 
	 * @author hellequin
	 * @param usuario
	 * @param contrasenia
	 * @return
	 * @throws ExcepcionDataBaseConnection
	 * @date Apr 23, 2016
	 */
	public static Subject autenticar(String user, String password) throws ExcepcionDataBaseConnection {
		try {

			Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:co/edu/proca3si/ejb/business/util/security/Shiro.ini");
			SecurityManager securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(user, password);
			currentUser.login(token);
			// Si el usuario esta autenticado se devuelve el sujeto
			if (currentUser.isAuthenticated())
				return currentUser;
			else
				// Se retorna nulo si la autenticacion ha fallado
				return null;
		} catch (UnknownAccountException ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
		} catch (IncorrectCredentialsException ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
		} catch (LockedAccountException ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
		} catch (AuthenticationException ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
			// if (ex.getCause().getMessage().contains("account was permanently
			// locked"))
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
		}
	}

	/**
	 * Corrobora si el usuario y la contrasenia coinciden. Si es asi regresa
	 * true, si no regresa false
	 * 
	 * Autor: hellequin
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws ExcepcionDataBaseConnection
	 *             Fecha de Cracion: May 7, 2016
	 */
	public boolean corroborateUserstent(String user, String password) throws ExcepcionDataBaseConnection {
		try {
			Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:co/edu/proca3si/ejb/business/util/security/Shiro.ini");
			SecurityManager securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
			UsernamePasswordToken token = new UsernamePasswordToken(user, password);
			CredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("MD5");
			return credentialsMatcher.doCredentialsMatch(token, securityManager.authenticate(token));
			// Si el usuario esta autenticado se devuelve el sujeto
		} catch (UnknownAccountException ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
		} catch (IncorrectCredentialsException ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
		} catch (LockedAccountException ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
		} catch (AuthenticationException ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
			// if (ex.getCause().getMessage().contains("account was permanently
			// locked"))
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ExcepcionDataBaseConnection("");
		}
	}
}
