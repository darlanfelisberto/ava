package br.com.feliva.dao.questionario;

import br.com.feliva.erp.model.questionarios.RespostaAlternativa;
import br.com.feliva.erp.model.questionarios.RespostaQuestao;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class RespostaAlternativaDAO extends InjectEntityManagerDAO<RespostaAlternativa> {

    public RespostaAlternativa findById(Integer id){
        try {
            return (RespostaAlternativa) this.em.createQuery("""
                select a from RespostaAlternativa a where a.idRespostaAlternativa = :id
                """)
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<RespostaAlternativa> findAll(RespostaQuestao respostaQuestao){
        try {
            return this.em.createQuery("""
                select a from RespostaAlternativa a where a.respostaQuestao = :id
                """,RespostaAlternativa.class)
                    .setParameter("id",respostaQuestao)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void removeMultiplas(List<RespostaAlternativa> remove,List<RespostaAlternativa> persist){
        remove.forEach(respostaAlternativa -> {
            this.em.remove(respostaAlternativa);
        });
        persist.forEach(respostaAlternativa -> {
            this.em.persist(respostaAlternativa);
        });
    }



}
