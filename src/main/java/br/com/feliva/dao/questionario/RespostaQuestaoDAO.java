package br.com.feliva.dao.questionario;

import br.com.feliva.erp.model.questionarios.RespostaQuestao;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestionarioDTO;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;

import java.util.UUID;

@RequestScoped
public class RespostaQuestaoDAO extends InjectEntityManagerDAO<RespostaQuestao> {

    public RespostaQuestao findById(Integer id){
        try {
            return (RespostaQuestao) this.em.createQuery("select q from RespostaQuestao q where q.idRespostaQuestao = :id")
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public RespostaQuestao findRespostaQuestaoAndRespQuestionario(UUID idRespQuesti, UUID idQestao){
        try {
            return (RespostaQuestao) this.em.createQuery("""
                select rq from RespostaQuestao rq
                left join rq.respostaQuestionario q
                left join rq.questao qt
                left join fetch rq.listaRespostaAlternativa lra
                where q.idRespostaQuestionario = :idRespQuesti
                and qt.idQuestao = :idQuestao
            """).setParameter("idRespQuesti",idRespQuesti)
                 .setParameter("idQuestao",idQestao)
                 .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

//    public RespostaQuestao findById(RespostaQuestionarioDTO respostaQuestionario){
//        try {
//            return (RespostaQuestionario) this.em.createQuery("""
//                    select q from RespostaQuestionario q
//                    where q.idRespostaQuestionario = :idResposta
//                    and q.questionario.idQuestionario = :idQuestionario
//                    """)
//                    .setParameter("idResposta",respostaQuestionario.idRespostaQuestionario)
//                    .setParameter("idQuestionario",respostaQuestionario.idQuestionario)
//                    .getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }
}
