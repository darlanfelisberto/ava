package br.com.feliva.dao.questionario;

import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@RequestScoped
public class QuestionarioDAO extends InjectEntityManagerDAO<Questionario> {

    public Questionario findById(UUID id){
        try {
            return (Questionario) this.em.createQuery("select q from Questionario q where q.idQuestionario = :id")
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public RespostaQuestionario findRespostaQuetionario(UUID id){
        try {
            this.em.createQuery("""
                select rqs from RespostaQuestionario rq 
                    left join rq.questionario q 
                    left join  rq.listaRespostaQuestao rqs
                    left join fetch rqs.listaRespostaAlternativa lra
                    left join fetch lra.alternativa
                    where q.idQuestionario = :id
            """).setParameter("id",id)
                    .getResultList();
            return (RespostaQuestionario) this.em.createQuery("""
                    select rq from RespostaQuestionario rq 
                    left join fetch rq.questionario q 
                    left join fetch rq.listaRespostaQuestao rqs
                    left join fetch rqs.questao qr  
                    where q.idQuestionario = :id
                    """).setParameter("id",id)
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

    public void saveRespostaQuestionario(RespostaQuestionario respostaQuestionario) {
        this.em.persist(respostaQuestionario);
    }

}
