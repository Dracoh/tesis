package co.edu.unipiloto.proca3si.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import co.edu.unipiloto.proca3si.conecionCliente.AccionWR;
import co.edu.unipiloto.proca3si.web.DTO.AccionDTO;
import co.edu.unipiloto.proca3si.web.util.JsfUtil;
import co.edu.unipiloto.proca3si.web.util.enumerations.Operation;

@ManagedBean
@ViewScoped
public class AccionMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AccionDTO accion; 
	private List<AccionDTO> acciones;
	private List<AccionDTO> filtroAcciones; 
	private boolean isCreate; 
	
	public AccionMB(){
	}
	
	@PostConstruct
	public void init(){
		accion = new AccionDTO();
		acciones = new ArrayList<AccionDTO>();
		isCreate = true; 
		cargarAcciones();
	}
	
	//actions	
	public void onRowSelect(SelectEvent event) {
		RequestContext.getCurrentInstance().update("frmEditarAccion:panelEdit");
        RequestContext.getCurrentInstance().execute("PF('dlgEditarAccion').show()");
    }

	public void crearModifcarAccion(char operacion){
		try{
			if(operacion == 'C'){
				AccionWR accionWr = new AccionWR(Operation.CREAR.getOperacion());	
				accionWr.crearAccion(accion);
				JsfUtil.addSuccessMessage("Accion agregado con exito");
				reinicio(true);
			}else if (operacion == 'E'){
				AccionWR accionWr = new AccionWR(Operation.ACTUALIZAR.getOperacion());	
				accionWr.editarAccion(accion);
				JsfUtil.addSuccessMessage("Accion actualizado con exito");
				reinicio(true);
			}else{
				JsfUtil.addSuccessMessage("Operación invalida");
			}	
		}catch(Exception ex){
			JsfUtil.addErrorMessage("Error : " + ex.getMessage());
			ex.printStackTrace();
		}
	}   
	
	public void cargarAcciones(){
		try{
			AccionWR accionWr = new AccionWR(Operation.CONSULTAR.getOperacion());	
			acciones = accionWr.getAcciones();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void antesAgregar(){
		reinicio(false);
	}

    private void reinicio(boolean isFullReset){
    	accion = null; 
    	accion = new AccionDTO();
    	if(isFullReset){ 
    		cargarAcciones(); 
    	}
    }
	
	//getter and setter
	public List<AccionDTO> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionDTO> acciones) {
		this.acciones = acciones;
	}

	public AccionDTO getAccion() {
		return accion;
	}

	public void setAccion(AccionDTO accion) {
		this.accion = accion;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public List<AccionDTO> getFiltroAcciones() {
		return filtroAcciones;
	}

	public void setFiltroAcciones(List<AccionDTO> filtroAcciones) {
		this.filtroAcciones = filtroAcciones;
	}
	
}
