package co.edu.proca3si.ejb.persistence.entities;
// Generated May 5, 2016 8:50:01 AM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name = "rol")
public class Rol implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "rol_codigo", unique = true, nullable = false)
	private long rolCodigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_rol_codigo")
	private Rol rol;
	
	@Column(name = "rol_nombre", nullable = false, length = 280)
	private String rolNombre;
	
	@Column(name = "rol_estado", nullable = false)
	private boolean rolEstado;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
	private Set<Rol> rols = new HashSet<Rol>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
	private Set<RecursoRol> recursoRols = new HashSet<RecursoRol>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
	private Set<RolAccion> rolAccions = new HashSet<RolAccion>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
	private Set<GrupoRol> grupoRols = new HashSet<GrupoRol>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
	private Set<UsuarioRol> usuarioRols = new HashSet<UsuarioRol>(0);

	public Rol() {
	}

	public Rol(long rolCodigo, String rolNombre, boolean rolEstado) {
		this.rolCodigo = rolCodigo;
		this.rolNombre = rolNombre;
		this.rolEstado = rolEstado;
	}

	public Rol(long rolCodigo, Rol rol, String rolNombre, boolean rolEstado, Set<Rol> rols, Set<RecursoRol> recursoRols, Set<RolAccion> rolAccions, Set<GrupoRol> grupoRols,
			Set<UsuarioRol> usuarioRols) {
		this.rolCodigo = rolCodigo;
		this.rol = rol;
		this.rolNombre = rolNombre;
		this.rolEstado = rolEstado;
		this.rols = rols;
		this.recursoRols = recursoRols;
		this.rolAccions = rolAccions;
		this.grupoRols = grupoRols;
		this.usuarioRols = usuarioRols;
	}

	public long getRolCodigo() {
		return this.rolCodigo;
	}

	public void setRolCodigo(long rolCodigo) {
		this.rolCodigo = rolCodigo;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getRolNombre() {
		return this.rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public boolean isRolEstado() {
		return this.rolEstado;
	}

	public void setRolEstado(boolean rolEstado) {
		this.rolEstado = rolEstado;
	}

	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

	public Set<RecursoRol> getRecursoRols() {
		return this.recursoRols;
	}

	public void setRecursoRols(Set<RecursoRol> recursoRols) {
		this.recursoRols = recursoRols;
	}

	public Set<RolAccion> getRolAccions() {
		return this.rolAccions;
	}

	public void setRolAccions(Set<RolAccion> rolAccions) {
		this.rolAccions = rolAccions;
	}

	public Set<GrupoRol> getGrupoRols() {
		return this.grupoRols;
	}

	public void setGrupoRols(Set<GrupoRol> grupoRols) {
		this.grupoRols = grupoRols;
	}

	public Set<UsuarioRol> getUsuarioRols() {
		return this.usuarioRols;
	}

	public void setUsuarioRols(Set<UsuarioRol> usuarioRols) {
		this.usuarioRols = usuarioRols;
	}

}
