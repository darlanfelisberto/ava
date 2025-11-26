package br.com.feliva.erp.model.questionarios;

import br.com.feliva.sharedClass.db.Model;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pagina")
public class Pagina extends Model<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pagina")
    private UUID idPagina;

    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;

    @OneToMany(mappedBy = "pagina", fetch = FetchType.LAZY)
    List<Questao> listQuestoes;

    public UUID getMMId() {
        return this.idPagina;
    }

    public UUID getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(UUID idPagina) {
        this.idPagina = idPagina;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public List<Questao> getListQuestoes() {
        return listQuestoes;
    }

    public void setListQuestoes(List<Questao> listQuestoes) {
        this.listQuestoes = listQuestoes;
    }
}
