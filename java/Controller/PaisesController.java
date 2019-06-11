/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Paises;
import Facade.PaisesFacade;
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
@Named(value = "paisesController")
@SessionScoped
public class PaisesController implements Serializable {

@EJB
private PaisesFacade paisesFacade; 
private Paises paises = new Paises();
private boolean confirm = false; 

public List<Paises> findAll(){
    return paisesFacade.findAll(); 
}

public List<Paises> findAll2(){
    return paisesFacade.findAll2(); 
}

public List<Paises> findByName(){
    return paisesFacade.findByName("MEXICO"); 
}

public String insert(){
     FacesMessage msj; 
    try{
    paisesFacade.insert(paises);
    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del pais "+paises.getNombre()+" ha sido agregado exitosamente", "");
   FacesContext.getCurrentInstance().addMessage("paisesAlta", msj);
    clean();
    }catch(Exception e){
        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del pais "+paises.getNombre()+" no pudo ser agregado. Contacte a soporte.", "");
   FacesContext.getCurrentInstance().addMessage("paisesAlta", msj);
    }
    return "paisesAlta"; 
}

public String prepareEdit(Paises p){
paises = p;
return "paisesEdit";
}

public String update(){
    FacesMessage msj; 
    try{
    paisesFacade.update(paises);
    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del pais "+paises.getNombre()+" ha sido modificado exitosamente", "");
   FacesContext.getCurrentInstance().addMessage("paisesEdit", msj);
clean();
    }catch(Exception e){
        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del pais "+paises.getNombre()+" no pudo ser modificado. Contacte a soporte.", "");
   FacesContext.getCurrentInstance().addMessage("paisesEdit", msj);
    }
 
return ""; 
}

public Paises find(Long id){
return paisesFacade.find(id); 
}

public void clean(){
    paises = new Paises();
}


public boolean flag(Paises p){
    return p.equals(paises); 
}

public void prepararEliminar(Paises p){
    paises = p; 
}

public String mainClean(String url){
paises = new Paises(); 
setConfirm(false);
return url; 
}

public String prepareConfirm(){
    setConfirm(true);
    return "paisesList"; 
}

public void delete(){
    FacesMessage msj;
try{
    if(paises.getEstadosList().isEmpty()){
   paisesFacade.delete(paises); 
   msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del pais "+paises.getNombre()+" ha sido eliminado exitosamente", "");
   FacesContext.getCurrentInstance().addMessage("paisesList", msj);
    }else{
    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del pais "+paises.getNombre()+" no pudo ser eliminado porque tiene dependientes.", "");
   FacesContext.getCurrentInstance().addMessage("paisesList", msj);
    }
}catch(Exception e){
    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del pais "+paises.getNombre()+" no pudo ser eliminado. Contacte a soporte.", "");
   FacesContext.getCurrentInstance().addMessage("paisesList", msj);
}
mainClean("paisesList"); 
}

    /**
     * @return the paises
     */
    public Paises getPaises() {
        return paises;
    }

    /**
     * @param paises the paises to set
     */
    public void setPaises(Paises paises) {
        this.paises = paises;
    }
    
        public boolean isConfirm(){
    return confirm;
    }
    
    public void setConfirm(boolean confirm){
    this.confirm = confirm; 
    }
    
}
