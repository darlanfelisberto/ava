package br.com.feliva.servlet;

import java.io.IOException;
import java.util.UUID;

import br.com.feliva.dao.CategoriaDAO;
import br.com.feliva.dao.ProdutoDAO;
import br.com.feliva.dao.TipoUnidadeDAO;
import br.com.feliva.erp.model.Categoria;
import br.com.feliva.erp.model.Produto;
import br.com.feliva.erp.model.TipoUnidade;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.openid.OpenIdContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.RollbackException;
import lombok.extern.log4j.Log4j2;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.output.MigrateResult;

import javax.sql.DataSource;

@WebServlet(urlPatterns = "/api/multi")
@Log4j2
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(lookup = "java:jboss/datasources/PostgreSQLDS")
    private DataSource ds;

    @Inject
    private OpenIdContext context;

    @Inject
    SecurityContext securityContext;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        https://engineering.cloudflight.io/database-migrations-using-flyway-in-dynamic-multi-tenant-spring-boot-applications
        Flyway flyway = Flyway.configure().dataSource(this.ds)
                .locations("db/migration")/**/
                .baselineOnMigrate(true).load();

        try {
            // Executa a migração
            MigrateResult migrateResult = flyway.migrate();
            System.out.println("Migração executada com sucesso!");
            System.out.println("Scripts aplicados: " + migrateResult.migrationsExecuted);

            System.out.println("\nInformações sobre as migrações:");
            System.out.println(flyway.info().current().getVersion());
        } catch (FlywayException e) {
            System.err.println("Erro ao executar a migração: " + e.getMessage());
            // flyway.repair();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}