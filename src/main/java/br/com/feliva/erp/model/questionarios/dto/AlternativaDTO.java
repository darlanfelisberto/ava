package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Alternativa;
import br.com.feliva.erp.model.questionarios.Questao;
import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AlternativaDTO extends Model<UUID>{

    public UUID idAlternativa;

    public String descricao;

    public AlternativaDTO inicialize(Alternativa item){
        idAlternativa = item.getIdAlternativa();
        descricao = item.getDescricao();
        return this;
    }

    public static List<AlternativaDTO> fromList(List<Alternativa> list){
        var l = new ArrayList<AlternativaDTO>();
        list.forEach( item -> {
            l.add(new AlternativaDTO().inicialize(item));
        });

        return l;
    }


    @Override
    public UUID getMMId() {
        return this.idAlternativa;
    }
}