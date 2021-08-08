package com.factotum.rin.component;

import com.factotum.rin.model.TenantEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class TenantEntityListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void setTenantId(Object object) {

        if (object instanceof TenantEntity entity) {
            String tenantId = ((Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaimAsString("sub");
            if (tenantId.isBlank()) {
                throw new RuntimeException(
                        "Tenant id has not been set to the global context and was therefore null. A valid tenant id is required to run queries.");
            }
            if (entity.getTenantId() != null && !entity.getTenantId().equals(tenantId)) {
                throw new IllegalStateException("Current tenant cannot modify an entity owned by another tenant");
            }
            entity.setTenantId(tenantId);
        }

    }

}
