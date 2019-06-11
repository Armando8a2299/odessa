/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Usuarios;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
public class UsuariosFacade {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;
    
       public List<Usuarios> findAll(){
        TypedQuery <Usuarios> query;
        query = em.createQuery("SELECT u FROM Usuarios u", Usuarios.class); 
        return query.getResultList();
    }
    
    public List<Usuarios> findAll2(){
        Query query; 
        query = em.createNamedQuery("findUsuarios"); 
        return query.getResultList();
    }
    
    
    public List<Usuarios> findByUsuario(String nombreParameter){
        TypedQuery <Usuarios> query;
        query = em.createQuery("SELECT u FROM Usuarios a WHERE u.nuombre=:nombre", Usuarios.class); 
        query.setParameter("nombre", nombreParameter);
        return query.getResultList();
    }
    
//    public List<Usuarios> findByUsuario(String nombreParameter){
//        Query query; 
//        query = em.createNamedQuery("findByUsuario");
//        query.setParameter("nombre",nombreParameter);
//        return query.getResultList();
//    }
    

    public void update(Usuarios u){
        em.merge(u); 
    }
    
    public Usuarios find(Long id){
        return em.find(Usuarios.class, id);
    }

    public void insert(Usuarios u) {
        em.persist(u);
    }

    public void delete(Usuarios u){
    em.remove(em.merge(u));
    }
    
    
    public Usuarios findByEmailAndPass(String email, String pass){
    TypedQuery<Usuarios> query;
    query= em.createQuery("SELECT u FROM Usuarios u WHERE u.email =:email AND u.contrasena=:pass",
            Usuarios.class);
    query.setParameter("email", email);
    query.setParameter("pass", pass);
    try{
    return query.getSingleResult();
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
    return null;
    }

}

    
    

