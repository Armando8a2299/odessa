/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author iris
 */
@FacesValidator(value="palabrasValidator")
public class palabrasValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value instanceof String){
            String str = (String) value; 
            if(!str.matches("[a-zA-Z ]*")){
                throw new ValidatorException(new FacesMessage
                (FacesMessage.SEVERITY_ERROR, "El campo con el dato" +value.toString()+" no es correcto. Solo se aceptan palabras y espacios en blanco.", ""));
            }
        }else{
            throw new ValidatorException(new FacesMessage
            (FacesMessage.SEVERITY_ERROR,"El tipo de dato no es aceptable.","")); 
        }
    }
    
}
