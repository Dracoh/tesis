/**
 * 
 */
package co.edu.proca3si.ejb.util.enumerations;

/**
 * @author hellequin
 *
 */
public enum KindProperties {
	mensajes_sistema(1), recursos(2);
	double propiedad;

	KindProperties(double p) {
		propiedad = p;
	}

	public double getPrecio() {
		return propiedad;
	}
}
