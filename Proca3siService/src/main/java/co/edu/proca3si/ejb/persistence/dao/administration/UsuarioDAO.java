/**
 * 
 */
package co.edu.proca3si.ejb.persistence.dao.administration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import co.edu.proca3si.ejb.common.exception.ExceptionDAO;
import co.edu.proca3si.ejb.persistence.dao.DAO;
import co.edu.proca3si.ejb.persistence.entities.Usuario;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class UsuarioDAO extends DAO<Usuario> {

	/**
	 * CONSTRUCTOR
	 */
	public UsuarioDAO() {
		entity = new Usuario();
	}

	/**
	 * Metodo que consulta un usuario por su nombre de usuario
	 * 
	 * Autor: hellequin
	 * 
	 * @param user
	 * @return
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 7, 2016
	 */
	public Usuario consultarUsuarioXUsuario(String user) throws ExceptionDAO {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT usu FROM Usuario usu");
			sql.append(" WHERE usu.usuCodigo IS NOT NULL");
			// sql.append(" AND gpo.gpoEstado = true");
			sql.append(" AND usu.usuUsuario = :usuUsuario");
			// Se ordena la cosnulta
			sql.append(" ORDER BY usu.usuCodigo");

			Query consulta = manager.createQuery(sql.toString());

			consulta.setParameter("usuUsuario", user);
			// Se realiza la consulta
			return (Usuario) consulta.getSingleResult();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarUsuariosXParametros: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
	}

	/**
	 * Metodo que consulta los usuaros asociados a un grupo x el id del
	 * departamento
	 * 
	 * Autor: hellequin
	 * 
	 * @param id
	 * @return Fecha de Cracion: May 3, 2016
	 * @throws ExceptionDAO
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarUsuarioXIdGpu(long id) throws ExceptionDAO {
		// Lista que se va a retornar
		List<Usuario> lstUsuario = new ArrayList<Usuario>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT usu FROM Usuario usu, UsuarioGrupo ugo, Grupo gpo");
			sql.append(" WHERE usu.usuCodigo IS NOT NULL");
			sql.append(" AND ugo.usuario.usuCodigo = usu.usuCodigo");
			sql.append(" AND ugo.grupo.gpoCodigo = gpo.gpoCodigo");
			// sql.append(" AND gpo.gpoEstado = true");
			sql.append(" AND gpo.gpoCodigo = :gpoCodigo");
			// Se ordena la cosnulta
			sql.append(" ORDER BY gpo.gpoCodigo");

			Query consulta = manager.createQuery(sql.toString());

			consulta.setParameter("gpoCodigo", id);
			// Se realiza la consulta
			lstUsuario = consulta.getResultList();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarUsuarioXIdGpu: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return lstUsuario;
	}

	/**
	 * Metodo que consulta un usuario por diferentes parametros
	 * 
	 * Autor: hellequin
	 * 
	 * @param user
	 * @param userName
	 * @return
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 5, 2016
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarUsuariosXParametros(String user, String name) throws ExceptionDAO {
		// Lista que se va a retornar
		List<Usuario> lstUsuario = new ArrayList<Usuario>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT usu FROM Usuario usu");
			sql.append(" WHERE usu.usuCodigo IS NOT NULL");
			// sql.append(" AND gpo.gpoEstado = true");
			sql.append(" AND usu.usuUsuario = :usuUsuario");
			sql.append(" AND CONCAT(usu.usuUsuario, usu.usuApellido) LIKE :usuUsuarioUsuApellido");
			// Se ordena la cosnulta
			sql.append(" ORDER BY usu.usuCodigo");

			Query consulta = manager.createQuery(sql.toString());

			consulta.setParameter("usuUsuario", user);
			consulta.setParameter("usuUsuarioUsuApellido", name);
			// Se realiza la consulta
			lstUsuario = consulta.getResultList();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarUsuariosXParametros: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return lstUsuario;
	}
}
