/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Vuelos;
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
public class VuelosFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;
    
    public List<Vuelos> findAll(){
        TypedQuery <Vuelos> query;
        query = em.createQuery("SELECT v FROM Vuelos v", Vuelos.class); 
        return query.getResultList();
    }
    
    public List<Vuelos> findAll2(){
        Query query; 
        query = em.createNamedQuery("findVuelos"); 
        return query.getResultList();
    }
    
    public List<Vuelos> findByNumeroVuelo(String numeroVueloParameter){
        TypedQuery <Vuelos> query;
        query = em.createQuery("SELECT v FROM Vuelos v WHERE v.numeroVuelo =:numeroVuelo", Vuelos.class);
        query.setParameter("numeroVuelo", numeroVueloParameter);
        return query.getResultList();
    }
    
    public List<Vuelos> findByNumeroDeVuelo(String numeroVuelo){
        TypedQuery <Vuelos> query; 
        query = em.createQuery("SELECT v FROM Vuelos v WHERE v.numeroVuelo=:numeroVuelo", Vuelos.class); 
        query.setParameter("numeroVuelo", numeroVuelo); 
        return query.getResultList(); 
    }
    
    public List<Vuelos> findByOrigen(String origenParameter){
        TypedQuery <Vuelos> query;
        query = em.createQuery("SELECT v FROM Vuelos v WHERE v.origen.nombre=:origen", Vuelos.class);
        query.setParameter("origen", origenParameter.toUpperCase());
        return query.getResultList();
    }
    
    
    public void insert(Vuelos v){
        em.persist(v);
    }
    
    public void update(Vuelos v){
        em.merge(v); 
    }
    
public Vuelos find(Long id){
        return em.find(Vuelos.class, id);
    }

public void delete(Vuelos v){
    em.remove(em.merge(v));
    }
}
