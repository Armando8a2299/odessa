/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Usuarios;
import Facade.UsuariosFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author iris
 */
@Named(value = "usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;
    private Usuarios usuarios = new Usuarios();
    private boolean confirm = false;
    private String nombreUsuario, pass;
    private Usuarios sesionUsuario;
    
    public List<Usuarios> findAll() {
        return usuariosFacade.findAll();
    }

    public List<Usuarios> findAll2() {
        return usuariosFacade.findAll2();
    }

    public List<Usuarios> findByNombre() {
        return usuariosFacade.findByUsuario("Iris");
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (usuarios.getPerfil() >= 0 && usuarios.getPerfil() <= 3) {
                usuariosFacade.insert(usuarios);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del usuario " + usuarios.getNombre() + " ha sido agregado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("usuariosAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario con el perfil " + usuarios.getPerfil() + " debe ser 0 = Ninguno, 1 = Gerente, 2 = Piloto, 3 = Director", "");
                FacesContext.getCurrentInstance().addMessage("usuariosAlta", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario " + usuarios.getNombre() + " no pudo ser agregado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("usuariosAlta", msj);
        }
        return "usuariosAlta";
    }

    public String prepareEdit(Usuarios u) {
        usuarios = u;
        return "usuariosEdit";
    }

    public String update() {
        FacesMessage msj;
        try {
            if (usuarios.getPerfil() >= 0 && usuarios.getPerfil() <= 3) {
                usuariosFacade.update(usuarios);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del usuario " + usuarios.getNombre() + " ha sido modificado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("usuariosEdit", msj);
            clean();
            }else{
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario con el perfil " + usuarios.getPerfil() + " debe ser 0 = Ninguno, 1 = Gerente, 2 = Piloto, 3 = Director", "");
            FacesContext.getCurrentInstance().addMessage("usuariosEdit", msj);
            }
            
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario " + usuarios.getNombre() + " no pudo ser modificado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("usuariosEdit", msj);
        }

        return "";
    }

    public Usuarios find(Long id) {
        return usuariosFacade.find(id);
    }

    public void clean() {
        usuarios = new Usuarios();
    }

    public boolean flag(Usuarios u) {
        return u.equals(usuarios);
    }

    public void prepararEliminar(Usuarios u) {
        usuarios = u;
    }

    public String mainClean(String url) {
        usuarios = new Usuarios();
        setConfirm(false);
        return url;
    }

    public String prepareConfirm() {
        setConfirm(true);
        return "usuariosList";
    }

    public void delete() {
        FacesMessage msj;
        try {

            usuariosFacade.delete(usuarios);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del usuario " + usuarios.getNombre() + " ha sido eliminado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("usuariosList", msj);

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario " + usuarios.getNombre() + " no pudo ser eliminado. Contacte a soporte.", "");
            FacesContext.getCurrentInstance().addMessage("usuariosList", msj);
        }
        mainClean("usuariosList");
    }
    

    
    public String inicioSesion(){
        
    Usuarios currentUser = usuariosFacade.findByEmailAndPass(getNombreUsuario(), getPass());        
    if(currentUser ==null){
        System.out.println("Usuario o contraseña incorrecta");
        return null;
    }else{
    FacesContext.getCurrentInstance().getExternalContext().
            getSessionMap().put("sesionUsuario", currentUser);
            return "index"; 
    }
    }


    
    /**
     * @return the paises
     */
    public Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param paises the paises to set
     */
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    private Object getUsuariosFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the sesionUsuario
     */
    public Usuarios getSesionUsuario() {
        return sesionUsuario;
    }

    /**
     * @param sesionUsuario the sesionUsuario to set
     */
    public void setSesionUsuario(Usuarios sesionUsuario) {
        this.sesionUsuario = sesionUsuario;
    }

}
