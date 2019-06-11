package Entity;

import Entity.Ciudades;
import Entity.Paises;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T12:30:52")
@StaticMetamodel(Estados.class)
public class Estados_ { 

    public static volatile SingularAttribute<Estados, Long> id;
    public static volatile ListAttribute<Estados, Ciudades> ciudadesList;
    public static volatile SingularAttribute<Estados, String> nombre;
    public static volatile SingularAttribute<Estados, Paises> pais;

}