package br.com.feliva.erp.model;


import br.com.feliva.sharedClass.db.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "marca")
public class Marca extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_marca")
    private Integer idMarca;

    @Column(length = 100)
    private String nome;

    @Override
    public Integer getMMId() {
        return this.idMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
