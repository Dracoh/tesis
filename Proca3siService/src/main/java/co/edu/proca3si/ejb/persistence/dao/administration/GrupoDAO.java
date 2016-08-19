/**
 * 
 */
package co.edu.proca3si.ejb.persistence.dao.administration;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.proca3si.ejb.persistence.dao.DAO;
import co.edu.proca3si.ejb.persistence.entities.Grupo;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class GrupoDAO extends DAO<Grupo> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public GrupoDAO() {
		entity = new Grupo();
	}
}
