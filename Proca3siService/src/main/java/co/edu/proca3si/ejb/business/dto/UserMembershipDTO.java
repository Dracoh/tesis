/**
 * 
 */
package co.edu.proca3si.ejb.business.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import co.edu.proca3si.ejb.persistence.entities.UserMembership;

/**
 * @author hellequin
 *
 */
@XmlRootElement
public class UserMembershipDTO implements Serializable {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private long umpCodigo;
	private String umpPasswordhash;
	private String umpPhoneNumber;
	private Boolean umpTwoFactorEnabled;
	private String umpEmail;
	private boolean umpLockoutEnabled;
	private String umpLastLoginIp;

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public UserMembershipDTO() {
	}

	/**
	 * 
	 * CONSTRUCTOR
	 * 
	 * @param userMembership
	 */
	public UserMembershipDTO(UserMembership userMembership) {
		this.umpCodigo = userMembership.getUmpCodigo();
		this.umpPasswordhash = userMembership.getUmpPasswordhash();
		this.umpPhoneNumber = userMembership.getUmpPhoneNumber();
		this.umpTwoFactorEnabled = userMembership.getUmpTwoFactorEnabled();
		this.umpEmail = userMembership.getUmpEmail();
		this.umpLockoutEnabled = userMembership.isUmpLockoutEnabled();
		this.umpLastLoginIp = userMembership.getUmpLastLoginIp();
	}

	/**
	 * @return the umpCodigo
	 */
	public long getUmpCodigo() {
		return umpCodigo;
	}

	/**
	 * @param umpCodigo
	 *            the umpCodigo to set
	 */
	public void setUmpCodigo(long umpCodigo) {
		this.umpCodigo = umpCodigo;
	}

	/**
	 * @return the umpPasswordhash
	 */
	public String getUmpPasswordhash() {
		return umpPasswordhash;
	}

	/**
	 * @param umpPasswordhash
	 *            the umpPasswordhash to set
	 */
	public void setUmpPasswordhash(String umpPasswordhash) {
		this.umpPasswordhash = umpPasswordhash;
	}

	/**
	 * @return the umpPhoneNumber
	 */
	public String getUmpPhoneNumber() {
		return umpPhoneNumber;
	}

	/**
	 * @param umpPhoneNumber
	 *            the umpPhoneNumber to set
	 */
	public void setUmpPhoneNumber(String umpPhoneNumber) {
		this.umpPhoneNumber = umpPhoneNumber;
	}

	/**
	 * @return the umpTwoFactorEnabled
	 */
	public Boolean getUmpTwoFactorEnabled() {
		return umpTwoFactorEnabled;
	}

	/**
	 * @param umpTwoFactorEnabled
	 *            the umpTwoFactorEnabled to set
	 */
	public void setUmpTwoFactorEnabled(Boolean umpTwoFactorEnabled) {
		this.umpTwoFactorEnabled = umpTwoFactorEnabled;
	}

	/**
	 * @return the umpEmail
	 */
	public String getUmpEmail() {
		return umpEmail;
	}

	/**
	 * @param umpEmail
	 *            the umpEmail to set
	 */
	public void setUmpEmail(String umpEmail) {
		this.umpEmail = umpEmail;
	}

	/**
	 * @return the umpLockoutEnabled
	 */
	public boolean isUmpLockoutEnabled() {
		return umpLockoutEnabled;
	}

	/**
	 * @param umpLockoutEnabled
	 *            the umpLockoutEnabled to set
	 */
	public void setUmpLockoutEnabled(boolean umpLockoutEnabled) {
		this.umpLockoutEnabled = umpLockoutEnabled;
	}

	/**
	 * @return the umpLastLoginIp
	 */
	public String getUmpLastLoginIp() {
		return umpLastLoginIp;
	}

	/**
	 * @param umpLastLoginIp
	 *            the umpLastLoginIp to set
	 */
	public void setUmpLastLoginIp(String umpLastLoginIp) {
		this.umpLastLoginIp = umpLastLoginIp;
	}
}
