package co.edu.unipiloto.proca3si.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import co.edu.unipiloto.proca3si.conecionCliente.RolWR;
import co.edu.unipiloto.proca3si.web.DTO.RolDTO;
import co.edu.unipiloto.proca3si.web.util.JsfUtil;
import co.edu.unipiloto.proca3si.web.util.enumerations.Operation;

@ManagedBean
@ViewScoped
public class RolMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private RolDTO rol; 
	private List<RolDTO> roles;
	private List<RolDTO> filtroRoles; 
	private boolean isCreate; 
	
	public RolMB(){
	}
	
	@PostConstruct
	public void init(){
		rol = new RolDTO();
		roles = new ArrayList<RolDTO>();
		isCreate = true; 
		cargarRoles();
	}
	
	//actions	
	public void onRowSelect(SelectEvent event) {
		RequestContext.getCurrentInstance().update("frmEditarRol:panelEdit");
        RequestContext.getCurrentInstance().execute("PF('dlgEditarRol').show()");
    }

	public void crearModifcarRol(char operacion){
		try{
			if(operacion == 'C'){
				RolWR rolWr = new RolWR(Operation.CREAR.getOperacion());	
				rolWr.crearRol(rol);
				JsfUtil.addSuccessMessage("Rol agregado con exito");
				reinicio(true);
			}else if (operacion == 'E'){
				RolWR rolWr = new RolWR(Operation.ACTUALIZAR.getOperacion());	
				rolWr.editarRol(rol);
				JsfUtil.addSuccessMessage("Rol actualizado con exito");
				reinicio(true);
			}else{
				JsfUtil.addSuccessMessage("Operación invalida");
			}	
		}catch(Exception ex){
			JsfUtil.addErrorMessage("Error : " + ex.getMessage());
			ex.printStackTrace();
		}
	}   
	
	public void cargarRoles(){
		try{
			RolWR rolWr = new RolWR(Operation.CONSULTAR.getOperacion());	
			roles = rolWr.getRoles();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void antesAgregar(){
		reinicio(false);
	}

    private void reinicio(boolean isFullReset){
    	rol = null; 
    	rol = new RolDTO();
    	if(isFullReset){ 
    		cargarRoles(); 
    	}
    }
	
	//getter and setter
	public List<RolDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolDTO> roles) {
		this.roles = roles;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public List<RolDTO> getFiltroRoles() {
		return filtroRoles;
	}

	public void setFiltroRoles(List<RolDTO> filtroRoles) {
		this.filtroRoles = filtroRoles;
	}
	
}
