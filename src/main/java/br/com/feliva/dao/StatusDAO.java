package br.com.feliva.dao;

import br.com.feliva.erp.model.Status;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class StatusDAO extends InjectEntityManagerDAO<Status> {


    public List<Status> listAll(){
        return em.createQuery("select s from Status s order by s.descricao asc ").getResultList();
    }
}
