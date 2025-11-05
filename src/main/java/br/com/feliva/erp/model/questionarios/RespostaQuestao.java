package br.com.feliva.erp.model.questionarios;

import br.com.feliva.erp.model.questionarios.dto.RespostaAlternativaDTO;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestaoDTO;
import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resposta_questao")
public class RespostaQuestao extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta_questao")
    private Integer idRespostaQuestao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resposta_questionario")
    private RespostaQuestionario respostaQuestionario;

    @JsonIgnoreProperties({"listaAlternativa","tipoQuestao"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questao")
    private Questao questao;

    @OneToMany(mappedBy = "respostaQuestao", fetch = FetchType.LAZY)
    private List<RespostaAlternativa> listaRespostaAlternativa;

    @Override
    public Integer getMMId() {
        return this.idRespostaQuestao;
    }

    public Integer getIdRespostaQuestao() {
        return idRespostaQuestao;
    }

    public void setIdRespostaQuestao(Integer idRespostaQuestao) {
        this.idRespostaQuestao = idRespostaQuestao;
    }

    public RespostaQuestionario getRespostaQuestionario() {
        return respostaQuestionario;
    }

    public void setRespostaQuestionario(RespostaQuestionario respostaQuestionario) {
        this.respostaQuestionario = respostaQuestionario;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public List<RespostaAlternativa> getListaRespostaAlternativa() {
        return listaRespostaAlternativa;
    }

    public void setListaRespostaAlternativa(List<RespostaAlternativa> listaRespostaAlternativa) {
        this.listaRespostaAlternativa = listaRespostaAlternativa;
    }

    public boolean contemRespostaAlternativa(RespostaAlternativaDTO alternativa){
        for(var item : this.listaRespostaAlternativa){
            if(item.equals(alternativa)){

            }
        }
        return false;
    }

    public static RespostaQuestao fromJson(RespostaQuestaoDTO respostaQuestaoDTO){
        var respostaQuestao = new RespostaQuestao();
//        respostaQuestao.se

        return null;
    }
}