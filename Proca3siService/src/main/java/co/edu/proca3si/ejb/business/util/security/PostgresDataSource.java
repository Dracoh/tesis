/**
 * 
 */
package co.edu.proca3si.ejb.business.util.security;

import org.apache.shiro.realm.jdbc.JdbcRealm;

public class PostgresDataSource extends JdbcRealm {

	public void setSaltStyle(String saltStyle) {
		super.setSaltStyle(SaltStyle.valueOf(saltStyle));
	}

	// public void setAuthenticationQuery(String authenticationQuery) {
	// String query = "SELECT ump.ump_passwordhash, ump.ump_securitysalt FROM
	// usuario usu Inner Join user_membership ump on usu.ump_codigo =
	// ump.ump_codigo where usu.usu_usuario = ?";
	// super.setAuthenticationQuery(query);
	// }
}
