package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.RespostaQuestao;

import java.util.ArrayList;
import java.util.List;

public class RespostaQuestaoDTO {
    public Integer idQuestao;
    public Integer idRespostaQuestao;
    public List<RespostaAlternativaDTO> listaRespostaAlternativa;

    static List<RespostaQuestaoDTO> fromList(List<RespostaQuestao> lrq){
        var retorno = new ArrayList<RespostaQuestaoDTO>();
        lrq.forEach(respostaQuestao -> {
            var dto = new RespostaQuestaoDTO();
            var listAlternativa = new ArrayList<RespostaAlternativaDTO>();
            dto.idQuestao = respostaQuestao.getQuestao().getIdQuestao();
            dto.idRespostaQuestao = respostaQuestao.getIdRespostaQuestao();
            respostaQuestao.getListaRespostaAlternativa().forEach(respostaAlternativa -> {
                listAlternativa.add(RespostaAlternativaDTO.fromAlternativa(respostaAlternativa));
            });
            dto.listaRespostaAlternativa = listAlternativa;
            retorno.add(dto);
        });
        return retorno;
    };
}
