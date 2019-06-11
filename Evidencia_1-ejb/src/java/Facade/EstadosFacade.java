/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Estados;
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
public class EstadosFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;

    public List<Estados> findAll() {
        TypedQuery<Estados> query;
        query = em.createQuery("SELECT e FROM Estados e", Estados.class);
        return query.getResultList();
    }

    public List<Estados> findAll2() {
        Query query;
        query = em.createNamedQuery("findEstados");
        return query.getResultList();
    }

    public List<Estados> findByCiudad(String ciudadParameter) {
        TypedQuery<Estados> query;
        query = em.createQuery("SELECT e FROM Estados e WHERE e.ciudadesList.nombre:ciudad", Estados.class);
        query.setParameter("ciudad", ciudadParameter);
        return query.getResultList();
    }

    public void insert(Estados e) {
        em.persist(e);
    }

    public void update(Estados e) {
        em.merge(e);
    }

    public Estados find(Long id) {
        return em.find(Estados.class, id);
    }

    public void delete(Estados e) {
        em.remove(em.merge(e));
    }

}
