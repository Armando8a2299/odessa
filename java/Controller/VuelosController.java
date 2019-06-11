/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Vuelos;
import Facade.VuelosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author iris
 */
@Named(value = "vuelosController")
@SessionScoped
public class VuelosController implements Serializable {

    @EJB
    private VuelosFacade vuelosFacade;
    private Vuelos vuelos = new Vuelos();
    private boolean confirm = false;

    public List<Vuelos> findAll() {
        return vuelosFacade.findAll();
    }

    public List<Vuelos> findAll2() {
        return vuelosFacade.findAll2();
    }

    public List<Vuelos> findByNumeroVuelo() {
        return vuelosFacade.findByNumeroVuelo("1");
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (vuelosFacade.findByNumeroVuelo(vuelos.getNumeroVuelo()).isEmpty()) {

                if (!vuelos.getOrigen().getNombre().toUpperCase().equals(vuelos.getDestino().getNombre().toUpperCase())) {
                    if (vuelos.getFechaInicioVuelo().before(vuelos.getFechaFinVuelo()) || vuelos.getFechaInicioVuelo().compareTo(vuelos.getFechaFinVuelo()) == 0) {
                        if (vuelos.getFechaInicioVuelo().compareTo(vuelos.getFechaFinVuelo()) == 0) {
                            if (vuelos.getHoraFinVuelo().toInstant().compareTo(vuelos.getHoraInicioVuelo().toInstant().plusSeconds(3600)) >= 0) {
                                vuelosFacade.insert(vuelos);
                                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo " + vuelos.getNumeroVuelo() + " ha sido agregado exitosamente", "");
                                FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                                clean();
                            } else {
                                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La hora de fin de vuelo " + vuelos.getHoraFinVuelo() + " debe ser mayor igual a la hora de inicio de vuelo "
                                        + vuelos.getHoraInicioVuelo() + " más una hora.", "");
                                FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                            }
                        } else {
                            vuelosFacade.insert(vuelos);
                                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo " + vuelos.getNumeroVuelo() + " ha sido agregado exitosamente", "");
                                FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                                clean();
                        }

                    } else {
                        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de fecha de fin de vuelo " + vuelos.getFechaFinVuelo() + " debe ser posterior al inicio del vuelo " + vuelos.getFechaInicioVuelo() + ".", "");
                        FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                    }
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del origen " + vuelos.getOrigen().getNombre().toUpperCase() + " y destino " + vuelos.getDestino().getNombre().toUpperCase() + " deben ser distintos.", "");
                    FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo número " + vuelos.getNumeroVuelo() + " ya esta ocupado.", "");
                FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
            }

        } catch (Exception ex) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo número " + vuelos.getNumeroVuelo() + " no pudo ser agregado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
        }

        return "vuelosAlta";
    }

    public String prepareEdit(Vuelos v) {
        vuelos = v;
        return "vuelosEdit";
    }

    public String update() {
   
        FacesMessage msj; 
        try {
//            if (vuelosFacade.findByNumeroVuelo(vuelos.getNumeroVuelo()).isEmpty()) {
                if (!vuelos.getOrigen().getNombre().toUpperCase().equals(vuelos.getDestino().getNombre().toUpperCase())) {
                    if (vuelos.getFechaInicioVuelo().before(vuelos.getFechaFinVuelo()) || vuelos.getFechaInicioVuelo().compareTo(vuelos.getFechaFinVuelo()) == 0) {
                        if (vuelos.getFechaInicioVuelo().compareTo(vuelos.getFechaFinVuelo()) == 0) {
                            if (vuelos.getHoraFinVuelo().toInstant().compareTo(vuelos.getHoraInicioVuelo().toInstant().plusSeconds(3600)) >= 0) {
                                vuelosFacade.update(vuelos);
                                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo número " + vuelos.getNumeroVuelo() + " se ha modificado exitosamente", "");
                                FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                                clean();
                            } else {
                                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La hora de fin de vuelo " + vuelos.getHoraFinVuelo() + " debe ser mayor igual a la hora de inicio de vuelo "+
                                        vuelos.getHoraInicioVuelo()+" más una hora.", "");
                                FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                            }
                        } else {
                            vuelosFacade.update(vuelos);
                            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo número " + vuelos.getNumeroVuelo() + " se ha modificado exitosamente", "");
                            FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                            clean();
                        }
                    } else {
                        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de fecha de fin de vuelo " + vuelos.getFechaFinVuelo() + " debe ser posterior al inicio del vuelo " + vuelos.getFechaInicioVuelo() + ".", "");
                        FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                    }

                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del origen " + vuelos.getOrigen().getNombre().toUpperCase() + " y destino " + vuelos.getDestino().getNombre().toUpperCase() + " deben ser distintos.", "");
                    FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                }
//            } else {
//
//                 msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo " + vuelos.getNumeroVuelo() + " ya esta ocupado.", "");
//                FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
//            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo número " + vuelos.getNumeroVuelo() + " no pudo ser modificado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
        }

        return "";
    }

    public Vuelos find(Long id) {
        return vuelosFacade.find(id);
    }

    public void clean() {
        vuelos = new Vuelos();
    }

    public boolean flag(Vuelos v) {
        return v.equals(vuelos);
    }

    public void prepararEliminar(Vuelos v) {
        vuelos = v;
    }

    public String mainClean(String url) {
        vuelos = new Vuelos();
        setConfirm(false);
        return url;
    }

    public String prepareConfirm() {
        setConfirm(true);
        return "vuelosList";
    }

    public void delete() {
        FacesMessage msj;
        try {
            vuelosFacade.delete(vuelos);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo número " + vuelos.getNumeroVuelo() + " ha sido eliminado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("vuelosList", msj);
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo número " + vuelos.getNumeroVuelo() + " no pudo ser eliminado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("vuelosList", msj);
        }
        mainClean("vuelosList");
    }

    /**
     * @return the vuelos
     */
    public Vuelos getVuelos() {
        return vuelos;
    }

    /**
     * @param vuelos the vuelos to set
     */
    public void setVuelos(Vuelos vuelos) {
        this.vuelos = vuelos;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

}
