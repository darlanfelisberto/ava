package br.com.feliva.erp.model.questionarios;

import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tipo_questao")
public class TipoQuestao extends Model<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipo_questao")
    private UUID idTipoQuestao;

    @Column(length = 200)
    private String nome;

    @Column(length = 4)
    private String cod;

    @Override
    public UUID getMMId() {
        return this.idTipoQuestao;
    }

    public UUID getIdTipoQuestao() {
        return idTipoQuestao;
    }

    public void setIdTipoQuestao(UUID idTipoQuestao) {
        this.idTipoQuestao = idTipoQuestao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}