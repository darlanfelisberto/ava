package br.com.feliva.erp.model;

import br.com.feliva.enun.DBs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "wraparound_risk")
@Getter
@Setter
public class WraparoundRiskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DBs datname;

    private Integer datfrozenxid;

    /**
     * Valor fixo, é um parametro do postgres
     * select current_setting('autovacuum_freeze_max_age')::int
     */
    @Transient
    private Integer freezeMaxAge = 200000000;

    private LocalDateTime dt;

    private Integer percentual;

    public WraparoundRiskModel() {
        this.dt = LocalDateTime.now();
    }

}