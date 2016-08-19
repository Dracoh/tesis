package co.edu.proca3si.ejb.persistence.dao.administration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import co.edu.proca3si.ejb.common.exception.ExceptionDAO;
import co.edu.proca3si.ejb.persistence.dao.DAO;
import co.edu.proca3si.ejb.persistence.entities.Accion;

@Stateless
@LocalBean
public class AccionDAO extends DAO<Accion> {

	/**
	 * 
	 * CONSTRUCTOR
	 */
	public AccionDAO() {
		entity = new Accion();
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
	public List<Accion> consultarAccionesXIdRol(long id) throws ExceptionDAO {
		// Lista que se va a retornar
		List<Accion> lstAccion = new ArrayList<Accion>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT acn FROM Accion acn, RolAccion ran, Rol rol");
			sql.append(" WHERE acn.acnCodigo IS NOT NULL");
			sql.append(" AND ran.accion.acnCodigo = acn.acnCodigo");
			sql.append(" AND ran.rol.rolCodigo = rol.rolCodigo");
			// sql.append(" AND rol.acnEstado = true");
			sql.append(" AND rol.rolCodigo = :rolCodigo");
			// Se ordena la cosnulta
			sql.append(" ORDER BY rol.rolCodigo");

			Query consulta = manager.createQuery(sql.toString());

			consulta.setParameter("rolCodigo", id);
			// Se realiza la consulta
			lstAccion = consulta.getResultList();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarAccionesXIdRol: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return lstAccion;
	}
}