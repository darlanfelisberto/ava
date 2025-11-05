package br.com.feliva.dao.questionario;

import br.com.feliva.erp.model.questionarios.Questao;
import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class QuestaoDAO extends InjectEntityManagerDAO<Questao> {

    public Questao findById(Integer id){
        try {
            return (Questao) this.em.createQuery("select q from Questao q where q.idQuestionario = :id")
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
