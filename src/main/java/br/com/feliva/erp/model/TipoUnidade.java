package br.com.feliva.erp.model;

import br.com.feliva.sharedClass.db.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tipo_unidade")
@Getter
@Setter
public class TipoUnidade extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidade")
    public Integer idUnidade;

    @NotEmpty(message = "Informe um nome.")
    public String nome;

    @NotEmpty(message = "Informe uma descrição.")
    public String descricao;

    @NotEmpty(message = "Informe uma sigla.")
    @Size(min = 3,message = "Sigla deve ter no mínimo 3 caracteres.")
    public String sigla;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_status")
    @NotNull(message = "Informe um status.")
    public Status status;

    public TipoUnidade() {}

    @Override
    public Integer getMMId() {
        return this.idUnidade;
    }
}
