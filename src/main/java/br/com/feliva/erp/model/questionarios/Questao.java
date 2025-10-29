package br.com.feliva.erp.model.questionarios;

import br.com.feliva.erp.model.Produto;
import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "questao")
public class Questao extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_questao")
    private Integer idQuestao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_questao")
    private TipoQuestao tipoQuestao;

    @Column(length = 200)
    private String descricao;

    @OneToMany(mappedBy = "questao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Alternativa> listaAlternativa;

    @Override
    public Integer getMMId() {
        return this.idQuestao;
    }

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    @JsonIgnore
    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Alternativa> getListaAlternativa() {
        return listaAlternativa;
    }

    public void setListaAlternativa(List<Alternativa> listaAlternativa) {
        this.listaAlternativa = listaAlternativa;
    }

    public TipoQuestao getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(TipoQuestao tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }
}