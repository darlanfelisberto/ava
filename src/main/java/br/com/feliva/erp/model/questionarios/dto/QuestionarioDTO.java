package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Questao;
import br.com.feliva.erp.model.questionarios.Questionario;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

public class QuestionarioDTO {

    public Integer idQuestionario;

    public String descricao;

    public List<QuestaoDTO> listaQuestao;

    public QuestionarioDTO inicialize(Questionario q){
        this.idQuestionario = q.getIdQuestionario();
        this.descricao = q.getDescricao();
        return this;
    }

    public QuestionarioDTO inicializeQuestoes(List<Questao> lista){
        this.listaQuestao = new ArrayList<>();
        lista.forEach( item -> {
            this.listaQuestao.add(new QuestaoDTO().inicialize(item));
        });
        return this;
    }

    public static List<QuestionarioDTO> fromList(List<Questionario> lista){
        var l = new ArrayList<QuestionarioDTO>();
        lista.forEach(item ->{
            l.add(new QuestionarioDTO().inicialize(item));
        });

        return l;
    }
}
