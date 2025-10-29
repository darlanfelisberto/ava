package br.com.feliva.servlet;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.openid.OpenIdContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.output.MigrateResult;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(urlPatterns = "/teste/realms/auth/.well-known/openid-configuration")
@Log4j2
public class TesteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                    System.out.println("/teste/realms/auth/.well-known/openid-configuration");
////        https://engineering.cloudflight.io/database-migrations-using-flyway-in-dynamic-multi-tenant-spring-boot-applications
//        Flyway flyway = Flyway.configure().dataSource(this.ds)
//                .locations("db/migration")/**/
//                .baselineOnMigrate(true).load();
//
//        try {
//            // Executa a migração
//            MigrateResult migrateResult = flyway.migrate();
//            System.out.println("Migração executada com sucesso!");
//            System.out.println("Scripts aplicados: " + migrateResult.migrationsExecuted);
//
//            System.out.println("\nInformações sobre as migrações:");
//            System.out.println(flyway.info().current().getVersion());
//        } catch (FlywayException e) {
//            System.err.println("Erro ao executar a migração: " + e.getMessage());
//            // flyway.repair();
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}