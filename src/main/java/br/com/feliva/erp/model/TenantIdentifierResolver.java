package br.com.feliva.erp.model;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    private String tenant;

    public void setCurrentTenant(String tenantId) {
        this.tenant = tenantId;
    }

    public String resolveCurrentTenantIdentifier() {
        return this.tenant;
    }

    public boolean validateExistingCurrentSessions() {
        return true;
    }
}