package br.com.feliva.erp.model.questionarios.dto;

import br.com.feliva.erp.model.questionarios.Pagina;
import br.com.feliva.erp.model.questionarios.Questao;
import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.sharedClass.db.Model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaginaDTO  {

    public UUID idPagina;

    public String titulo;

    public List<QuestaoDTO> listaQuestoes;

    public PaginaDTO inicialize(Pagina q){
        idPagina = q.getIdPagina();
        titulo = q.getTitulo();
        this.listaQuestoes = QuestaoDTO.fromList(q.getListQuestoes());
        return this;
    }

    public static List<PaginaDTO> fromList(List<Pagina> lista){
        var l = new ArrayList<PaginaDTO>();
        lista.forEach(item ->{
            l.add(new PaginaDTO().inicialize(item));
        });
        return l;
    }
}
