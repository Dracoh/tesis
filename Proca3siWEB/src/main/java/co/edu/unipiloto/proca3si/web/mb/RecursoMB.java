package co.edu.unipiloto.proca3si.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import co.edu.unipiloto.proca3si.conecionCliente.RecursoWR;
import co.edu.unipiloto.proca3si.web.DTO.RecursoDTO;
import co.edu.unipiloto.proca3si.web.util.JsfUtil;
import co.edu.unipiloto.proca3si.web.util.enumerations.Operation;

@ManagedBean
@ViewScoped
public class RecursoMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private RecursoDTO recurso; 
	private List<RecursoDTO> recursos;
	private List<RecursoDTO> filtroRecursos; 
	private boolean isCreate; 
	
	public RecursoMB(){
	}
	
	@PostConstruct
	public void init(){
		recurso = new RecursoDTO();
		recursos = new ArrayList<RecursoDTO>();
		isCreate = true; 
		cargarRecursos();
	}
	
	//actions	
	public void onRowSelect(SelectEvent event) {
		RequestContext.getCurrentInstance().update("frmEditarRecurso:panelEdit");
        RequestContext.getCurrentInstance().execute("PF('dlgEditarRecurso').show()");
    }

	public void crearModifcarRecurso(char operacion){
		try{
			if(operacion == 'C'){
				RecursoWR recursoWr = new RecursoWR(Operation.CREAR.getOperacion());	
				recursoWr.crearRecurso(recurso);
				JsfUtil.addSuccessMessage("Recurso agregado con exito");
				reinicio(true);
			}else if (operacion == 'E'){
				RecursoWR recursoWr = new RecursoWR(Operation.ACTUALIZAR.getOperacion());	
				recursoWr.editarRecurso(recurso);
				JsfUtil.addSuccessMessage("Recurso actualizado con exito");
				reinicio(true);
			}else{
				JsfUtil.addSuccessMessage("Operación invalida");
			}	
		}catch(Exception ex){
			JsfUtil.addErrorMessage("Error : " + ex.getMessage());
			ex.printStackTrace();
		}
	}   
	
	public void cargarRecursos(){
		try{
			RecursoWR recursoWr = new RecursoWR(Operation.CONSULTAR.getOperacion());	
			recursos = recursoWr.getRecursos();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void antesAgregar(){
		reinicio(false);
	}

    private void reinicio(boolean isFullReset){
    	recurso = null; 
    	recurso = new RecursoDTO();
    	if(isFullReset){ 
    		cargarRecursos(); 
    	}
    }
	
	//getter and setter
	public List<RecursoDTO> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<RecursoDTO> recursos) {
		this.recursos = recursos;
	}

	public RecursoDTO getRecurso() {
		return recurso;
	}

	public void setRecurso(RecursoDTO recurso) {
		this.recurso = recurso;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public List<RecursoDTO> getFiltroRecursos() {
		return filtroRecursos;
	}

	public void setFiltroRecursos(List<RecursoDTO> filtroRecursos) {
		this.filtroRecursos = filtroRecursos;
	}
	
}
