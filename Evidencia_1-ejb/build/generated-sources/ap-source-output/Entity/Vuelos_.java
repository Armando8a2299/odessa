package Entity;

import Entity.Aviones;
import Entity.Ciudades;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T12:30:52")
@StaticMetamodel(Vuelos.class)
public class Vuelos_ { 

    public static volatile SingularAttribute<Vuelos, Integer> numeroPasajeros;
    public static volatile SingularAttribute<Vuelos, Date> horaInicioVuelo;
    public static volatile SingularAttribute<Vuelos, Date> fechaInicioVuelo;
    public static volatile SingularAttribute<Vuelos, Date> fechaFinVuelo;
    public static volatile SingularAttribute<Vuelos, Long> id;
    public static volatile SingularAttribute<Vuelos, Ciudades> origen;
    public static volatile SingularAttribute<Vuelos, Ciudades> destino;
    public static volatile SingularAttribute<Vuelos, Date> horaFinVuelo;
    public static volatile SingularAttribute<Vuelos, Aviones> numeroAvion;
    public static volatile SingularAttribute<Vuelos, String> numeroVuelo;

}