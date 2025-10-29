package br.com.feliva.bean;

import br.com.feliva.dao.Postgres93DAO;
import br.com.feliva.dao.TupleDeadDAO;
import br.com.feliva.dao.WraparoundRiskDAO;
import br.com.feliva.enun.DBs;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.CronTrigger;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.concurrent.Future;

@ApplicationScoped
public class BuscaDados93Bean {

    @Resource(name = "concurrent/scheduledExecutor")
    ManagedScheduledExecutorService executor;

    @Inject TupleDeadDAO tDao;
    @Inject WraparoundRiskDAO wDao;


    @PostConstruct
    public void init() {
        CronTrigger cronMin15 = new CronTrigger("15 * * * * ?",ZoneId.systemDefault());
        Future<?> scheduledTask = executor.schedule(this::min15, cronMin15);

        CronTrigger cronHora6 = new CronTrigger("15 0 * * * ?",ZoneId.systemDefault());
        executor.schedule(this::horas6, cronHora6);
    }

    @ActivateRequestContext
    public void min15() {
        try {
            var dao = Postgres93DAO.getInstance(DBs.sigaa);
            this.tDao.persistList(dao.getTuplesDead());
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ActivateRequestContext
    public void horas6() {
        System.out.println("Horas6.onStart() ");
        try {
            for(DBs db : DBs.values()){
                var dao = Postgres93DAO.getInstance(db);
                this.wDao.persistList(dao.getWraparoundRisk());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
