package br.com.feliva.bean;

import br.com.feliva.dao.StatusDAO;
import br.com.feliva.erp.model.Status;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@RequestScoped
@Named
public class StatusBean {

    @Inject
    private StatusDAO statusDAO;

    List<Status> statuses;

    public List<Status> getListAll() {
        if(statuses == null) {
            statuses = this.statusDAO.listAll();
        }
        return  statuses;
    }
}
