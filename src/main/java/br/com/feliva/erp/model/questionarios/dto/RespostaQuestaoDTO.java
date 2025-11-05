package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Questao;
import br.com.feliva.erp.model.questionarios.RespostaQuestao;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.sharedClass.db.Model;

import java.util.ArrayList;
import java.util.List;

public class RespostaQuestaoDTO {
    public Integer idQuestao;
//    public Integer idRespostaQuestao;
    public List<AlternativaDTO> listaAlternativa;

    static List<RespostaQuestaoDTO> fromList(List<RespostaQuestao> lrq){
        var retorno = new ArrayList<RespostaQuestaoDTO>();
        lrq.forEach(respostaQuestao -> {
            var dto = new RespostaQuestaoDTO();
            dto.listaAlternativa = new ArrayList<AlternativaDTO>();
            dto.idQuestao = respostaQuestao.getQuestao().getIdQuestao();
//            dto.idRespostaQuestao = respostaQuestao.getIdRespostaQuestao();
            respostaQuestao.getListaRespostaAlternativa().forEach(respostaAlternativa -> {
                dto.listaAlternativa.add(new AlternativaDTO().inicialize(respostaAlternativa.getAlternativa()));
            });
            retorno.add(dto);
        });
        return retorno;
    }

}
