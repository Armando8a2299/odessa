/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Estados;
import Facade.EstadosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author iris
 */
@Named(value = "estadosController")
@SessionScoped
public class EstadosController implements Serializable {

@EJB
private EstadosFacade estadosFacade; 
private Estados estados = new Estados();
private boolean confirm = false; 

public List<Estados> findAll(){
    return estadosFacade.findAll(); 
}

public List<Estados> findAll2(){
    return estadosFacade.findAll2(); 
}

public String insert(){
     FacesMessage msj; 
    try{
    estadosFacade.insert(estados); 
    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del estado "+estados.getNombre()+" ha sido agregado exitosamente", "");
   FacesContext.getCurrentInstance().addMessage("estadosAlta", msj);
    clean(); 
    }catch(Exception ex){
        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del estado "+estados.getNombre()+" no pudo ser agregado. Contacte a soporte.", "");
   FacesContext.getCurrentInstance().addMessage("estadosAlta", msj);
    }
    return "estadosAlta"; 
}

public String prepareEdit(Estados e){
estados = e;
return "estadosEdit";
}




public String update(){
    FacesMessage msj; 
    try{
    estadosFacade.update(estados);
    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del estado "+estados.getNombre()+" ha sido modificado exitosamente", "");
   FacesContext.getCurrentInstance().addMessage("estadosEdit", msj);
clean(); 
    }catch(Exception e){
     msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del estado "+estados.getNombre()+" no pudo ser modificado. Contacte a soporte.", "");
   FacesContext.getCurrentInstance().addMessage("estadosEdit", msj);
    }

return ""; 
}

public Estados find(Long id){
return estadosFacade.find(id); 
}

public void clean(){
    estados = new Estados();
}

public boolean flag(Estados e){
    return e.equals(estados); 
}

public void prepararEliminar(Estados e){
    estados = e; 
}

public String mainClean(String url){
estados = new Estados(); 
setConfirm(false);
return url; 
}


public String prepareConfirm(){
    setConfirm(true);
    return "estadosList"; 
}

public void delete(){
    FacesMessage msj;
try{
    if(estados.getCiudadesList().isEmpty()){
   estadosFacade.delete(estados); 
   msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del estado "+estados.getNombre()+" ha sido eliminado exitosamente", "");
   FacesContext.getCurrentInstance().addMessage("estadosList", msj);
    }else{
        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del estado "+estados.getNombre()+" no pudo ser eliminado porque tiene dependientes.", "");
   FacesContext.getCurrentInstance().addMessage("estadosList", msj);
    }
}catch(Exception ex){
    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del estado "+estados.getNombre()+" no pudo ser eliminado. Contacte a soporte.", "");
   FacesContext.getCurrentInstance().addMessage("estadosList", msj);
}
mainClean("estadosList"); 
}

    /**
     * @return the estados
     */
    public Estados getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(Estados estados) {
        this.estados = estados;
    }
    
        public boolean isConfirm(){
    return confirm;
    }
    
    public void setConfirm(boolean confirm){
    this.confirm = confirm; 
    }
}
