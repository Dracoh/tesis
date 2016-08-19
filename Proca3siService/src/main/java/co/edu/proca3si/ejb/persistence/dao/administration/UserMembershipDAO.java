/**
 * 
 */
package co.edu.proca3si.ejb.persistence.dao.administration;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.proca3si.ejb.persistence.dao.DAO;
import co.edu.proca3si.ejb.persistence.entities.UserMembership;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class UserMembershipDAO extends DAO<UserMembership> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public UserMembershipDAO() {
		entity = new UserMembership();
	}
}
