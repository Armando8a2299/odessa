/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Ciudades;
import Entity.Estados;
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
public class CiudadesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;
    
        public List<Ciudades> findAll(){
        TypedQuery <Ciudades> query;
        query = em.createQuery("SELECT c FROM Ciudades c", Ciudades.class); 
        return query.getResultList();
    }
    
    public List<Ciudades> findAll2(){
        Query query; 
        query = em.createNamedQuery("findCiudades"); 
        return query.getResultList();
    }
    
     public List<Ciudades> findByEstado(String estadoParameter){
        TypedQuery <Ciudades> query;
        query = em.createQuery("SELECT c FROM Ciudades c WHERE c.estado.nombre=:estado", Ciudades.class);
        query.setParameter("estado", estadoParameter);
        return query.getResultList();
    }
     
    public List<Ciudades> findByPais(String paisParameter){
        Query query; 
        query = em.createNamedQuery("findByPais");
        query.setParameter("pais", paisParameter);
        return query.getResultList();
    }
    
    public void insert(Ciudades c){
        em.persist(c);
    }
    
    public void update(Ciudades c){
        em.merge(c); 
    }
    
    public Ciudades find(Long id){
        return em.find(Ciudades.class, id);
    }
    
    public void delete(Ciudades c){
    em.remove(em.merge(c));
    }
    
}
