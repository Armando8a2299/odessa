/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Aviones;
import Facade.AvionesFacade;
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
@Named(value = "avionesController")
@SessionScoped
public class AvionesController implements Serializable {

    @EJB
    private AvionesFacade avionesFacade;
    private Aviones aviones = new Aviones();
    private boolean confirm = false;

    public List<Aviones> findAll() {
        return avionesFacade.findAll();
    }

    public List<Aviones> findAll2() {
        return avionesFacade.findAll2();
    }

    public List<Aviones> findByNumeroAvion() {
        return avionesFacade.findByNumeroAvion("1");
    }

    public List<Aviones> findByNumeroYcapacidad() {
        return avionesFacade.findByNumeroYcapacidad("1", 100);
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (avionesFacade.findByNumeroAvion(aviones.getNumeroAvion()).isEmpty()) {
                avionesFacade.insert(aviones);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del avion número " + aviones.getNumeroAvion() + " ha sido agregado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("avionesAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avion número " + aviones.getNumeroAvion() + " ya esta ocupado.", "");
                FacesContext.getCurrentInstance().addMessage("avionesAlta", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avion número " + aviones.getNumeroAvion() + " no pudo ser agregado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("avionesAlta", msj);
        }
        return "avionesAlta";
    }

    public String prepareEdit(Aviones a) {
        aviones = a;
        return "avionesEdit";
    }

    public String update() {
        FacesMessage msj;
        try {
//            if (avionesFacade.findByNumeroAvion(aviones.getNumeroAvion()).isEmpty()) {
                avionesFacade.update(aviones);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del avion número " + aviones.getNumeroAvion() + " ha sido modificado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("avionesEdit", msj);
                clean();
//            } else {
//                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avion número " + aviones.getNumeroAvion() + " ya esta ocupado.", "");
//                FacesContext.getCurrentInstance().addMessage("avionesEdit", msj);
//            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avion número " + aviones.getNumeroAvion() + " no pudo ser modificado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("avionesEdit", msj);
        }
        return "";
    }

    public Aviones find(Long id) {
        return avionesFacade.find(id);
    }

    public void clean() {
        aviones = new Aviones();
    }

    public String mainClean(String url) {
        aviones = new Aviones();
        setConfirm(false);
        return url;
    }

    public String prepareConfirm() {
        setConfirm(true);
        return "avionesList";
    }

    public void delete() {
        FacesMessage msj;
        try {
            if (aviones.getVuelosList().isEmpty()) {

                avionesFacade.delete(aviones);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del avion número " + aviones.getNumeroAvion() + " ha sido eliminado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("avionesList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avion número " + aviones.getNumeroAvion() + " no pudo ser eliminado porque tiene dependientes.", "");
                FacesContext.getCurrentInstance().addMessage("avionesList", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avion número " + aviones.getNumeroAvion() + " no pudo ser eliminado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("avionesList", msj);
        }
        mainClean("avionesList");
    }

    public boolean flag(Aviones a) {
        return a.equals(aviones);
    }

    public void prepararEliminar(Aviones a) {
        aviones = a;
    }

    /**
     * @return the aviones
     */
    public Aviones getAviones() {
        return aviones;
    }

    /**
     * @param aviones the aviones to set
     */
    public void setAviones(Aviones aviones) {
        this.aviones = aviones;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
