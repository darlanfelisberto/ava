package br.com.feliva.erp.model.questionarios;

import br.com.feliva.erp.model.ImagenProduto;
import br.com.feliva.sharedClass.db.Model;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "questionario")
public class Questionario extends Model<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_questionario")
    private UUID idQuestionario;

    @Column(columnDefinition="text")
    private String descricao;

    private String nome;

    @OneToMany(mappedBy = "questionario", fetch = FetchType.LAZY)
    List<Pagina> listPaginas;

    @Override
    public UUID getMMId() {
        return this.idQuestionario;
    }

    public UUID getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(UUID idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Pagina> getListPaginas() {
        return listPaginas;
    }

    public void setListPaginas(List<Pagina> listPaginas) {
        this.listPaginas = listPaginas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}