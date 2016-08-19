package co.edu.unipiloto.proca3si.web.util.resources;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Recursos {
	private Properties properties = null;
	private ResourceBundle rb;

	/**
	 * Metodo que carga las propiedades necesarias para obtener los
	 * identificadores asignados a diferentes recursos usados en la aplicacion
	 * 
	 * @author hellequin
	 * @param nombreArchivo
	 * @return properties. propiedades cargadas del archivo
	 * @date Apr 12, 2016
	 */
	public Properties cargarPropiedades(String nombreArchivo) {
		try {
			if (properties == null) {
				properties = new Properties();
				properties.load(Recursos.class.getResourceAsStream(nombreArchivo + ".properties"));
			}
		} catch (java.io.IOException exc) {
			System.out.println("Error cargando archivo de propiedades" + exc.getMessage());
		}
		return properties;
	}

	/**
	 * 
	 * @author hellequin
	 * @return
	 * @date Apr 12, 2016
	 */
	private Properties obtenerProperties() {
		try {
			properties = new Properties();
			properties.load(Recursos.class.getResourceAsStream("recursos.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * Metodo que obtiene recusro del recursos.properties
	 * 
	 * @author hellequin
	 * @param recurso
	 * @return
	 * @date Apr 12, 2016
	 */
	public String obtenerRecursoSimple(String recurso) {
		Properties dom = obtenerProperties();
		return dom.getProperty(recurso);
	}

	/**
	 * Metodo que obtiene el valor de la propiedad pasada como parametro
	 * 
	 * @param propiedad
	 *            Nombre de la propiedad cuyo valor se extraera del archivo de
	 *            propiedades
	 * @return Valor asignado a la propiedad especificada
	 */
	public String obtenerRecurso(String propiedad, String archivo) {
		Properties domPropiedades = cargarPropiedades(archivo);
		return domPropiedades.getProperty(propiedad);
	}

	/**
	 * Metodo que obtiene el valor de la propiedad pasada como parametro
	 *
	 * @author hellequin
	 * @param propiedad
	 *            Nombre de la propiedad cuyo valor se extraera del archivo de
	 *            propiedades
	 * @param archivo
	 *            Nombre del archivo de propiedades
	 * @return Valor asignado a la propiedad especificada
	 * @date Apr 12, 2016
	 */
	public String obtenerPropiedad(String propiedad, String archivo) {
		rb = ResourceBundle.getBundle("co/com/proca3si/ejb/util/recursos/" + archivo);
		return rb.getString(propiedad);
	}
}
