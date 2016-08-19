/**
 * 
 */
package co.edu.proca3si.ejb.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import co.edu.proca3si.ejb.common.exception.ExceptionDAO;
import co.edu.proca3si.ejb.util.enumerations.KindProperties;
import co.edu.proca3si.ejb.util.resources.Recursos;

/**
 * @author hellequin
 *
 */
// @Stateless
// @LocalBean
public abstract class DAO<TEntity> {

	@PersistenceContext(unitName = "Proca3siServicePU")
	protected EntityManager manager;
	// Recursos
	protected Recursos recursos = new Recursos();
	// Propiedades del sistema
	protected Properties propertiesSystem = recursos.cargarPropiedades(KindProperties.mensajes_sistema.name());
	// Log general de la aplicacion
	protected static Logger logger;;
	// Entidad
	protected TEntity entity;

	/**
	 * 
	 * CONSTRUCTOR
	 * 
	 * @param object
	 */
	public DAO() {
		logger = Logger.getLogger(this.getClass());
	}

	/**
	 * Metodo que registra una Entidad
	 * 
	 * @author hellequin
	 * @param entity
	 * @throws ExcepcionDAO
	 * @date Apr 14, 2016
	 */
	public void crear(TEntity entityPersisit) throws ExceptionDAO {
		try {
			manager.persist(entityPersisit);
			manager.flush();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO registrar: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CREACION"));
		}
	}

	/**
	 * Metodo que consulta una entidad por su id
	 * 
	 * @author hellequin
	 * @param id
	 * @return
	 * @date Apr 14, 2016
	 */
	@SuppressWarnings({ "unchecked" })
	public TEntity consultarXId(long id) throws ExceptionDAO {
		TEntity entityFind = null;
		try {
			entityFind = (TEntity) manager.find(entity.getClass(), id);
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO searchById: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return entityFind;
	}

	/**
	 * Mrtodo que consulta todos los registros de la entidad consultada
	 * 
	 * @author hellequin
	 * @return
	 * @throws ExceptionDAO
	 * @date Apr 15, 2016
	 */
	@SuppressWarnings("unchecked")
	public List<TEntity> consultarTodos() throws ExceptionDAO {
		List<TEntity> lstEntity = new ArrayList<TEntity>();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT e FROM " + this.getClass().getSimpleName().replace("DAO", "") + " e");

			Query query = manager.createQuery(builder.toString());
			lstEntity = query.getResultList();
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO consultarTodos: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
		return lstEntity;
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author hellequin
	 * @param tEntity
	 * @throws ExceptionDAO
	 * @date Apr 15, 2016
	 */
	public boolean actualizar(TEntity entityMerge) throws ExceptionDAO {
		try {
			manager.merge(entityMerge);
			manager.flush();
			return true;
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO actualizar: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
	}

	/**
	 * Metodo que elimina la entidad que se encuentra persistida
	 * 
	 * @author hellequin
	 * @param tEntity
	 * @throws ExceptionDAO
	 * @date Apr 15, 2016
	 */
	public boolean eliminar(TEntity entityRemove) throws ExceptionDAO {
		try {
			manager.remove(entityRemove);
			manager.flush();
			return true;
		} catch (PersistenceException e) {
			logger.error("ERROR PROCA3SI - PERSISTENCIA - METODO eliminar: " + e.getMessage());
			throw new ExceptionDAO(propertiesSystem.getProperty("ERROR_CONSULTA"));
		}
	}
}
