/**
 * 
 */
package co.edu.proca3si.ejb.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.edu.proca3si.ejb.business.dto.AccionDTO;
import co.edu.proca3si.ejb.business.dto.GeneralEntityConversion;
import co.edu.proca3si.ejb.business.dto.GrupoDTO;
import co.edu.proca3si.ejb.business.dto.RecursoDTO;
import co.edu.proca3si.ejb.business.dto.RolDTO;
import co.edu.proca3si.ejb.business.dto.UserMembershipDTO;
import co.edu.proca3si.ejb.business.dto.UsuarioDTO;
import co.edu.proca3si.ejb.business.util.EncryptionSecurity;
import co.edu.proca3si.ejb.business.util.security.ConnectionSecurity;
import co.edu.proca3si.ejb.common.exception.ExcepcionAplicacion;
import co.edu.proca3si.ejb.common.exception.ExceptionDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.AccionDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.GrupoDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.GrupoRolDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.RecursoDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.RolDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.UserMembershipDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.UsuarioDAO;
import co.edu.proca3si.ejb.persistence.dao.administration.UsuarioGrupoDAO;
import co.edu.proca3si.ejb.persistence.entities.Accion;
import co.edu.proca3si.ejb.persistence.entities.Grupo;
import co.edu.proca3si.ejb.persistence.entities.GrupoRol;
import co.edu.proca3si.ejb.persistence.entities.Recurso;
import co.edu.proca3si.ejb.persistence.entities.Rol;
import co.edu.proca3si.ejb.persistence.entities.UserMembership;
import co.edu.proca3si.ejb.persistence.entities.Usuario;
import co.edu.proca3si.ejb.persistence.entities.UsuarioGrupo;

/**
 * @author hellequin
 *
 */
@Stateless
public class FacadeAdministracionBe extends FacadeBean {

	@EJB
	private GeneralEntityConversion generalEntityConversion;
	@EJB
	private UsuarioDAO usuarioDAO;
	@EJB
	private UserMembershipDAO userMembershipDAO;
	@EJB
	private AccionDAO accionDAO;
	@EJB
	private GrupoDAO grupoDAO;
	@EJB
	private RolDAO rolDAO;
	@EJB
	private RecursoDAO recursoDAO;
	@EJB
	private GrupoRolDAO grupoRolDAO;
	@EJB
	private UsuarioGrupoDAO usuarioGrupoDAO;

	/**
	 * CONSTRUCTOR
	 */
	public FacadeAdministracionBe() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que crea un usuario
	 * 
	 * @author hellequin
	 * @param usuarioDTO
	 * @throws ExceptionDAO
	 * @throws ExcepcionAplicacion
	 * @date Apr 18, 2016
	 */
	public void crearUsuario(UsuarioDTO usuarioDTO) throws ExceptionDAO, ExcepcionAplicacion {
		try {
			EncryptionSecurity encryptionSecurity = new EncryptionSecurity();
			/*
			 * Se realiza la conversion de los datos de seguridad del usuario
			 */
			UserMembership userMembership = generalEntityConversion.AssembleEntityUserMembership(usuarioDTO.getUserMembershipDTO());
			// Se encripta la contrasenia
			String userPass = usuarioDTO.getUserMembershipDTO().getUmpPasswordhash();
			// Se encripta la clave del usuario
			// String[] pass = encryptionSecurity.encryptUserPassword(userPass);
			// userMembership.setUmpPasswordhash(pass[0]);
			// userMembership.setUmpSecuritysalt(pass[1]);
			String pass = encryptionSecurity.encryptUserPasswordNotSalt(userPass);
			userMembership.setUmpPasswordhash(pass);
			userMembership.setUmpSecuritysalt("123");
			// Se registran los datos de seguridad del usuario
			userMembershipDAO.crear(userMembership);
			// Se realiza la convercion a su entidad correspondiente
			Usuario usuario = generalEntityConversion.AssembleEntityUsuario(usuarioDTO);
			// Se asigna la relacion del usuario con sus datos de seguridad
			usuario.setUserMembership(userMembership);
			// Se registra el usuario
			usuarioDAO.crear(usuario);
		} catch (Exception e) {
			throw new ExcepcionAplicacion(e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza el usuario
	 * 
	 * @author hellequin
	 * @param usuarioDTO
	 * @throws ExceptionDAO
	 * @throws ExcepcionAplicacion
	 * @date Apr 23, 2016
	 */
	public void actualizarUsuario(UsuarioDTO usuarioDTO) throws ExceptionDAO, ExcepcionAplicacion {
		try {
			if (usuarioDTO.getUsuCodigo() > 0L) {
				// Se consulta la bala de seguridad con los datos asociados del
				// usuario
				UserMembership userMembership = null;
				if (usuarioDTO.getUserMembershipDTO() != null && usuarioDTO.getUserMembershipDTO().getUmpCodigo() > 0L)
					userMembership = userMembershipDAO.consultarXId(usuarioDTO.getUserMembershipDTO().getUmpCodigo());
				else
					throw new Exception("La informacion del usuario no fue procesada correctamente!");
				/* Se actualian los usuarios de seguridad */
				UserMembership userMembershipUpdate = generalEntityConversion.AssembleEntityUserMembership(usuarioDTO.getUserMembershipDTO());
				userMembership.setUmpPhoneNumber(userMembershipUpdate.getUmpPhoneNumber());
				userMembership.setUmpEmailConfirmed(userMembershipUpdate.isUmpEmailConfirmed());
				// Se registran los datos de seguridad del usuario
				userMembershipDAO.actualizar(userMembership);
				// Se realiza la convercion a su entidad correspondiente
				Usuario usuario = generalEntityConversion.AssembleEntityUsuario(usuarioDTO);
				// Se asigna la relacion del usuario con sus datos de seguridad
				usuario.setUserMembership(userMembership);
				// Se registra el usuario
				usuarioDAO.actualizar(usuario);
			}
		} catch (Exception e) {
			throw new ExcepcionAplicacion(e.getMessage());
		}
	}

	/**
	 * Se cambia el usuario registrado con el id seleccionado. Se verifica la
	 * contrasenia pasada y se actualiza con la nueva
	 * 
	 * Autor: hellequin
	 * 
	 * @param idUser
	 * @param contrasenia
	 * @param nuevaContrasenia
	 * @return
	 * @throws ExcepcionAplicacion
	 *             Fecha de Cracion: May 7, 2016
	 * @throws ExceptionDAO
	 */
	public boolean cambiarContrasenia(Long idUser, String contrasenia, String nuevaContrasenia) throws ExcepcionAplicacion, ExceptionDAO {
		try {
			/**
			 * Se consulta el usuario de seguridad por el id del usuario
			 */
			Usuario usuario = usuarioDAO.consultarXId(idUser);
			// Se consulta el usuario de seguridad
			UserMembership userMembership = userMembershipDAO.consultarXId(usuario.getUserMembership().getUmpCodigo());
			ConnectionSecurity connectionSecurity = new ConnectionSecurity();
			if (connectionSecurity.corroborateUserstent(usuario.getUsuUsuario(), contrasenia)) {
				EncryptionSecurity encryptionSecurity = new EncryptionSecurity();
				String pass = encryptionSecurity.encryptUserPasswordNotSalt(nuevaContrasenia);
				userMembership.setUmpPasswordhash(pass);
				userMembership.setUmpSecuritysalt("123");
				return true;
			}
			throw new ExcepcionAplicacion("La informacion del usuario no fue procesada correctamente!");
		} catch (ExceptionDAO ex) {
			throw new ExceptionDAO(ex.getMessage());
		} catch (Exception e) {
			throw new ExcepcionAplicacion(e.getMessage());
		}
	}

	/**
	 * Metodo que elimina un usuario
	 * 
	 * Autor: hellequin
	 * 
	 * @param usuarioDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public void eliminarUsuario(Long id) throws ExceptionDAO {
		// Se consulta el usuario
		Usuario usuario = usuarioDAO.consultarXId(id);
		// Se elimina el usuario
		usuarioDAO.eliminar(usuario);
	}

	/**
	 * Metodo que consulta un usuario con su informacion basica por su id
	 * 
	 * @author hellequin
	 * @param id
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public UsuarioDTO consultarUsuarioSimpleXid(Long id) throws ExceptionDAO {
		Usuario usuario = usuarioDAO.consultarXId(id);
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		// Se consulta la bala de seguridad con los datos asociados del usuario
		if (usuario.getUserMembership() != null && usuario.getUserMembership().getUmpCodigo() > 0L) {
			UserMembership userMembership = userMembershipDAO.consultarXId(usuario.getUserMembership().getUmpCodigo());
			usuarioDTO.setUserMembershipDTO(new UserMembershipDTO(userMembership));
		}
		// Se retorna el usuario con toda su informacion
		return usuarioDTO;
	}

	/**
	 * Metodo que consulta todos los usuarios registrados
	 * 
	 * Autor: hellequin
	 * 
	 * @return
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public List<UsuarioDTO> consultarUsuariosTodos() throws ExceptionDAO {
		// Lista de usuarios que se devuelve
		List<UsuarioDTO> lsUsuarioDTOs = new ArrayList<UsuarioDTO>();
		// Se consultan todos los usuarios
		for (Usuario iteraUsu : usuarioDAO.consultarTodos()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(iteraUsu);
			lsUsuarioDTOs.add(usuarioDTO);
		}
		// Se retornan todos los usuarios consultados
		return lsUsuarioDTOs;
	}

	/**
	 * Metodo que consulta usuarios por diferenste criterios de busqueda
	 * 
	 * Autor: hellequin
	 * 
	 * @param user
	 * @param userName
	 * @return
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 5, 2016
	 */
	public List<UsuarioDTO> consultarUsuariosXParametros(String user, String name) throws ExceptionDAO {
		// Lista de usuarios que se devuelve
		List<UsuarioDTO> lsUsuarioDTOs = new ArrayList<UsuarioDTO>();
		// Se consultan todos los usuarios
		for (Usuario iteraUsu : usuarioDAO.consultarUsuariosXParametros(user, name)) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(iteraUsu);
			lsUsuarioDTOs.add(usuarioDTO);
		}
		// Se retornan todos los usuarios consultados
		return lsUsuarioDTOs;
	}

	/*-----------------------ACCIONES------------------------*/

	/**
	 * Se registra una accion
	 * 
	 * @author hellequin
	 * @param accionDTO
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public void crearAccion(AccionDTO accionDTO) throws ExceptionDAO {
		Accion accion = generalEntityConversion.AssembleEntityAccion(accionDTO);
		accionDAO.crear(accion);
	}

	/**
	 * Metodo que consulta la accion por su id
	 * 
	 * @author hellequin
	 * @param id
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public AccionDTO consultarAccionXId(Long id) throws ExceptionDAO {
		Accion accion = accionDAO.consultarXId(id);
		return new AccionDTO(accion);
	}

	/**
	 * Metodo que actualiza una accion
	 * 
	 * @author hellequin
	 * @param accionDTO
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public void actualizarAccion(AccionDTO accionDTO) throws ExceptionDAO {
		Accion accion = generalEntityConversion.AssembleEntityAccion(accionDTO);
		accionDAO.actualizar(accion);
	}

	/**
	 * Metodo que elimina una accion
	 * 
	 * Autor: hellequin
	 * 
	 * @param accionDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 * @throws ExcepcionAplicacion
	 */
	public void eliminarAccion(long id) throws ExceptionDAO, ExcepcionAplicacion {
		try {
			// Se consulta la accion
			Accion accion = accionDAO.consultarXId(id);
			if (accion == null)
				throw new ExceptionDAO("No existe la acción consultada.");
			// Se elimina el registro
			accionDAO.eliminar(accion);

		} catch (Exception e) {
			throw new ExcepcionAplicacion(e.getMessage());
		}
	}

	/**
	 * Metodo que consulta todas las acciones registradas
	 * 
	 * @author hellequin
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public List<AccionDTO> consultarAccionesTodas() throws ExceptionDAO {
		List<AccionDTO> accionDTOs = new ArrayList<AccionDTO>();
		for (Accion iteraAccion : accionDAO.consultarTodos()) {
			AccionDTO accionDTO = new AccionDTO(iteraAccion);
			accionDTOs.add(accionDTO);
		}
		return accionDTOs;
	}

	/**
	 * Metdodo que consulta las acciones pertenecientes a un rol
	 * 
	 * Autor: hellequin
	 * 
	 * @param idRol
	 * @return
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 6, 2016
	 */
	public List<AccionDTO> consultarAccionesXIdRol(Long id) throws ExceptionDAO {
		List<AccionDTO> accionDTOs = new ArrayList<AccionDTO>();
		for (Accion iteraAccion : accionDAO.consultarAccionesXIdRol(id)) {
			AccionDTO accionDTO = new AccionDTO(iteraAccion);
			accionDTOs.add(accionDTO);
		}
		return accionDTOs;
	}

	/*-----------------------GRUPOS------------------------*/
	/**
	 * Metodo que registra un grupo
	 * 
	 * Autor: hellequin
	 * 
	 * @param grupoDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public void crearGrupo(GrupoDTO grupoDTO) throws ExceptionDAO {
		Grupo grupo = generalEntityConversion.AssembleEntityGrupo(grupoDTO);
		grupoDAO.crear(grupo);
	}

	/**
	 * Metodo que consulta un grupo por su id
	 * 
	 * @author hellequin
	 * @param id
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public GrupoDTO consultarGrupoXId(Long id) throws ExceptionDAO {
		// Se consulta el grupo
		Grupo grupo = grupoDAO.consultarXId(id);
		return new GrupoDTO(grupo);
	}

	/**
	 * Metodo que actualiza un grupo
	 * 
	 * @author hellequin
	 * @param accionDTO
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public void actualizarGrupo(GrupoDTO grupoDTO) throws ExceptionDAO {
		Grupo grupo = generalEntityConversion.AssembleEntityGrupo(grupoDTO);
		grupoDAO.actualizar(grupo);
	}

	/**
	 * Metodo que actualiza un grupo completo y sus relaciones
	 * 
	 * Autor: hellequin
	 * 
	 * @param grupoDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 4, 2016
	 * @throws ExcepcionAplicacion
	 */
	public void actualizarGrupoCompleto(GrupoDTO grupoDTO) throws ExceptionDAO, ExcepcionAplicacion {
		try {
			Grupo grupo = generalEntityConversion.AssembleEntityGrupo(grupoDTO);
			grupoDAO.actualizar(grupo);
			/* Se actualizan las relaciones */
			/* Roles */
			for (GrupoRol iteraGrupoRol : grupoRolDAO.consultarGrupoRol(null, grupo.getGpoCodigo())) {
				// Se consultan los roles asociados previamente al grupo
				boolean eliminado = true;
				for (RolDTO iteraRolDTO : grupoDTO.getLsRolDTOs()) {
					if (iteraGrupoRol.getRol().getRolCodigo() == iteraRolDTO.getRolCodigo()) {
						eliminado = false;
						break;
					}
				}
				if (eliminado)
					grupoRolDAO.eliminar(iteraGrupoRol);
				else
					grupoRolDAO.crear(iteraGrupoRol);
			}
			/* Usuarios */
			for (UsuarioGrupo iteraUsuarioGrupo : usuarioGrupoDAO.consultarUsuarioGrupo(null, grupo.getGpoCodigo())) {
				// Se consultan los roles asociados previamente al grupo
				boolean eliminado = true;
				for (UsuarioDTO iteraUsuDTO : grupoDTO.getLsUsuarioDTOs()) {
					if (iteraUsuarioGrupo.getUsuario().getUsuCodigo() == iteraUsuDTO.getUsuCodigo()) {
						eliminado = false;
						break;
					}
				}
				if (eliminado)
					usuarioGrupoDAO.eliminar(iteraUsuarioGrupo);
				else
					usuarioGrupoDAO.crear(iteraUsuarioGrupo);
			}
		} catch (Exception e) {
			throw new ExcepcionAplicacion(e.getMessage());
		}
	}

	/**
	 * Metodo que elimina una accion
	 * 
	 * Autor: hellequin
	 * 
	 * @param accionDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public void eliminarGrupo(GrupoDTO grupoDTO) throws ExceptionDAO {
		grupoDAO.eliminar(generalEntityConversion.AssembleEntityGrupo(grupoDTO));
	}

	/**
	 * Metodo que consulta todos los grupos registrados
	 * 
	 * @author hellequin
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public List<GrupoDTO> consultarGruposTodos() throws ExceptionDAO {
		List<GrupoDTO> grupoDTOs = new ArrayList<GrupoDTO>();
		for (Grupo grupo : grupoDAO.consultarTodos()) {
			GrupoDTO grupoDTO = new GrupoDTO(grupo);
			grupoDTOs.add(grupoDTO);
		}
		return grupoDTOs;
	}

	/**
	 * Metodo que consulta el establecimiento con todas sus relaciones
	 * 
	 * Autor: hellequin
	 * 
	 * @param id
	 * @return
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public GrupoDTO consultarGrupoCompletoXId(Long id) throws ExceptionDAO {
		// Se consulta el grupo
		Grupo grupo = grupoDAO.consultarXId(id);
		GrupoDTO grupoDTO = new GrupoDTO(grupo);
		/* Se consultan las relaciones de la entidad */
		// Los usuarios asociados
		for (Usuario iteraUsuario : usuarioDAO.consultarUsuarioXIdGpu(id)) {
			grupoDTO.getLsUsuarioDTOs().add(new UsuarioDTO(iteraUsuario));
		}
		// Los roles asignados
		for (Rol iteraRol : rolDAO.consultarRolXIdGpu(id)) {
			grupoDTO.getLsRolDTOs().add(new RolDTO(iteraRol));
		}
		return grupoDTO;
	}

	/*-----------------------RECURSOS------------------------*/
	/**
	 * Metodo que registra un recurso
	 * 
	 * Autor: hellequin
	 * 
	 * @param grupoDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public Long crearRecurso(RecursoDTO recursoDTO) throws ExceptionDAO {
		Recurso recurso = generalEntityConversion.AssembleEntityRecurso(recursoDTO);
		recursoDAO.crear(recurso);
		return recurso.getRecCodigo();
	}

	/**
	 * Metodo que consulta un recurso por su id
	 * 
	 * @author hellequin
	 * @param id
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public RecursoDTO consultarRecursoXId(Long id) throws ExceptionDAO {
		// Se consulta el grupo
		Recurso recurso = recursoDAO.consultarXId(id);
		return new RecursoDTO(recurso);
	}

	/**
	 * Metodo que consulta todos los recursos registrado
	 * 
	 * @author hellequin
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public List<RecursoDTO> consultarRecursosTodos() throws ExceptionDAO {
		List<RecursoDTO> lsRecursoDTOs = new ArrayList<RecursoDTO>();
		for (Recurso iteraRecurso : recursoDAO.consultarTodos()) {
			RecursoDTO recursoDTO = new RecursoDTO(iteraRecurso);
			lsRecursoDTOs.add(recursoDTO);
		}
		return lsRecursoDTOs;
	}

	/**
	 * Consulta todos los recursos asociados a un usuario. Se debe tener en
	 * cuenta que solo se toman de los resurso, roles y grupos que esten activos
	 * 
	 * @author racso
	 * @param idUser
	 * @return
	 * @throws ExceptionDAO
	 */
	public List<RecursoDTO> consultarRecursosXUsuario(Long idUser) throws ExceptionDAO {
		List<RecursoDTO> lsRecursoDTOs = new ArrayList<RecursoDTO>();
		for (Recurso iteraRecurso : recursoDAO.consultarRecursosXUsuario(idUser)) {
			RecursoDTO recursoDTO = new RecursoDTO(iteraRecurso);
			lsRecursoDTOs.add(recursoDTO);
		}
		return lsRecursoDTOs;
	}

	/**
	 * Actualiza el usuario que es enviado con todos sus parametros
	 * 
	 * @author racso
	 * @param recursoDTO
	 * @return
	 * @throws ExceptionDAO
	 */
	public boolean actualizarRecusrso(RecursoDTO recursoDTO) throws ExceptionDAO {
		Recurso recurso = generalEntityConversion.AssembleEntityRecurso(recursoDTO);
		return recursoDAO.actualizar(recurso);
	}

	/**
	 * Metodo que elimina un recurso
	 * 
	 * Autor: hellequin
	 * 
	 * @param accionDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public boolean eliminarRecurso(RecursoDTO recursoDTO) throws ExceptionDAO {
		return recursoDAO.eliminar(generalEntityConversion.AssembleEntityRecurso(recursoDTO));
	}

	/*-----------------------ROL------------------------*/

	/**
	 * Metodo que registra un rol
	 * 
	 * Autor: hellequin
	 * 
	 * @param grupoDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public void crearRol(RolDTO rolDTO) throws ExceptionDAO {
		rolDAO.crear(generalEntityConversion.AssembleEntityRol(rolDTO));
	}

	/**
	 * Metodo que consulta un rol por su id
	 * 
	 * @author hellequin
	 * @param id
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public RolDTO consultarRolXId(Long id) throws ExceptionDAO {
		return new RolDTO(rolDAO.consultarXId(id));
	}

	/**
	 * Metodo que consulta todos los roles registrado
	 * 
	 * @author hellequin
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public List<RolDTO> consultarRolesTodos() throws ExceptionDAO {
		List<RolDTO> lsRolDTOs = new ArrayList<RolDTO>();
		for (Rol iteraRol : rolDAO.consultarTodos()) {
			lsRolDTOs.add(new RolDTO(iteraRol));
		}
		return lsRolDTOs;
	}

	/**
	 * Metodo que actualiza un rol
	 * 
	 * @author hellequin
	 * @param accionDTO
	 * @throws ExceptionDAO
	 * @date Apr 23, 2016
	 */
	public void actualizarRol(RolDTO rolDTO) throws ExceptionDAO {
		rolDAO.actualizar(generalEntityConversion.AssembleEntityRol(rolDTO));
	}

	/**
	 * Metodo que actualiza un rol completo y sus relaciones
	 * 
	 * Autor: hellequin
	 * 
	 * @param grupoDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 4, 2016
	 */
	public void actualizarRolCompleto(RolDTO rolDTO) throws ExceptionDAO {
		Rol rol = generalEntityConversion.AssembleEntityRol(rolDTO);
		rolDAO.actualizar(rol);
		/* Se actualizan las relaciones */
		// Acciones

	}

	/**
	 * Metodo que elimina un rol
	 * 
	 * Autor: hellequin
	 * 
	 * @param accionDTO
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 3, 2016
	 */
	public void eliminarRol(RolDTO rolDTO) throws ExceptionDAO {
		rolDAO.eliminar(generalEntityConversion.AssembleEntityRol(rolDTO));
	}

}
