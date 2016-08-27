package co.edu.unipiloto.proca3si.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfUtil {

	public static void addErrorMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, " ", msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}
	
	public static void addSuccessMessage(String msg){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, " ", msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}
	                
	public static void addSeverityWarnMessage(String msg){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, " ", msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

}
