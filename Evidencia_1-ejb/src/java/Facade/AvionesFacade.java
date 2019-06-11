/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Aviones;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author iris
 */
@Stateless
@LocalBean
public class AvionesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;
    
        public List<Aviones> findAll(){
        TypedQuery <Aviones> query;
        query = em.createQuery("SELECT a FROM Aviones a", Aviones.class); 
        return query.getResultList();
    }
    
    public List<Aviones> findAll2(){
        Query query; 
        query = em.createNamedQuery("findAviones"); 
        return query.getResultList();
    }
    
    
    public List<Aviones> findByNumeroAvion(String numeroAvionParameter){
        TypedQuery <Aviones> query;
        query = em.createQuery("SELECT a FROM Aviones a WHERE a.numeroAvion=:numeroAvion", Aviones.class); 
        query.setParameter("numeroAvion", numeroAvionParameter);
        return query.getResultList();
    }
    
    public List<Aviones> findByNumeroYcapacidad(String numeroAvionParameter, int capacidadPasajerosParameter){
        Query query; 
        query = em.createNamedQuery("findByNumeroYcapacidad");
        query.setParameter("numeroAvion",numeroAvionParameter);
        query.setParameter("capacidadPasajeros", capacidadPasajerosParameter); 
        return query.getResultList();
    }
    

    public void update(Aviones a){
        em.merge(a); 
    }
    
    public Aviones find(Long id){
        return em.find(Aviones.class, id);
    }

    public void insert(Aviones a) {
        em.persist(a);
    }

    public void delete(Aviones a){
    em.remove(em.merge(a));
    }

}
