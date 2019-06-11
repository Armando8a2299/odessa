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
@FacesValidator(value= "correoValidator")
public class correoValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value instanceof String){
            String str = (String) value; 
            if(!str.matches("^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")){
                throw new ValidatorException(new FacesMessage
                (FacesMessage.SEVERITY_ERROR, "El campo con el dato "+value.toString()+" no es correcto. Solo se aceptan correos en el formato algo@algo.algo", ""));
            }
        }else{
            throw new ValidatorException(new FacesMessage
            (FacesMessage.SEVERITY_ERROR,"El tipo de dato no es aceptable.","")); 
        }
    }
    
}
