package Entity;

import Entity.Vuelos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T12:30:52")
@StaticMetamodel(Aviones.class)
public class Aviones_ { 

    public static volatile ListAttribute<Aviones, Vuelos> vuelosList;
    public static volatile SingularAttribute<Aviones, String> aerolinea;
    public static volatile SingularAttribute<Aviones, Long> id;
    public static volatile SingularAttribute<Aviones, String> numeroAvion;
    public static volatile SingularAttribute<Aviones, Integer> capacidadPasajeros;
    public static volatile SingularAttribute<Aviones, String> modelo;

}