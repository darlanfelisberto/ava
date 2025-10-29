package br.com.feliva.dao;

import br.com.feliva.erp.model.Categoria;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class CategoriaDAO extends InjectEntityManagerDAO<Categoria> {

    public List<Categoria> listByName(String name){
        return this.em.createQuery("from Categoria c where c.nome ilike :nome").setParameter("nome", "%" + name +"%").getResultList();
    }
}
