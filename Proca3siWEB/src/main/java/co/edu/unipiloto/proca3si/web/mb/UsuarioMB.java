package co.edu.unipiloto.proca3si.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import co.edu.unipiloto.proca3si.conecionCliente.UsuarioWR;
import co.edu.unipiloto.proca3si.web.DTO.UsuarioDTO;
import co.edu.unipiloto.proca3si.web.util.JsfUtil;
import co.edu.unipiloto.proca3si.web.util.enumerations.Operation;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private UsuarioDTO usuario; 
	private List<UsuarioDTO> usuarios;
	private List<UsuarioDTO> filtroUsuarios; 
	private boolean isCreate; 
	
	public UsuarioMB(){
	}
	
	@PostConstruct
	public void init(){
		usuario = new UsuarioDTO();
		usuarios = new ArrayList<UsuarioDTO>();
		isCreate = true; 
		cargarUsuarios();
	}
	
	//actions	
	public void onRowSelect(SelectEvent event) {
		RequestContext.getCurrentInstance().update("frmEditarUsuario:panelEdit");
        RequestContext.getCurrentInstance().execute("PF('dlgEditarUsuario').show()");
    }

	public void crearModifcarUsuario(char operacion){
		try{
			if(operacion == 'C'){
				UsuarioWR usuarioWr = new UsuarioWR(Operation.CREAR.getOperacion());	
				usuarioWr.crearUsuario(usuario);
				JsfUtil.addSuccessMessage("Usuario agregado con exito");
				reinicio(true);
			}else if (operacion == 'E'){
				UsuarioWR usuarioWr = new UsuarioWR(Operation.ACTUALIZAR.getOperacion());	
				usuarioWr.editarUsuario(usuario);
				JsfUtil.addSuccessMessage("Usuario actualizado con exito");
				reinicio(true);
			}else{
				JsfUtil.addSuccessMessage("Operación invalida");
			}	
		}catch(Exception ex){
			JsfUtil.addErrorMessage("Error : " + ex.getMessage());
			ex.printStackTrace();
		}
	}   
	
	public void cargarUsuarios(){
		try{
			UsuarioWR usuarioWr = new UsuarioWR(Operation.CONSULTAR.getOperacion());	
			usuarios = usuarioWr.getUsuarios();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void antesAgregar(){
		reinicio(false);
	}

    private void reinicio(boolean isFullReset){
    	usuario = null; 
    	usuario = new UsuarioDTO();
    	if(isFullReset){ 
    		cargarUsuarios(); 
    	}
    }
	
	//getter and setter
	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public List<UsuarioDTO> getFiltroUsuarios() {
		return filtroUsuarios;
	}

	public void setFiltroUsuarios(List<UsuarioDTO> filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}
	
}
