package br.com.feliva.dao;

import br.com.feliva.enun.DBs;
import br.com.feliva.erp.model.WraparoundRiskModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequestScoped
public class WraparoundRiskDAO {

    @PersistenceContext(unitName = "post16Unit")
    private EntityManager em;

    @Transactional
    public void persistList(List<WraparoundRiskModel> list) {
        list.forEach(item -> {
            em.persist(item);
        });
    }

    public List<WraparoundRiskModel> findData(LocalDateTime inicio, DBs db) {
        String hql = """
                from WraparoundRiskModel wr where wr.dt between :inicio and :fin and wr.datname = :db
                order by wr.datname, wr.dt asc
                """;
        Query query = em.createQuery(hql);
        query.setParameter("inicio", inicio);
        query.setParameter("fin", LocalDateTime.now());
        query.setParameter("db", db);
        return query.getResultList();
    }
}
