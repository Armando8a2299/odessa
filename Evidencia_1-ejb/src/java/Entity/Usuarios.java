
package Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Usuarios")
@NamedQueries({
    @NamedQuery(name = "findUsuarios", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.nombre=:nombre")
})
@XmlRootElement
public class Usuarios implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre; 
    
    @Column(name = "email", length = 60, nullable = false)
    private String email; 
    
    @Column(name = "contrasena", length = 30, nullable = false)
    private String contrasena; 
    
    @Column(name = "perfil", nullable = false)
    private int perfil; 
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuarios[ id=" + getId() +" Nombre="+getNombre()+ " Email= "+getEmail()+" Contraseña= "+getContrasena()+" Perfil= "+getPerfil()+ " ]";
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
        public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
    
}
