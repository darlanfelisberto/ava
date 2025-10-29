package br.com.feliva.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import java.io.Serializable;
import java.util.Map;

@ApplicationScoped
public class AplicationInicializer implements Serializable {

    @PersistenceUnit(unitName = "post16Unit")
    private EntityManagerFactory emf;

    /**
     * Metodo responsavel por pegar todas as entitys e registrar no ModelConverter
     *
     * @param pointless
     */
    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
        System.out.println("Producer.onStart() ");
//        Map<String, String> env = System.getenv();
//        for (Map.Entry<String, String> entry : env.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
//        Flyway flyway = Flyway.configure().dataSource(this.dataSource)
////                .locations("db/migration")/**/
//                .baselineOnMigrate(true).load();
//
//        try {
//            // Executa a migração
//            MigrateResult migrateResult = flyway.migrate();
//            System.out.println("Migração executada com sucesso!");
//            System.out.println("Scripts aplicados: " + migrateResult.migrationsExecuted);
//
//            // Outras operações úteis
//            System.out.println("\nInformações sobre as migrações:");
//            System.out.println(flyway.info().current().getVersion());
//
//        } catch (FlywayException e) {
//            System.err.println("Erro ao executar a migração: " + e.getMessage());
//            // Opcional: Executar reparo em caso de falha
//            // flyway.repair();
//        }

        this.emf.getMetamodel().getEntities().forEach(entityType -> {
            ModelConverter.register(entityType.getJavaType());
        });

    }
}
