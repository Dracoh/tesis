package co.edu.proca3si.ejb.persistence.entities;
// Generated May 5, 2016 8:50:01 AM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

/**
 * UsuarioRol generated by hbm2java
 */
@SuppressWarnings("deprecation")
@Entity
@Table(name = "usuario_rol")
@Audited
public class UsuarioRol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "uro_codigo", unique = true, nullable = false)
	private long uroCodigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_codigo", nullable = false)
	@ForeignKey(name="Fk_usuario_rol_rol_codigo")
	private Rol rol;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_codigo", nullable = false)
	@ForeignKey(name="Fk_usuario_rol_usu_codigo")
	private Usuario usuario;

	public UsuarioRol() {
	}

	public UsuarioRol(long uroCodigo, Rol rol, Usuario usuario) {
		this.uroCodigo = uroCodigo;
		this.rol = rol;
		this.usuario = usuario;
	}

	public long getUroCodigo() {
		return this.uroCodigo;
	}

	public void setUroCodigo(long uroCodigo) {
		this.uroCodigo = uroCodigo;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
