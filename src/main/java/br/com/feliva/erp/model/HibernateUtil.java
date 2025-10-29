
package br.com.feliva.erp.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@ApplicationScoped
public class HibernateUtil {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try {
            sessionFactory = new MetadataSources(registry)
                            .addAnnotatedClass(Event.class)
                            .buildMetadata()
                            .buildSessionFactory();

//            Session session = sessionFactory.withOptions().tenantIdentifier(4).openSession();
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }


    @PreDestroy
    public void close() {
        try {
            sessionFactory.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
