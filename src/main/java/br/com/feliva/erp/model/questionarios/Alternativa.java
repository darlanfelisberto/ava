package br.com.feliva.erp.model.questionarios;

import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "alternativa")
public class Alternativa extends Model<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_alternativa")
    private UUID idAlternativa;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questao")
    private Questao questao;

    @Column(length = 200)
    private String descricao;

    @Override
    public UUID getMMId() {
        return this.idAlternativa;
    }

    public UUID getIdAlternativa() {
        return idAlternativa;
    }

    public void setIdAlternativa(UUID idAlternativa) {
        this.idAlternativa = idAlternativa;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}