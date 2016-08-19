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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Grupo generated by hbm2java
 */
@Entity
@Table(name = "grupo")
public class Grupo implements java.io.Serializable {

	private long gpoCodigo;
	private String gpoNombre;
	private String gpoDescripcion;
	private boolean gpoEstado;
	private Set<UsuarioGrupo> usuarioGrupos = new HashSet<UsuarioGrupo>(0);
	private Set<GrupoRol> grupoRols = new HashSet<GrupoRol>(0);

	public Grupo() {
	}

	public Grupo(long gpoCodigo, String gpoNombre, boolean gpoEstado) {
		this.gpoCodigo = gpoCodigo;
		this.gpoNombre = gpoNombre;
		this.gpoEstado = gpoEstado;
	}

	public Grupo(long gpoCodigo, String gpoNombre, String gpoDescripcion, boolean gpoEstado, Set<UsuarioGrupo> usuarioGrupos, Set<GrupoRol> grupoRols) {
		this.gpoCodigo = gpoCodigo;
		this.gpoNombre = gpoNombre;
		this.gpoDescripcion = gpoDescripcion;
		this.gpoEstado = gpoEstado;
		this.usuarioGrupos = usuarioGrupos;
		this.grupoRols = grupoRols;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "gpo_codigo", unique = true, nullable = false)
	public long getGpoCodigo() {
		return this.gpoCodigo;
	}

	public void setGpoCodigo(long gpoCodigo) {
		this.gpoCodigo = gpoCodigo;
	}

	@Column(name = "gpo_nombre", nullable = false, length = 280)
	public String getGpoNombre() {
		return this.gpoNombre;
	}

	public void setGpoNombre(String gpoNombre) {
		this.gpoNombre = gpoNombre;
	}

	@Column(name = "gpo_descripcion", length = 280)
	public String getGpoDescripcion() {
		return this.gpoDescripcion;
	}

	public void setGpoDescripcion(String gpoDescripcion) {
		this.gpoDescripcion = gpoDescripcion;
	}

	@Column(name = "gpo_estado", nullable = false)
	public boolean isGpoEstado() {
		return this.gpoEstado;
	}

	public void setGpoEstado(boolean gpoEstado) {
		this.gpoEstado = gpoEstado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	public Set<UsuarioGrupo> getUsuarioGrupos() {
		return this.usuarioGrupos;
	}

	public void setUsuarioGrupos(Set<UsuarioGrupo> usuarioGrupos) {
		this.usuarioGrupos = usuarioGrupos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	public Set<GrupoRol> getGrupoRols() {
		return this.grupoRols;
	}

	public void setGrupoRols(Set<GrupoRol> grupoRols) {
		this.grupoRols = grupoRols;
	}

}