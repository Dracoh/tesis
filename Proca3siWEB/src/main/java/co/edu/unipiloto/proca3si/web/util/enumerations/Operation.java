package co.edu.unipiloto.proca3si.web.util.enumerations;

public enum Operation {
	
	CREAR("crear"), 
	ACTUALIZAR("actualizar"),
	CONSULTAR("consultar"); 
	
	private Operation(String operacion){
		this.operacion = operacion; 
	}
	
	private String operacion;

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	} 
	
}
