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
import co.edu.proca3si.ejb.persistence.entities.Rol;

/**
 * @author hellequin
 *
 */
@Stateless
@LocalBean
public class RolDAO extends DAO<Rol> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public RolDAO() {
		entity = new Rol();
	}

	/**
	 * Metodo que consulta los roles asociados a un grupo x el id del
	 * departamento
	 * 
	 * Autor: hellequin
	 * 
	 * @param id
	 * @return Fecha de Cracion: May 3, 2016
	 * @throws ExceptionDAO
	 */
	@SuppressWarnings("unchecked")
	public List<Rol> consultarRolXIdGpu(long id) throws ExceptionDAO {
		// Lista que se va a retornar
		List<Rol> lstRol = new ArrayList<Rol>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT rol FROM Rol rol, GrupoRol grl, Grupo gpo");
			sql.append(" WHERE rol.rolCodigo IS NOT NULL");
			sql.append(" AND grl.rol.rolCodigo = rol.rolCodigo");
			sql.append(" AND grl.grupo.gpoCodigo = gpo.gpoCodigo");
			// sql.append(" AND gpu.gpoEstado = true");
			sql.append(" AND gpo.gpoCodigo = :gpoCodigo");
			// Se ordena la cosnulta
			sql.append(" ORDER BY gpo.gpoCodigo");

			Query consulta = manager.createQuery(sql.toString());

			consulta.setParameter("gpoCodigo", id);
			// Se realiza la consulta
			lstRol = consulta.getResultList();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarRolXIdGpu: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return lstRol;
	}
}
