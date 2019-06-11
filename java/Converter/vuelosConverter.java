/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Controller.VuelosController;
import Entity.Vuelos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author iris
 */
@FacesConverter(forClass = Vuelos.class)
public class vuelosConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        VuelosController vuelosController =context.getApplication().evaluateExpressionGet(context, "#{vuelosController}", VuelosController.class);
        return vuelosController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         Vuelos vuelos = (Vuelos) value; 
        return vuelos.getId().toString(); 
    }
    
}
