package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Alternativa;
import br.com.feliva.erp.model.questionarios.RespostaAlternativa;
import br.com.feliva.erp.model.questionarios.RespostaQuestao;
import br.com.feliva.sharedClass.db.Model;

import java.util.ArrayList;
import java.util.List;

public class RespostaAlternativaDTO extends Model<Integer> {
    public Integer idRespostaAlternativa;
    public Integer idRespostaQuestao;
    public Integer idAlternativa;

    public static RespostaAlternativaDTO fromAlternativa(RespostaAlternativa a){
        var dto = new RespostaAlternativaDTO();
        dto.idRespostaAlternativa = a.getIdRespostaAlternativa();
        dto.idRespostaQuestao = a.getRespostaQuestao().getIdRespostaQuestao();
        dto.idAlternativa = a.getAlternativa().getIdAlternativa();
        return dto;
    }

    public RespostaAlternativa toObject(RespostaQuestao respostaQuestao) {
        var respostaAlternativa = new RespostaAlternativa();
        respostaAlternativa.setIdRespostaAlternativa(this.idRespostaAlternativa);
        respostaAlternativa.setRespostaQuestao(respostaQuestao);
        Alternativa alternativa = new Alternativa();
        alternativa.setIdAlternativa(this.idAlternativa);
        respostaAlternativa.setAlternativa(alternativa);
        return respostaAlternativa;
    }

    public static List<RespostaAlternativa> toList(List<RespostaAlternativaDTO> dtos, RespostaQuestao respostaQuestao) {
        var list = new ArrayList<RespostaAlternativa>();
        for (RespostaAlternativaDTO dto : dtos) {
            list.add(dto.toObject(respostaQuestao));
        }
        return list;
    }

    @Override
    public Integer getMMId() {
        return idRespostaAlternativa;
    }
}
