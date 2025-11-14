package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Alternativa;
import br.com.feliva.erp.model.questionarios.Questao;
import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.erp.model.questionarios.TipoQuestao;
import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class QuestaoDTO {

    public UUID idQuestao;

    public String tipoQuestao;

    public String descricao;

    public List<Alternativa> listaAlternativa;

    public QuestaoDTO inicialize(Questao q){
        idQuestao = q.getIdQuestao();
        descricao = q.getDescricao();
        tipoQuestao = q.getTipoQuestao().getCod();
        listaAlternativa = q.getListaAlternativa();
        return this;
    }

    public static List<QuestaoDTO> fromList(List<Questao> lista){
        var l = new ArrayList<QuestaoDTO>();
        lista.forEach(item ->{
            l.add(new QuestaoDTO().inicialize(item));
        });
        return l;
    }
}