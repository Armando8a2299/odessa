/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Controller.UsuariosController;
import Entity.Usuarios;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author iris
 */
@FacesConverter(forClass = Usuarios.class)
public class usuariosConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        UsuariosController usuariosController =context.getApplication().evaluateExpressionGet(context, "#{usuariosController}", UsuariosController.class);
        return usuariosController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Usuarios usuarios = (Usuarios) value; 
        return usuarios.getId().toString(); 
    }
    
}

