package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Alternativa;
import br.com.feliva.erp.model.questionarios.RespostaAlternativa;
import br.com.feliva.erp.model.questionarios.RespostaQuestao;

import java.util.ArrayList;
import java.util.List;

public class RespostaAlternativaDTO {
    public Integer idRespostaAlternativa;
    public Integer idRespostaQuestao;
    public Integer IdAlternativa;



    public static RespostaAlternativaDTO fromAlternativa(RespostaAlternativa a){
        var dto = new RespostaAlternativaDTO();
        dto.idRespostaAlternativa = a.getIdRespostaAlternativa();
        dto.idRespostaQuestao = a.getRespostaQuestao().getIdRespostaQuestao();
        dto.IdAlternativa = a.getAlternativa().getIdAlternativa();
        return dto;
    }
}
