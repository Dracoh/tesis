package co.edu.unipiloto.proca3si.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import co.edu.unipiloto.proca3si.conecionCliente.AplicacionWR;
import co.edu.unipiloto.proca3si.web.DTO.AplicacionDTO;
import co.edu.unipiloto.proca3si.web.util.JsfUtil;
import co.edu.unipiloto.proca3si.web.util.enumerations.Operation;

@ManagedBean
@ViewScoped
public class AplicacionMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AplicacionDTO aplicacion; 
	private List<AplicacionDTO> aplicaciones;
	private List<AplicacionDTO> filtroAplicaciones; 
	private boolean isCreate; 
	
	public AplicacionMB(){
	}
	
	@PostConstruct
	public void init(){
		aplicacion = new AplicacionDTO();
		aplicaciones = new ArrayList<AplicacionDTO>();
		isCreate = true; 
		cargarAplicaciones();
	}
	
	//actions	
	public void onRowSelect(SelectEvent event) {
		RequestContext.getCurrentInstance().update("frmEditarAplicacion:panelEdit");
        RequestContext.getCurrentInstance().execute("PF('dlgEditarAplicacion').show()");
    }

	public void crearModifcarAplicacion(char operacion){
		try{
			if(operacion == 'C'){
				AplicacionWR aplicacionWr = new AplicacionWR(Operation.CREAR.getOperacion());	
				aplicacionWr.crearAplicacion(aplicacion);
				JsfUtil.addSuccessMessage("Aplicacion agregado con exito");
				reinicio(true);
			}else if (operacion == 'E'){
				AplicacionWR aplicacionWr = new AplicacionWR(Operation.ACTUALIZAR.getOperacion());	
				aplicacionWr.editarAplicacion(aplicacion);
				JsfUtil.addSuccessMessage("Aplicacion actualizado con exito");
				reinicio(true);
			}else{
				JsfUtil.addSuccessMessage("Operación invalida");
			}	
		}catch(Exception ex){
			JsfUtil.addErrorMessage("Error : " + ex.getMessage());
			ex.printStackTrace();
		}
	}   
	
	public void cargarAplicaciones(){
		try{
			AplicacionWR aplicacionWr = new AplicacionWR(Operation.CONSULTAR.getOperacion());	
			aplicaciones = aplicacionWr.getAplicaciones();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void antesAgregar(){
		reinicio(false);
	}

    private void reinicio(boolean isFullReset){
    	aplicacion = null; 
    	aplicacion = new AplicacionDTO();
    	if(isFullReset){ 
    		cargarAplicaciones(); 
    	}
    }
	
	//getter and setter
	public List<AplicacionDTO> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(List<AplicacionDTO> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public AplicacionDTO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionDTO aplicacion) {
		this.aplicacion = aplicacion;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public List<AplicacionDTO> getFiltroAplicaciones() {
		return filtroAplicaciones;
	}

	public void setFiltroAplicaciones(List<AplicacionDTO> filtroAplicaciones) {
		this.filtroAplicaciones = filtroAplicaciones;
	}
	
}
