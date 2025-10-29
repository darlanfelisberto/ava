package br.com.feliva.dao;


import br.com.feliva.enun.DBs;
import br.com.feliva.erp.model.TupleDead;
import br.com.feliva.erp.model.WraparoundRiskModel;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Postgres93DAO {

    private DataSource ds;
    private DBs db;

    public Postgres93DAO(DataSource ds,DBs db) {
        this.ds = ds;
        this.db = db;
    }

    public List<TupleDead> getTuplesDead() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = this.ds.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(""" 
                        SELECT schemaname, relname, n_live_tup,n_dead_tup, last_vacuum, last_autovacuum, last_analyze,
                            last_autoanalyze FROM pg_stat_all_tables WHERE n_dead_tup > 0 ORDER BY n_dead_tup DESC;
                    """);

            List<TupleDead> list = new ArrayList<>();
            while (resultSet.next()) {
                var tuple = new TupleDead();
                tuple.setDb(this.db.getId());
                tuple.setSchemaname(resultSet.getString("schemaname"));
                tuple.setRelname(resultSet.getString("relname"));
                tuple.setNLiveTup(resultSet.getInt("n_live_tup"));
                tuple.setNDeadTup(resultSet.getInt("n_dead_tup"));

                tuple.setLastVacuum(resultSet.getTimestamp("last_vacuum"));
                tuple.setLastAutovacuum(resultSet.getTimestamp("last_autovacuum"));
                tuple.setLastAnalyze(resultSet.getTimestamp("last_analyze"));
                tuple.setLastAutoanalyze(resultSet.getTimestamp("last_autoanalyze"));
                list.add(tuple);
            }
            return list;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            resultSet.close();
            statement.close();
            conn.close();
        }
    }

    public List<WraparoundRiskModel> getWraparoundRisk() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = this.ds.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(""" 
                    SELECT datname, age(datfrozenxid) AS idade_xid,
                    age(datfrozenxid)::numeric / current_setting('autovacuum_freeze_max_age')::numeric * 100 AS perc_usado
                    FROM pg_database where datname = current_database()
                    """);

            List<WraparoundRiskModel> list = new ArrayList<>();
            while (resultSet.next()) {
                var tuple = new WraparoundRiskModel();
                tuple.setDatname(this.db);
                tuple.setDatfrozenxid(resultSet.getInt("idade_xid"));
                tuple.setPercentual(resultSet.getInt("perc_usado"));
                list.add(tuple);
            }
            return list;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            resultSet.close();
            statement.close();
            conn.close();
        }
    }

    public static Postgres93DAO getInstance(DBs db) throws NamingException {
        Context initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup(db.getJni());
        return new Postgres93DAO(dataSource,db);
    }
}

