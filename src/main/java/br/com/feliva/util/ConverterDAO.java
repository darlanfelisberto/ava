package br.com.feliva.util;

import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import br.com.feliva.sharedClass.db.Model;
import jakarta.enterprise.context.RequestScoped;

import java.util.UUID;

@RequestScoped
public class ConverterDAO extends InjectEntityManagerDAO<Model<?>> {

    public Model<?> findBd(Class<?> entity, Object id){
        return (Model<?>)  em.find(entity, id );
    }

    public Model<?> findBd(Class <?>entity, Class<?> gerics, String id){
        if(gerics.equals(UUID.class)) {
            return this.findBd(entity, UUID.fromString(id));
        }else {
            return this.findBd(entity, Integer.parseInt(id));
        }
    }
}
