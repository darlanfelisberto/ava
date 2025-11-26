package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Questao;
import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.util.HtmlSanitizer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionarioDTO {

    public UUID idQuestionario;

    public String nome;

    public String descricao;

    public List<PaginaDTO> listaPaginas;

    public QuestionarioDTO copyFrom(Questionario q){
        this.idQuestionario = q.getIdQuestionario();
        this.descricao = q.getDescricao();
        this.nome = q.getNome();
        this.listaPaginas = PaginaDTO.fromList(q.getListPaginas());
        return this;
    }

    public void copyFor(Questionario q){
        q.setIdQuestionario(this.idQuestionario);
        q.setDescricao(HtmlSanitizer.sanitizeHtml(this.descricao,true,true,true,true,true));
        q.setNome(this.nome);
    }

//    public QuestionarioDTO inicializeQuestoes(List<Questao> lista){
//        this.listaQuestao = new ArrayList<>();
//        lista.forEach( item -> {
//            this.listaQuestao.add(new QuestaoDTO().inicialize(item));
//        });
//        return this;
//    }

    public static List<QuestionarioDTO> fromList(List<Questionario> lista){
        if(lista == null) return null;

        var l = new ArrayList<QuestionarioDTO>();
        lista.forEach(item ->{
            l.add(new QuestionarioDTO().copyFrom(item));
        });

        return l;
    }
}
