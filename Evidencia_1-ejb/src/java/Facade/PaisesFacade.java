/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Paises;
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
public class PaisesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;
    
        public List<Paises> findAll(){
        TypedQuery <Paises> query;
        query = em.createQuery("SELECT p FROM Paises p", Paises.class); 
        return query.getResultList();
    }
    
    public List<Paises> findAll2(){
        Query query; 
        query = em.createNamedQuery("findPaises"); 
        return query.getResultList();
    }
    
        public List<Paises> findByName(String nombreParameter){
        Query query; 
        query = em.createNamedQuery("findByName"); 
        query.setParameter("nombre", nombreParameter);
        return query.getResultList();
    }
        
    public void insert(Paises p){
        em.persist(p);
    }
    
    public void update(Paises p){
        em.merge(p);
    }
    
    public Paises find(Long id){
        return em.find(Paises.class, id);
    }
    
    public void delete(Paises p){
    em.remove(em.merge(p));
    }
}
