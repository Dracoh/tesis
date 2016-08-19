/**
 * 
 */
package co.edu.proca3si.ejb.persistence.dao.administration;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.proca3si.ejb.persistence.dao.DAO;
import co.edu.proca3si.ejb.persistence.entities.AplicacionRecurso;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class AplicacionRecursoDAO extends DAO<AplicacionRecurso> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public AplicacionRecursoDAO() {
		entity = new AplicacionRecurso();
	}
}
