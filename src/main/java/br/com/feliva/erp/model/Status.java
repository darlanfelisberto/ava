package br.com.feliva.erp.model;
import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.faces.model.SelectItem;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "status")
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Cacheable
public class Status  extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer idStatus;

    private String descricao;

//    @JsonCreator
//    public static Status fromJson(@JsonProperty("nome") String name) {
//        return valueOf(name);
//    }


    @Override
    public Integer getMMId() {
        return this.idStatus;
    }
}
