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
import co.edu.proca3si.ejb.persistence.entities.UsuarioGrupo;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class UsuarioGrupoDAO extends DAO<UsuarioGrupo> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public UsuarioGrupoDAO() {
		entity = new UsuarioGrupo();
	}

	/**
	 * Metodo que consulta la relacion entre usuarios y grupos por el id de de
	 * cualquierea de estos
	 * 
	 * Autor: hellequin
	 * 
	 * @param idRol
	 * @param idGrupo
	 * @return
	 * @throws ExceptionDAO
	 *             Fecha de Cracion: May 4, 2016
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioGrupo> consultarUsuarioGrupo(Long idUsu, Long idGrupo) throws ExceptionDAO {
		// Lista que se va a retornar
		List<UsuarioGrupo> lstUsuarioGrupo = new ArrayList<UsuarioGrupo>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ugo FROM UsuarioGrupo ugo");
			sql.append(" WHERE ugo.ugoCodigo IS NOT NULL");
			if (idUsu != null && idUsu > 0L)
				sql.append(" AND ugo.grupo.gpoCodigo = :gpoCodigo");
			if (idGrupo != null && idGrupo > 0L)
				sql.append(" AND ugo.usuario.usuCodigo = :usuCodigo");
			// Se ordena la cosnulta
			sql.append(" ORDER BY ugo.ugoCodigo");

			Query consulta = manager.createQuery(sql.toString());

			if (idUsu != null && idUsu > 0L)
				consulta.setParameter("usuCodigo", idUsu);
			if (idGrupo != null && idGrupo > 0L)
				consulta.setParameter("gpoCodigo", idGrupo);

			// Se realiza la consulta
			lstUsuarioGrupo = consulta.getResultList();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarUsuarioGrupo: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return lstUsuarioGrupo;
	}
}
