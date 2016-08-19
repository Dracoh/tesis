/**
 * 
 */
package co.edu.proca3si.ejb.persistence.dao.administration;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.proca3si.ejb.persistence.dao.DAO;
import co.edu.proca3si.ejb.persistence.entities.UsuarioRol;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class UsuRolDAO extends DAO<UsuarioRol> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public UsuRolDAO() {
		entity = new UsuarioRol();
	}

}
