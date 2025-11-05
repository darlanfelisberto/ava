package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.sharedClass.db.Model;

import java.util.ArrayList;
import java.util.List;

public class RespostaQuestionarioDTO extends Model<Integer> {
    public Integer idQuestionario;
    public Integer idRespostaQuestionario;
    public List<RespostaQuestaoDTO> listaRespostaQuestao;

    public RespostaQuestionarioDTO inicialize(RespostaQuestionario rq){
        this.idQuestionario = rq.getQuestionario().getIdQuestionario();
        this.idRespostaQuestionario = rq.getIdRespostaQuestionario();
        this.listaRespostaQuestao = RespostaQuestaoDTO.fromList(rq.getListaRespostaQuestao());

        return this;
    }

    public static List<RespostaQuestionarioDTO> fromList(List<RespostaQuestionario> lista){
        var l = new ArrayList<RespostaQuestionarioDTO>();
        lista.forEach(item ->{
            l.add(new RespostaQuestionarioDTO().inicialize(item));
        });

        return l;
    }

//    public RespostaQuestionario toObject() {
//        var respostaQuestionario = new RespostaQuestionario();
//        respostaQuestionario.setIdRespostaQuestionario(this.idRespostaQuestionario);
//        Questionario questionario = new Questionario();
//        questionario.setIdQuestionario(this.idQuestionario);
//        respostaQuestionario.setQuestionario(questionario);
//        respostaQuestionario.setListaRespostaQuestao(RespostaQuestaoDTO.toList(this.listaRespostaQuestao, respostaQuestionario));
//        return respostaQuestionario;
//    }

    @Override
    public Integer getMMId() {
        return idRespostaQuestionario;
    }
}
