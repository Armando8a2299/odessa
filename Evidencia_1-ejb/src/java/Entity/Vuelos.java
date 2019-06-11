/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author iris
 */
@Entity
@Table(name = "Vuelos")
@NamedQueries({
    @NamedQuery(name = "findVuelos", query = "SELECT v FROM Vuelos v")
})


public class Vuelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numeroVuelo", length = 20, nullable = false)
    private String numeroVuelo; 
    
//    @Column(name = "Numero_Avion", length = 20, nullable = false)
//    private String numeroAvion; 
    @JoinColumn(name = "numeroAvion", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Aviones numeroAvion; 
    
////    @Column(name = "Origen", length = 35, nullable = false)
////    private String origen; 
    @JoinColumn(name = "origen", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Ciudades origen; 
//    
////    @Column(name = "Destino", length = 35, nullable = false)
////    private String destino; 
    @JoinColumn(name = "destino", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Ciudades destino; 
    
    @Column(name = "numeroPasajeros")
    private int numeroPasajeros; 
    
    @Column(name = "fechaInicioVuelo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicioVuelo; 
    
    @Column(name = "fechaFinVuelo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinVuelo; 
    
    @Column(name = "horaInicioVuelo")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaInicioVuelo; 
    
    @Column(name = "horaFinVuelo")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaFinVuelo; 
    
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
        if (!(object instanceof Vuelos)) {
            return false;
        }
        Vuelos other = (Vuelos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\nVuelos[ id=" + getId() + " Numero de vuelo="+ getNumeroVuelo()+" Numero Avion="+getNumeroAvion()+" Origen="+getOrigen()+"Destino="+getDestino()+" ]";
    }

    /**
     * @return the numeroVuelo
     */
    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    /**
     * @param numeroVuelo the numeroVuelo to set
     */
    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    /**
     * @return the numeroAvion
     */
    public Aviones getNumeroAvion() {
        return numeroAvion;
    }

    /**
     * @param numeroAvion the numeroAvion to set
     */
    public void setNumeroAvion(Aviones numeroAvion) {
        this.numeroAvion = numeroAvion;
    }

    /**
     * @return the origen
     */
    public Ciudades getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Ciudades origen) {
        this.origen = origen;
    }

    /**
     * @return the Destino
     */
    public Ciudades getDestino() {
        return destino;
    }

    /**
     * @param destino the Destino to set
     */
    public void setDestino(Ciudades destino) {
        this.destino = destino;
    }

    /**
     * @return the NumeroPasajeros
     */
    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    /**
     * @param numeroPasajeros the NumeroPasajeros to set
     */
    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    /**
     * @return the FechaInicioVuelo
     */
    public Date getFechaInicioVuelo() {
        return fechaInicioVuelo;
    }

    /**
     * @param FechaInicioVuelo the FechaInicioVuelo to set
     */
    public void setFechaInicioVuelo(Date FechaInicioVuelo) {
        this.fechaInicioVuelo = FechaInicioVuelo;
    }

    /**
     * @return the FechaFinVuelo
     */
    public Date getFechaFinVuelo() {
        return fechaFinVuelo;
    }

    /**
     * @param fechaFinVuelo the FechaFinVuelo to set
     */
    public void setFechaFinVuelo(Date fechaFinVuelo) {
        this.fechaFinVuelo = fechaFinVuelo;
    }

    /**
     * @return the HoraInicioVuelo
     */
    public Date getHoraInicioVuelo() {
        return horaInicioVuelo;
    }

    /**
     * @param horaInicioVuelo the HoraInicioVuelo to set
     */
    public void setHoraInicioVuelo(Date horaInicioVuelo) {
        this.horaInicioVuelo = horaInicioVuelo;
    }

    /**
     * @return the HoraFinVuelo
     */
    public Date getHoraFinVuelo() {
        return horaFinVuelo;
    }

    /**
     * @param horaFinVuelo the HoraFinVuelo to set
     */
    public void setHoraFinVuelo(Date horaFinVuelo) {
        this.horaFinVuelo = horaFinVuelo;
    }
    
}
