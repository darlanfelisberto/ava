package br.com.feliva.erp.model.questionarios;

import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "resposta_questionario")
public class RespostaQuestionario extends Model<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_resposta_questionario")
    private UUID idRespostaQuestionario;

    @JsonIgnoreProperties({"listaQuestao"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;

    @OneToMany(mappedBy = "respostaQuestionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<RespostaQuestao> listaRespostaQuestao;

    @Override
    public UUID getMMId() {
        return this.idRespostaQuestionario;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public UUID getIdRespostaQuestionario() {
        return idRespostaQuestionario;
    }

    public void setIdRespostaQuestionario(UUID idRespostaQuestionario) {
        this.idRespostaQuestionario = idRespostaQuestionario;
    }

    public List<RespostaQuestao> getListaRespostaQuestao() {
        return listaRespostaQuestao;
    }

    public void setListaRespostaQuestao(List<RespostaQuestao> listaRespostaQuestao) {
        this.listaRespostaQuestao = listaRespostaQuestao;
    }
}
