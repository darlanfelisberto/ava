package br.com.feliva.dao.questionario;

import br.com.feliva.erp.model.questionarios.Questao;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestionarioDTO;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;

@RequestScoped
public class RespostaQuestionarioDAO extends InjectEntityManagerDAO<RespostaQuestionario> {

    public RespostaQuestionario findById(Integer id){
        try {
            return (RespostaQuestionario) this.em.createQuery("select q from RespostaQuestionario q where q.idRespostaQuestionario = :id")
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public RespostaQuestionario findRespostaQuestionarioAndQuestionario(RespostaQuestionarioDTO respostaQuestionario){
        try {
            return (RespostaQuestionario) this.em.createQuery("""
                    select q from RespostaQuestionario q 
                    where q.idRespostaQuestionario = :idResposta
                    and q.questionario.idQuestionario = :idQuestionario
                    """)
                    .setParameter("idResposta",respostaQuestionario.idRespostaQuestionario)
                    .setParameter("idQuestionario",respostaQuestionario.idQuestionario)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
