package br.com.feliva.dao;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.*;
import lombok.extern.log4j.Log4j2;


@RequestScoped
@Log4j2
public class Producer {

    @PersistenceUnit(unitName = "post16Unit")
    private EntityManagerFactory emf;

//    @Resource(name = "jdbc/postgres16") // Replace with your JNDI name
//    private DataSource dataSource;

    private EntityManager entityManager;

    @PostConstruct
    public void init() {
//        final CurrentTenantIdentifierResolver<?> tenantResolver = this.emf.unwrap(SessionFactoryImplementor.class).getCurrentTenantIdentifierResolver();
//        ((TenantIdentifierResolver)tenantResolver).setCurrentTenant("public");

       this.entityManager = this.emf.createEntityManager();
    }


    @Produces
    @Default
    @RequestScoped
    public EntityManager produceEntityManager() {
        log.info("produceEntityManager: " + this.entityManager);
        return this.entityManager;
    }

    /**
     * Disposer method for the EntityManager.
     * This method will be called by CDI when the request scope ends to close the EntityManager.
     */
    public void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
