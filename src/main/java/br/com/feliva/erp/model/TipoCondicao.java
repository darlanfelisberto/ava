package br.com.feliva.erp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCondicao {
    nov("Novo"),
    usa("Usado"),
    rec("Recondicionado");

    private String descricao;

    TipoCondicao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return this.name();
    }

    @JsonCreator
    public static TipoCondicao fromJson(@JsonProperty("nome") String name) {
        return valueOf(name);
    }
}
