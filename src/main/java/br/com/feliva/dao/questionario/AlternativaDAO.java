package br.com.feliva.dao.questionario;

import br.com.feliva.erp.model.questionarios.Alternativa;
import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class AlternativaDAO extends InjectEntityManagerDAO<Alternativa> {

    public Alternativa findById(Integer id){
        try {
            return (Alternativa) this.em.createQuery("""
                select a from Alternativa a where a.idAlternativa = :id
                """)
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }



}
