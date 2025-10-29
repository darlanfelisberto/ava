package br.com.feliva.dao;

import br.com.feliva.erp.model.Categoria;
import br.com.feliva.erp.model.TipoUnidade;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class TipoUnidadeDAO extends InjectEntityManagerDAO<TipoUnidade> {


    public List<TipoUnidade> listAll(){
        return this.em.createQuery("from TipoUnidade tc order by tc.nome asc ").getResultList();
    }

    public List<TipoUnidade> listByName(String name){
        return this.em.createQuery("from TipoUnidade tc where tc.nome ilike :nome").setParameter("nome", "%" + name +"%").getResultList();
    }
}
