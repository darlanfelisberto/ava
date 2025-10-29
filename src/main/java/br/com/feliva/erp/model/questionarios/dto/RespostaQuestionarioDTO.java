package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.RespostaQuestao;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;

import java.util.List;

public class RespostaQuestionarioDTO {
    public Integer idQuestionario;
    public Integer idRespostaQuestionario;
    public List<RespostaQuestaoDTO> listaRespostaQuestao;

    public RespostaQuestionarioDTO inicialize(RespostaQuestionario rq){
        this.idQuestionario = rq.getQuestionario().getIdQuestionario();
        this.idRespostaQuestionario = rq.getIdRespostaQuestionario();
        this.listaRespostaQuestao = RespostaQuestaoDTO.fromList(rq.getListaRespostaQuestao());

        return this;
    }
}
