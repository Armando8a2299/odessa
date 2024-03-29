/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author iris
 */
@Entity
@Table(name = "Aviones")

@NamedQueries({
    @NamedQuery(name = "findAviones", query = "SELECT a FROM Aviones a"),
    @NamedQuery(name = "findByNumeroYcapacidad", query = "SELECT a FROM Aviones a WHERE a.numeroAvion=:numeroAvion AND a.capacidadPasajeros=:capacidadPasajeros")
})

public class Aviones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numeroAvion", length = 20, nullable = false)
    private String numeroAvion; 
    
    @Column(name = "capacidadPasajeros", nullable = false)
    private int capacidadPasajeros; 
    
    @Column(name = "modelo",length = 25, nullable = false)
    private String modelo; 
    
    @Column(name = "aerolinea",length = 35,  nullable = false)
    private String aerolinea; 
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "numeroAvion")
    private List <Vuelos> vuelosList; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aviones)) {
            return false;
        }
        Aviones other = (Aviones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\nAviones[ id=" + getId() + " Numero de avion="+getNumeroAvion()+" Capacidad de pasajeros="+getCapacidadPasajeros()+ " ]";
    }

    /**
     * @return the numeroAvion
     */
    public String getNumeroAvion() {
        return numeroAvion;
    }

    /**
     * @param numeroAvion the numeroAvion to set
     */
    public void setNumeroAvion(String numeroAvion) {
        this.numeroAvion = numeroAvion;
    }

    /**
     * @return the capacidadPasajeros
     */
    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    /**
     * @param capacidadPasajeros the capacidadPasajeros to set
     */
    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the aerolinea
     */
    public String getAerolinea() {
        return aerolinea;
    }

    /**
     * @param aerolinea the aerolinea to set
     */
    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }
    
    public List <Vuelos> getVuelosList(){
        return vuelosList; 
    }
    
    public void setVuelosList(List <Vuelos> vuelosList){
        this.vuelosList = vuelosList;  
    }
    
}
