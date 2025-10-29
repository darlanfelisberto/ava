package br.com.feliva.erp.model.questionarios;

import br.com.feliva.erp.model.ImagenProduto;
import br.com.feliva.sharedClass.db.Model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "questionario")
public class Questionario extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_questionario")
    private Integer idQuestionario;

    @Column(length = 200)
    private String descricao;

    @OneToMany(mappedBy = "questionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Questao> listaQuestao;

    @Override
    public Integer getMMId() {
        return this.idQuestionario;
    }

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Questao> getListaQuestao() {
        return listaQuestao;
    }

}