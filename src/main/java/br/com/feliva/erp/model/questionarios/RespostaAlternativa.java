package br.com.feliva.erp.model.questionarios;

import br.com.feliva.sharedClass.db.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "resposta_alternativa")
public class RespostaAlternativa extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta_alternativa")
    private Integer idRespostaAlternativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alternativa")
    private Alternativa alternativa;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resposta_questao")
    private RespostaQuestao respostaQuestao;

    @Override
    public Integer getMMId() {
        return this.idRespostaAlternativa;
    }

    public Integer getIdRespostaAlternativa() {
        return idRespostaAlternativa;
    }

    public void setIdRespostaAlternativa(Integer idRespostaAlternativa) {
        this.idRespostaAlternativa = idRespostaAlternativa;
    }

    public Alternativa getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(Alternativa alternativa) {
        this.alternativa = alternativa;
    }

    public RespostaQuestao getRespostaQuestao() {
        return respostaQuestao;
    }

    public void setRespostaQuestao(RespostaQuestao respostaQuestao) {
        this.respostaQuestao = respostaQuestao;
    }
}