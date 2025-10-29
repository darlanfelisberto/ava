package br.com.feliva.erp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tuple_dead")
@Getter
@Setter
public class TupleDead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int db;

    private LocalDate dt = LocalDate.now();

    private Integer relid;
    private String schemaname;
    private String relname;

    @Column(name = "seq_scan")
    private Integer  seqScan;
    @Column(name = "seq_tup_read")
    private Integer seqTupRead;
    @Column(name = "idx_scan")
    private Integer idxScan;

    @Column(name = "idx_tup_fetch")
    private Integer idxTupFetch;

    @Column(name = "n_tup_ins")
    private Integer nTupIns;
    @Column(name = "n_tup_upd")
    private Integer nTupUpd;
    @Column(name = "n_tup_del")
    private Integer nTupDel;
    @Column(name = "n_tup_hot_upd")
    private Integer nTupHotUpd;
    @Column(name = "n_live_tup")
    private Integer nLiveTup;
    @Column(name = "n_dead_tup")
    private Integer nDeadTup;
    @Column(name = "last_vacuum")

    private Timestamp lastVacuum;
    @Column(name = "last_autovacuum")
    private Timestamp lastAutovacuum;

    @Column(name = "last_analyze")
    private Timestamp lastAnalyze;

    @Column(name = "last_autoanalyze")
    private Timestamp lastAutoanalyze;

    @Column(name = "vacuum_count")
    private Integer vacuumCount;
    @Column(name = "autovacuum_count")
    private Integer autovacuumCount;
    @Column(name = "analyze_count")
    private Integer analyzeCount;
    @Column(name = "autoanalyze_count")
    private Integer autoanalyzeCount;

}