/**
 * 
 */
package co.edu.proca3si.ejb.business.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author hellequin
 *
 */
public class EncryptionSecurity {

	/**
	 * Metodo que encripta la contrasenia del usuario
	 * 
	 * @author hellequin
	 * @param userPass
	 * @return
	 * @date Apr 24, 2016
	 */
	public String[] encryptUserPassword(String userPass) {
		String[] secret = new String[2];
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();
		/*
		 * Now hash the plain-text password with the random salt and multiple
		 * iterations and then Base64-encode the value (requires less space than
		 * Hex):
		 */
		secret[0] = new Md5Hash(userPass, salt).toString();
		// save the salt with the new account. The HashedCredentialsMatcher
		// will need it later when handling login attempts:
		secret[1] = salt.toString();
		return secret;
	}

	/**
	 * Metodo que encripta una constrasenia en md5
	 * 
	 * Autor: hellequin
	 * 
	 * @param userPass
	 * @return Fecha de Cracion: May 7, 2016
	 */
	public String encryptUserPasswordNotSalt(String userPass) {
		// return userPass;
		return new Md5Hash(userPass).toString();
	}
}
