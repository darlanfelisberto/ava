package br.com.feliva.dao.questionario;

import br.com.feliva.erp.model.Categoria;
import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;

import java.util.List;

@RequestScoped
public class QuestionarioDAO extends InjectEntityManagerDAO<Questionario> {

    public Questionario findById(Integer id){
        try {
            return (Questionario) this.em.createQuery("select q from Questionario q where q.idQuestionario = :id")
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public RespostaQuestionario findRespostaQuetionario(Integer id){
        try {
            return (RespostaQuestionario) this.em.createQuery("select q from RespostaQuestionario q where q.questionario.idQuestionario = :id")
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Questionario> listAllQuestionarios(){
        try {
            return  this.em.createQuery("select q from Questionario q ").getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
