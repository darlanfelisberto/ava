package br.com.feliva.dao;

import br.com.feliva.erp.model.TupleDead;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class TupleDeadDAO {

    @PersistenceContext(unitName = "post16Unit")
    private EntityManager em;

    @Transactional
    public void persistList(List<TupleDead> list) {
        list.forEach(item -> {
            em.persist(item);
        });
    }
}
