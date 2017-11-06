package com.cherkasov;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@org.springframework.stereotype.Repository
public class Repository {

    @PersistenceContext
    private EntityManager em;

    public Data get(Integer id) {
        return em.find(Data.class, id);  //return null
    }

    public List<Son> getAllById(Integer id) {
        return  em.createQuery("SELECT son FROM Son son WHERE son.id=:id ORDER BY son.id", Son.class).setParameter("id", id).getResultList();
    }

    public Data getFull(Integer id) {
        EntityGraph entityGraph = em.getEntityGraph("category.with.competitors");
        return em.createQuery("SELECT cat FROM Category cat WHERE cat.id=:id ORDER BY cat.id", Data.class).setParameter("id", id).setHint("javax.persistence.fetchgraph", entityGraph).getSingleResult();
    }

    public List<Son> getAll() {
        return  em.createQuery("SELECT cat FROM Category cat ORDER BY cat.id", Son.class).getResultList();
    }

    @Transactional
    public Data save(Data data) {

//        event.setUser(em.getReference(Event.class, userId));
        if (data.getId() == null) {
//            log.info("Persist event {}", event.getName());
            em.persist(data);
            return data;
        } else {
//            log.info("Merge event {}", event.getName());
            return em.merge(data);
        }
    }

    @Transactional
    public boolean delete(Integer id) {

//        log.debug("removing category with id={}", id);

        Data entity = em.getReference(Data.class, id);
        if (entity != null) {
//            log.debug("category not null, {}, {}", entity.getId(), entity.getDescription());
            em.remove(entity);
//            log.debug("category remove");
            return true;
        }
        return false;
    }

    public void flush() {
        em.flush();
    }
    public EntityManager getManager() {
        return em;
    }

}
