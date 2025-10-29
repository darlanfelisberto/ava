package br.com.feliva.erp.model.questionarios;

import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resposta_questionario")
public class RespostaQuestionario extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta_questionario")
    private Integer idRespostaQuestionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;

    @OneToMany(mappedBy = "respostaQuestionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<RespostaQuestao> listaRespostaQuestao;

    @Override
    public Integer getMMId() {
        return this.idRespostaQuestionario;
    }

    @JsonIgnore
    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Integer getIdRespostaQuestionario() {
        return idRespostaQuestionario;
    }

    public void setIdRespostaQuestionario(Integer idRespostaQuestionario) {
        this.idRespostaQuestionario = idRespostaQuestionario;
    }

    public List<RespostaQuestao> getListaRespostaQuestao() {
        return listaRespostaQuestao;
    }

    public void setListaRespostaQuestao(List<RespostaQuestao> listaRespostaQuestao) {
        this.listaRespostaQuestao = listaRespostaQuestao;
    }
}
