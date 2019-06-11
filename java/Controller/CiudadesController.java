/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Ciudades;
import Entity.Estados;
import Facade.CiudadesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;

/**
 *
 * @author iris
 */
@Named(value = "ciudadesController")
@SessionScoped
public class CiudadesController implements Serializable {

    @EJB
    private CiudadesFacade ciudadesFacade;
    private Ciudades ciudades = new Ciudades();
    private boolean confirm = false;

    public List<Ciudades> findAll() {
        return ciudadesFacade.findAll();
    }

    public List<Ciudades> findAll2() {
        return ciudadesFacade.findAll2();
    }

    public List<Ciudades> findByEstado() {
        return ciudadesFacade.findByEstado("NUEVO LEON");
    }

    public List<Ciudades> findByPais() {
        return ciudadesFacade.findByPais("MEXICO");
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (ciudadesFacade.findByEstado(ciudades.getEstado().getNombre()).isEmpty()) {
                ciudadesFacade.insert(ciudades);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de la ciudad " + ciudades.getNombre() + " ha sido agregado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad " + ciudades.getNombre() + " ya esta ocupado.", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
            }

        } catch (Exception ex) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad " + ciudades.getNombre() + " no pudo ser agregado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
        }
        return "ciudadesAlta";
    }

    public String prepareEdit(Ciudades c) {
        ciudades = c;
        return "ciudadesEdit";
    }

    public String update() {
        FacesMessage msj;
        try {
            if (ciudadesFacade.findByEstado(ciudades.getEstado().getNombre()).isEmpty()){
            ciudadesFacade.update(ciudades);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de la ciudad " + ciudades.getNombre() + " ha sido modificado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("ciudadesEdit", msj);
            clean();
            }else{
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad " + ciudades.getNombre() + " ya esta ocupado.", "");
            FacesContext.getCurrentInstance().addMessage("ciudadesEdit", msj);
            }
            
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad " + ciudades.getNombre() + " no pudo ser modificado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("ciudadesEdit", msj);
        }

        return "";
    }

    public Ciudades find(Long id) {
        return ciudadesFacade.find(id);
    }

    public void clean() {
        ciudades = new Ciudades();
    }

    public String mainClean(String url) {
        ciudades = new Ciudades();
        setConfirm(false);
        return url;
    }

    public String prepareConfirm() {
        setConfirm(true);
        return "ciudadesList";
    }

    public void delete() {
        FacesMessage msj;
        try {
            if (ciudades.getVuelosDestinoList().isEmpty() && ciudades.getVuelosOrigenList().isEmpty()) {

                ciudadesFacade.delete(ciudades);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de la ciudad " + ciudades.getNombre() + " ha sido eliminado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad " + ciudades.getNombre() + " no pudo ser eliminado porque tiene dependientes.", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesList", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad " + ciudades.getNombre() + " no pudo ser eliminado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("ciudadesList", msj);
        }
        mainClean("ciudadesList");
    }

    public boolean flag(Ciudades c) {
        return c.equals(ciudades);
    }

    public void prepararEliminar(Ciudades c) {
        ciudades = c;
    }

    /**
     * @return the ciudades
     */
    public Ciudades getCiudades() {
        return ciudades;
    }

    /**
     * @param ciudades the ciudades to set
     */
    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
