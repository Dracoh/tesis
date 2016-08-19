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
import co.edu.proca3si.ejb.persistence.entities.Recurso;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class RecursoDAO extends DAO<Recurso> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public RecursoDAO() {
		entity = new Recurso();
	}

	/**
	 * Se consultan los recursos pertenecientes a el usuario
	 * 
	 * Autor: hellequin
	 * 
	 * @param user
	 * @return
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 7, 2016
	 */
	@SuppressWarnings("unchecked")
	public List<Recurso> consultarRecursosXUsuario(Long idUser) throws ExceptionDAO {
		// Lista que se va a retornar
		List<Recurso> lsRecursos = new ArrayList<Recurso>();
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT rec FROM Recurso rec, RecursoRol rrl, Rol rol, UsuarioRol uro, GrupoRol grl, Grupo gpo, UsuarioGrupo ugo, Usuario usu");
			sql.append(" WHERE rec.recCodigo IS NOT NULL");
			sql.append(" AND INNER JOIN rrl.recurso.recCodigo = rec.recCodigo");
			sql.append(" AND INNER JOIN rrl.rol.rolCodigo = rol.rolCodigo");
			sql.append(" AND LEFT JOIN uro.rol.rolCodigo = rol.rolCodigo");
			sql.append(" AND LEFT JOIN uro.usuario.usuCodigo  usu.usuCodigo");
			sql.append(" AND LEFT JOIN grl.rol.rolCodigo = rol.rolCodigo");
			sql.append(" AND LEFT JOIN grl.grupo.gpoCodigo = gpo.gpoCodigo");
			sql.append(" AND LEFT JOIN ugo.grupo.gpoCodigo = gpo.gpoCodigo");
			sql.append(" AND LEFT JOIN ugo.usuario.usuCodigo = usu.gpoCodigo");
			sql.append(" AND rec.recEstado = true AND rol.rolEstado = true AND gpo.gpoEstado = true ");
			sql.append(" AND usu.usu.usuCodigo = :usuCodigo");
			// Se ordena la cosnulta
			sql.append(" ORDER BY rec.recCodigo");

			Query consulta = manager.createQuery(sql.toString());

			consulta.setParameter("usu.usuCodigo", idUser);
			// Se realiza la consulta
			lsRecursos = consulta.getResultList();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarRecursosXUsuario: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return lsRecursos;
	}
}
