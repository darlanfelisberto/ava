package br.com.feliva.erp.model;

import br.com.feliva.sharedClass.db.Model;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "tags")
public class Tags extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tag")
    private Integer idTag;

    @Column(length = 100)
    private String nome;

    @Override
    public Integer getMMId() {
        return this.idTag;
    }
}
