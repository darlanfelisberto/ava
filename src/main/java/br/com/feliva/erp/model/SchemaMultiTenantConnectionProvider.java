package br.com.feliva.erp.model;

import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider<String>, ServiceRegistryAwareService {

    private DataSource dataSource;
    private String typeTenancy ;

    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        // Obtém o DataSource configurado no persistence.xml
//        this.dataSource = serviceRegistry.getService(DataSource.class);

        typeTenancy = (String) ((ConfigurationService)serviceRegistry
                .getService(ConfigurationService.class))
                .getSettings().get("hibernate.multiTenancy");

        dataSource = (DataSource) ((ConfigurationService)serviceRegistry
                .getService(ConfigurationService.class))
                .getSettings().get("hibernate.connection.datasource");
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }


    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try (Statement statement = connection.createStatement()) {
            // Comando para PostgreSQL. Adapte para seu banco (ex: `USE tenant_id` para MySQL).
            statement.execute("SET search_path TO '" + tenantIdentifier + "'");
            System.out.println("Conexão configurada para o schema: " + tenantIdentifier);
        } catch (SQLException e) {
            throw new SQLException("Não foi possível alterar para o schema do locatário [" + tenantIdentifier + "]", e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Reseta a conexão para o schema padrão antes de devolvê-la ao pool.
            statement.execute("SET search_path TO 'public'");
        } catch (SQLException e) {
            // Não relance a exceção, apenas logue, para não impedir que a conexão seja fechada.
            System.err.println("Não foi possível resetar o schema da conexão: " + e.getMessage());
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public boolean isUnwrappableAs(Class<?> unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        throw new UnknownUnwrapTypeException( unwrapType );
    }
}