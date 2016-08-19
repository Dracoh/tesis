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
import co.edu.proca3si.ejb.persistence.entities.GrupoRol;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class GrupoRolDAO extends DAO<GrupoRol> {

	public GrupoRolDAO() {
		entity = new GrupoRol();
	}

	/**
	 * Metodo que consulta las relaciones entre grupos y roles
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
	public List<GrupoRol> consultarGrupoRol(Long idRol, Long idGrupo) throws ExceptionDAO {
		// Lista que se va a retornar
		List<GrupoRol> lstGrupoRol = new ArrayList<GrupoRol>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT grl FROM GrupoRol grl");
			sql.append(" WHERE grl.grlCodigo IS NOT NULL");
			if (idRol != null && idRol > 0L)
				sql.append(" AND grl.rol.rolCodigo = :rolCodigo");
			if (idRol != null && idGrupo > 0L)
				sql.append(" AND grl.grupo.gpuCodigo = :gpoCodigo");
			// Se ordena la cosnulta
			sql.append(" ORDER BY grl.grlCodigo");

			Query consulta = manager.createQuery(sql.toString());

			if (idRol != null && idRol > 0L)
				consulta.setParameter("rolCodigo", idRol);
			if (idRol != null && idGrupo > 0L)
				consulta.setParameter("gpoCodigo", idGrupo);

			// Se realiza la consulta
			lstGrupoRol = consulta.getResultList();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarGrupoRol: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return lstGrupoRol;
	}

}
