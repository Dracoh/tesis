/**
 * 
 */
package co.edu.proca3si.ejb.persistence.dao.administration;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.proca3si.ejb.persistence.dao.DAO;
import co.edu.proca3si.ejb.persistence.entities.Aplicacion;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class AplicacionDAO extends DAO<Aplicacion> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public AplicacionDAO() {
		entity = new Aplicacion();
	}

}
