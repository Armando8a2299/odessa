package Entity;

import Entity.Estados;
import Entity.Vuelos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T12:30:52")
@StaticMetamodel(Ciudades.class)
public class Ciudades_ { 

    public static volatile SingularAttribute<Ciudades, Estados> estado;
    public static volatile ListAttribute<Ciudades, Vuelos> vuelosDestinoList;
    public static volatile ListAttribute<Ciudades, Vuelos> vuelosOrigenList;
    public static volatile SingularAttribute<Ciudades, Long> id;
    public static volatile SingularAttribute<Ciudades, String> nombre;

}