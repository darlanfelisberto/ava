package br.com.feliva.erp.model.questionarios;

import br.com.feliva.erp.model.questionarios.dto.RespostaAlternativaDTO;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestaoDTO;
import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "resposta_questao")
public class RespostaQuestao extends Model<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_resposta_questao")
    private UUID idRespostaQuestao;

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
    public UUID getMMId() {
        return this.idRespostaQuestao;
    }

    public UUID getIdRespostaQuestao() {
        return idRespostaQuestao;
    }

    public void setIdRespostaQuestao(UUID idRespostaQuestao) {
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