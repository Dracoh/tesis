package co.edu.proca3si.ejb.persistence.entities;
// Generated May 5, 2016 8:50:01 AM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * UserMembership generated by hbm2java
 */
@Entity
@Table(name = "user_membership")
@Audited
public class UserMembership implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ump_codigo", unique = true, nullable = false)
	private long umpCodigo;
	
	@Column(name = "ump_passwordhash")
	private String umpPasswordhash;
	
	@Column(name = "ump_securitysalt", nullable = false)
	private String umpSecuritysalt;
	
	@Column(name = "ump_phone_number", nullable = false, length = 280)
	private String umpPhoneNumber;
	
	@Column(name = "ump_two_factor_enabled")
	private Boolean umpTwoFactorEnabled;
	
	@Column(name = "ump_email")
	private String umpEmail;
	
	@Column(name = "ump_email_confirmed", nullable = false)
	private boolean umpEmailConfirmed;
	
	@Column(name = "ump_lockout_enabled", nullable = false)
	private boolean umpLockoutEnabled;
	
	@Column(name = "ump_last_login_ip", nullable = false, length = 280)
	private String umpLastLoginIp;
	
	@Column(name = "ump_failed_password_attempt_count", nullable = false)
	private long umpFailedPasswordAttemptCount;
	
	@Column(name = "ump_failed_password_attempt_windowstart", nullable = false)
	private long umpFailedPasswordAttemptWindowstart;
	
	@Column(name = "ump_failed_password_answer_attempt_count", nullable = false)
	private long umpFailedPasswordAnswerAttemptCount;
	
	@Column(name = "ump_failed_password_answer_attempt_window_start", nullable = false)
	private long umpFailedPasswordAnswerAttemptWindowStart;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userMembership")
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);

	public UserMembership() {
	}

	public UserMembership(long umpCodigo, String umpSecuritysalt, String umpPhoneNumber, boolean umpEmailConfirmed, boolean umpLockoutEnabled, String umpLastLoginIp,
			long umpFailedPasswordAttemptCount, long umpFailedPasswordAttemptWindowstart, long umpFailedPasswordAnswerAttemptCount,
			long umpFailedPasswordAnswerAttemptWindowStart) {
		this.umpCodigo = umpCodigo;
		this.umpSecuritysalt = umpSecuritysalt;
		this.umpPhoneNumber = umpPhoneNumber;
		this.umpEmailConfirmed = umpEmailConfirmed;
		this.umpLockoutEnabled = umpLockoutEnabled;
		this.umpLastLoginIp = umpLastLoginIp;
		this.umpFailedPasswordAttemptCount = umpFailedPasswordAttemptCount;
		this.umpFailedPasswordAttemptWindowstart = umpFailedPasswordAttemptWindowstart;
		this.umpFailedPasswordAnswerAttemptCount = umpFailedPasswordAnswerAttemptCount;
		this.umpFailedPasswordAnswerAttemptWindowStart = umpFailedPasswordAnswerAttemptWindowStart;
	}

	public UserMembership(long umpCodigo, String umpPasswordhash, String umpSecuritysalt, String umpPhoneNumber, Boolean umpTwoFactorEnabled, String umpEmail,
			boolean umpEmailConfirmed, boolean umpLockoutEnabled, String umpLastLoginIp, long umpFailedPasswordAttemptCount, long umpFailedPasswordAttemptWindowstart,
			long umpFailedPasswordAnswerAttemptCount, long umpFailedPasswordAnswerAttemptWindowStart, Set<Usuario> usuarios) {
		this.umpCodigo = umpCodigo;
		this.umpPasswordhash = umpPasswordhash;
		this.umpSecuritysalt = umpSecuritysalt;
		this.umpPhoneNumber = umpPhoneNumber;
		this.umpTwoFactorEnabled = umpTwoFactorEnabled;
		this.umpEmail = umpEmail;
		this.umpEmailConfirmed = umpEmailConfirmed;
		this.umpLockoutEnabled = umpLockoutEnabled;
		this.umpLastLoginIp = umpLastLoginIp;
		this.umpFailedPasswordAttemptCount = umpFailedPasswordAttemptCount;
		this.umpFailedPasswordAttemptWindowstart = umpFailedPasswordAttemptWindowstart;
		this.umpFailedPasswordAnswerAttemptCount = umpFailedPasswordAnswerAttemptCount;
		this.umpFailedPasswordAnswerAttemptWindowStart = umpFailedPasswordAnswerAttemptWindowStart;
		this.usuarios = usuarios;
	}

	public long getUmpCodigo() {
		return this.umpCodigo;
	}

	public void setUmpCodigo(long umpCodigo) {
		this.umpCodigo = umpCodigo;
	}

	public String getUmpPasswordhash() {
		return this.umpPasswordhash;
	}

	public void setUmpPasswordhash(String umpPasswordhash) {
		this.umpPasswordhash = umpPasswordhash;
	}

	public String getUmpSecuritysalt() {
		return this.umpSecuritysalt;
	}

	public void setUmpSecuritysalt(String umpSecuritysalt) {
		this.umpSecuritysalt = umpSecuritysalt;
	}

	public String getUmpPhoneNumber() {
		return this.umpPhoneNumber;
	}

	public void setUmpPhoneNumber(String umpPhoneNumber) {
		this.umpPhoneNumber = umpPhoneNumber;
	}

	public Boolean getUmpTwoFactorEnabled() {
		return this.umpTwoFactorEnabled;
	}

	public void setUmpTwoFactorEnabled(Boolean umpTwoFactorEnabled) {
		this.umpTwoFactorEnabled = umpTwoFactorEnabled;
	}

	public String getUmpEmail() {
		return this.umpEmail;
	}

	public void setUmpEmail(String umpEmail) {
		this.umpEmail = umpEmail;
	}

	public boolean isUmpEmailConfirmed() {
		return this.umpEmailConfirmed;
	}

	public void setUmpEmailConfirmed(boolean umpEmailConfirmed) {
		this.umpEmailConfirmed = umpEmailConfirmed;
	}

	public boolean isUmpLockoutEnabled() {
		return this.umpLockoutEnabled;
	}

	public void setUmpLockoutEnabled(boolean umpLockoutEnabled) {
		this.umpLockoutEnabled = umpLockoutEnabled;
	}

	public String getUmpLastLoginIp() {
		return this.umpLastLoginIp;
	}

	public void setUmpLastLoginIp(String umpLastLoginIp) {
		this.umpLastLoginIp = umpLastLoginIp;
	}

	public long getUmpFailedPasswordAttemptCount() {
		return this.umpFailedPasswordAttemptCount;
	}

	public void setUmpFailedPasswordAttemptCount(long umpFailedPasswordAttemptCount) {
		this.umpFailedPasswordAttemptCount = umpFailedPasswordAttemptCount;
	}

	public long getUmpFailedPasswordAttemptWindowstart() {
		return this.umpFailedPasswordAttemptWindowstart;
	}

	public void setUmpFailedPasswordAttemptWindowstart(long umpFailedPasswordAttemptWindowstart) {
		this.umpFailedPasswordAttemptWindowstart = umpFailedPasswordAttemptWindowstart;
	}

	public long getUmpFailedPasswordAnswerAttemptCount() {
		return this.umpFailedPasswordAnswerAttemptCount;
	}

	public void setUmpFailedPasswordAnswerAttemptCount(long umpFailedPasswordAnswerAttemptCount) {
		this.umpFailedPasswordAnswerAttemptCount = umpFailedPasswordAnswerAttemptCount;
	}

	public long getUmpFailedPasswordAnswerAttemptWindowStart() {
		return this.umpFailedPasswordAnswerAttemptWindowStart;
	}

	public void setUmpFailedPasswordAnswerAttemptWindowStart(long umpFailedPasswordAnswerAttemptWindowStart) {
		this.umpFailedPasswordAnswerAttemptWindowStart = umpFailedPasswordAnswerAttemptWindowStart;
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
