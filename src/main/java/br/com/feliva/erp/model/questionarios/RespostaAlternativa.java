package br.com.feliva.erp.model.questionarios;

import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "resposta_alternativa")
public class RespostaAlternativa extends Model<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_resposta_alternativa")
    private UUID idRespostaAlternativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alternativa",nullable = false)
    private Alternativa alternativa;
//
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resposta_questao",nullable = false)
    private RespostaQuestao respostaQuestao;

    public RespostaAlternativa(Alternativa alternativa, RespostaQuestao respostaQuestao) {
        this.alternativa = alternativa;
        this.respostaQuestao = respostaQuestao;
    }

    public RespostaAlternativa() {

    }

    @Override
    public UUID getMMId() {
        return this.idRespostaAlternativa;
    }

    public UUID getIdRespostaAlternativa() {
        return idRespostaAlternativa;
    }

    public void setIdRespostaAlternativa(UUID idRespostaAlternativa) {
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