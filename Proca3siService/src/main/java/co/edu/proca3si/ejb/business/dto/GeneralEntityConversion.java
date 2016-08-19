/**
 * 
 */
package co.edu.proca3si.ejb.business.dto;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.proca3si.ejb.persistence.entities.Accion;
import co.edu.proca3si.ejb.persistence.entities.Aplicacion;
import co.edu.proca3si.ejb.persistence.entities.AplicacionRecurso;
import co.edu.proca3si.ejb.persistence.entities.Grupo;
import co.edu.proca3si.ejb.persistence.entities.GrupoRol;
import co.edu.proca3si.ejb.persistence.entities.Recurso;
import co.edu.proca3si.ejb.persistence.entities.Rol;
import co.edu.proca3si.ejb.persistence.entities.UserMembership;
import co.edu.proca3si.ejb.persistence.entities.Usuario;
import co.edu.proca3si.ejb.persistence.entities.UsuarioGrupo;
import co.edu.proca3si.ejb.persistence.entities.UsuarioRol;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class GeneralEntityConversion {

	/**
	 * Metodo que arma la entidad Accion
	 * 
	 * @author hellequin
	 * @param accionDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public Accion AssembleEntityAccion(AccionDTO accionDTO) {
		Accion accion = new Accion();

		/* Attributes */
		accion.setAcnCodigo(accionDTO.getAcnCodigo());
		accion.setAcnNombre(accionDTO.getAcnDescripcion());
		accion.setAcnNombre(accionDTO.getAcnNombre());
		accion.setAcnEstado(accionDTO.isAcnEstado());
		return accion;
	}

	/**
	 * Metodo que arma la entidad Aplicacion
	 * 
	 * @author hellequin
	 * @param aplicacionDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public Aplicacion AssembleEntityAplicacion(AplicacionDTO aplicacionDTO) {
		Aplicacion aplicacion = new Aplicacion();
		/* Attributes */
		aplicacion.setAppCodigo(aplicacionDTO.getAppCodigo());
		aplicacion.setAppNombre(aplicacionDTO.getAppNombre());
		aplicacion.setAppToken(aplicacionDTO.getAppToken());
		aplicacion.setAppEmail(aplicacionDTO.getAppEmail());
		aplicacion.setAppEmailConfirmed(aplicacionDTO.isAppEmailConfirmed());
		return aplicacion;
	}

	/**
	 * Metodo que arma la entidad AplicacionRecurso
	 * 
	 * @author hellequin
	 * @param aplicacionRecursoDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public AplicacionRecurso AssembleEntityAplicacionRecurso(AplicacionRecursoDTO aplicacionRecursoDTO) {
		AplicacionRecurso aplicacionRecurso = new AplicacionRecurso();

		/* Attributes */
		aplicacionRecurso.setAprCodigo(aplicacionRecursoDTO.getAprCodigo());

		/* Llaves Foraneas */
		// Aplicacion
		if (aplicacionRecursoDTO.getAplicacionDTO() != null && aplicacionRecursoDTO.getAplicacionDTO().getAppCodigo() > 0L) {
			Aplicacion aplicacion = new Aplicacion();
			aplicacion.setAppCodigo(aplicacionRecursoDTO.getAplicacionDTO().getAppCodigo());
			aplicacionRecurso.setAplicacion(aplicacion);
		}
		// Recurso
		if (aplicacionRecursoDTO.getRecursoDTO() != null && aplicacionRecursoDTO.getRecursoDTO().getRecCodigo() > 0L) {
			Recurso recurso = new Recurso();
			recurso.setRecCodigo(aplicacionRecursoDTO.getRecursoDTO().getRecCodigo());
			aplicacionRecurso.setRecurso(recurso);
		}
		return aplicacionRecurso;
	}

	/**
	 * Metodo que arma la entidad Grupo
	 * 
	 * @author hellequin
	 * @param grupoDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public Grupo AssembleEntityGrupo(GrupoDTO grupoDTO) {
		Grupo grupo = new Grupo();

		/* Attributes */
		grupo.setGpoCodigo(grupoDTO.getGpoCodigo());
		grupo.setGpoNombre(grupoDTO.getGpoNombre());
		grupo.setGpoDescripcion(grupoDTO.getGpoDescripcion());
		grupo.setGpoEstado(grupoDTO.isGpoEstado());
		return grupo;
	}

	/**
	 * Metodo que arma la entidad GrupoRol
	 * 
	 * @author hellequin
	 * @param grupoRolDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public GrupoRol AssembleEntityGrupoRol(GrupoRolDTO grupoRolDTO) {
		GrupoRol grupoRol = new GrupoRol();

		/* Attributes */
		grupoRol.setGrlCodigo(grupoRolDTO.getGrupoRolCodigo());

		/* Llaves Foraneas */
		// Grupo
		if (grupoRol.getGrupo() != null && grupoRol.getGrupo().getGpoCodigo() > 0L) {
			Grupo grupo = new Grupo();
			grupo.setGpoCodigo(grupoRol.getGrupo().getGpoCodigo());
			grupoRol.setGrupo(grupo);
		}
		// Rol
		if (grupoRol.getRol() != null && grupoRol.getRol().getRolCodigo() > 0L) {
			Rol rol = new Rol();
			rol.setRolCodigo(grupoRol.getRol().getRolCodigo());
			grupoRol.setRol(rol);
		}
		return grupoRol;
	}

	/**
	 * Metodo que arma la entidad Recurso
	 * 
	 * @author hellequin
	 * @param recursoDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public Recurso AssembleEntityRecurso(RecursoDTO recursoDTO) {
		Recurso recurso = new Recurso();

		/* Attributes */
		recurso.setRecCodigo(recursoDTO.getRecCodigo());
		recurso.setRecNombre(recursoDTO.getRecNombre());
		recurso.setRecDescripccion(recursoDTO.getRecDescripccion());
		recurso.setRecPath(recursoDTO.getRecPath());
		recurso.setRecEstado(recursoDTO.isRecEstado());
		return recurso;
	}

	/**
	 * Metodo que arma la entidad Rol
	 * 
	 * @author hellequin
	 * @param rolDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public Rol AssembleEntityRol(RolDTO rolDTO) {
		Rol rol = new Rol();

		/* Attributes */
		rol.setRolCodigo(rolDTO.getRolCodigo());
		rol.setRolNombre(rolDTO.getRolNombre());
		rol.setRolEstado(rolDTO.isRolAestado());

		/* Llaves Foraneas */
		// Rol
		if (rolDTO.getRolDTO() != null && rolDTO.getRolDTO().getRolCodigo() > 0L) {
			Rol rol2 = new Rol();
			rol2.setRolCodigo(rolDTO.getRolDTO().getRolCodigo());
			rol.setRol(rol2);
		}
		return rol;
	}

	/**
	 * Metodo que arma la entidad UserMembership
	 * 
	 * @author hellequin
	 * @param userMembershipDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public UserMembership AssembleEntityUserMembership(UserMembershipDTO userMembershipDTO) {
		UserMembership userMembership = new UserMembership();
		/* Attributes */
		userMembership.setUmpCodigo(userMembershipDTO.getUmpCodigo());
		userMembership.setUmpPasswordhash(userMembershipDTO.getUmpPasswordhash());
		userMembership.setUmpPhoneNumber(userMembershipDTO.getUmpPhoneNumber());
		userMembership.setUmpTwoFactorEnabled(userMembershipDTO.getUmpTwoFactorEnabled());
		userMembership.setUmpEmail(userMembershipDTO.getUmpEmail());
		userMembership.setUmpLockoutEnabled(userMembershipDTO.isUmpLockoutEnabled());
		userMembership.setUmpLastLoginIp(userMembershipDTO.getUmpLastLoginIp());
		return userMembership;
	}

	/**
	 * Metodo que arma la entidad Usuario
	 * 
	 * @author hellequin
	 * @param usuarioDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public Usuario AssembleEntityUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();

		/* Attributes */
		usuario.setUsuCodigo(usuarioDTO.getUsuCodigo());
		usuario.setUsuUsuario(usuarioDTO.getUsuUsuario());
		usuario.setUsuNombre(usuarioDTO.getUsuNombre());
		usuario.setUsuApellido(usuarioDTO.getUsuApellido());
		usuario.setUsuDireccion(usuarioDTO.getUsuDireccion());
		usuario.setUsuFechaNacimiento(usuarioDTO.getUsuFechaNacimiento());
		usuario.setUsuEstado(usuarioDTO.isUsuEstado());

		/* Llaves Foraneas */
		// UserMembership
		if (usuarioDTO.getUserMembershipDTO() != null && usuarioDTO.getUserMembershipDTO().getUmpCodigo() > 0L) {
			UserMembership userMembership = new UserMembership();
			userMembership.setUmpCodigo(usuarioDTO.getUserMembershipDTO().getUmpCodigo());
			usuario.setUserMembership(userMembership);
		}
		return usuario;
	}

	/**
	 * Metodo que arma la entidad UsuarioGrupo
	 * 
	 * @author hellequin
	 * @param usuarioGrupoDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public UsuarioGrupo AssembleEntityUsuarioGrupo(UsuarioGrupoDTO usuarioGrupoDTO) {
		UsuarioGrupo usuarioGrupo = new UsuarioGrupo();

		/* Attributes */
		usuarioGrupo.setUgoCodigo(usuarioGrupoDTO.getUsuarioGrupoCodigo());

		/* Llaves Foraneas */
		// Grupo
		if (usuarioGrupoDTO.getGrupoDTO() != null && usuarioGrupoDTO.getGrupoDTO().getGpoCodigo() > 0L) {
			Grupo grupo = new Grupo();
			grupo.setGpoCodigo(usuarioGrupoDTO.getGrupoDTO().getGpoCodigo());
			usuarioGrupo.setGrupo(grupo);
		}
		// Usuario
		if (usuarioGrupoDTO.getUsuarioDTO() != null && usuarioGrupoDTO.getUsuarioDTO().getUsuCodigo() > 0L) {
			Usuario usuario = new Usuario();
			usuario.setUsuCodigo(usuarioGrupoDTO.getUsuarioDTO().getUsuCodigo());
			usuarioGrupo.setUsuario(usuario);
		}
		return usuarioGrupo;
	}

	/**
	 * Metodo que arma la entidad usuarioRol
	 * 
	 * @author hellequin
	 * @param usuarioRolDTO
	 * @return
	 * @date Apr 16, 2016
	 */
	public UsuarioRol AssembleEntityusuarioRol(UsuarioRolDTO usuarioRolDTO) {
		UsuarioRol usuarioRol = new UsuarioRol();

		/* Attributes */
		usuarioRol.setUroCodigo(usuarioRolDTO.getusuarioRolCodigo());

		/* Llaves Foraneas */
		// Rol
		if (usuarioRolDTO.getRolDTO() != null && usuarioRolDTO.getRolDTO().getRolCodigo() > 0L) {
			Rol rol = new Rol();
			rol.setRolCodigo(usuarioRolDTO.getRolDTO().getRolCodigo());
			usuarioRol.setRol(rol);
		}
		// Usuario
		if (usuarioRolDTO.getUsuarioDTO() != null && usuarioRolDTO.getUsuarioDTO().getUsuCodigo() > 0L) {
			Usuario usuario = new Usuario();
			usuario.setUsuCodigo(usuarioRolDTO.getUsuarioDTO().getUsuCodigo());
			usuarioRol.setUsuario(usuario);
		}
		return usuarioRol;
	}

}
