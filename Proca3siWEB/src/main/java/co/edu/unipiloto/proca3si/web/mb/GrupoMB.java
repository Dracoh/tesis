package co.edu.unipiloto.proca3si.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import co.edu.unipiloto.proca3si.conecionCliente.GrupoWR;
import co.edu.unipiloto.proca3si.web.DTO.GrupoDTO;
import co.edu.unipiloto.proca3si.web.util.JsfUtil;
import co.edu.unipiloto.proca3si.web.util.enumerations.Operation;

@ManagedBean
@ViewScoped
public class GrupoMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private GrupoDTO grupo; 
	private List<GrupoDTO> grupos;
	private List<GrupoDTO> filtroGrupos; 
	private boolean isCreate; 
	
	public GrupoMB(){
	}
	
	@PostConstruct
	public void init(){
		grupo = new GrupoDTO();
		grupos = new ArrayList<GrupoDTO>();
		isCreate = true; 
		cargarGrupos();
	}
	
	//actions	
	public void onRowSelect(SelectEvent event) {
		RequestContext.getCurrentInstance().update("frmEditarGrupo:panelEdit");
        RequestContext.getCurrentInstance().execute("PF('dlgEditarGrupo').show()");
    }

	public void crearModifcarGrupo(char operacion){
		try{
			if(operacion == 'C'){
				GrupoWR grupoWr = new GrupoWR(Operation.CREAR.getOperacion());	
				grupoWr.crearGrupo(grupo);
				JsfUtil.addSuccessMessage("Grupo agregado con exito");
				reinicio(true);
			}else if (operacion == 'E'){
				GrupoWR grupoWr = new GrupoWR(Operation.ACTUALIZAR.getOperacion());	
				grupoWr.editarGrupo(grupo);
				JsfUtil.addSuccessMessage("Grupo actualizado con exito");
				reinicio(true);
			}else{
				JsfUtil.addSuccessMessage("Operación invalida");
			}	
		}catch(Exception ex){
			JsfUtil.addErrorMessage("Error : " + ex.getMessage());
			ex.printStackTrace();
		}
	}   
	
	public void cargarGrupos(){
		try{
			GrupoWR grupoWr = new GrupoWR(Operation.CONSULTAR.getOperacion());	
			grupos = grupoWr.getGrupos();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void antesAgregar(){
		reinicio(false);
	}

    private void reinicio(boolean isFullReset){
    	grupo = null; 
    	grupo = new GrupoDTO();
    	if(isFullReset){ 
    		cargarGrupos(); 
    	}
    }
	
	//getter and setter
	public List<GrupoDTO> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoDTO> grupos) {
		this.grupos = grupos;
	}

	public GrupoDTO getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoDTO grupo) {
		this.grupo = grupo;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public List<GrupoDTO> getFiltroGrupos() {
		return filtroGrupos;
	}

	public void setFiltroGrupos(List<GrupoDTO> filtroGrupos) {
		this.filtroGrupos = filtroGrupos;
	}
	
}
