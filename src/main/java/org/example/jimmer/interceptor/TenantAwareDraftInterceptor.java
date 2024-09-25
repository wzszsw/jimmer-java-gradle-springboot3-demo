package org.example.jimmer.interceptor;

import org.babyfish.jimmer.ImmutableObjects;
import org.babyfish.jimmer.sql.DraftInterceptor;

import org.example.jimmer.TenantProvider;
import org.example.jimmer.entity.common.TenantAware;
import org.example.jimmer.entity.common.TenantAwareDraft;
import org.example.jimmer.entity.common.TenantAwareProps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TenantAwareDraftInterceptor implements DraftInterceptor<TenantAware, TenantAwareDraft> { //

    private final TenantProvider tenantProvider;

    private final String defaultTenant;

    public TenantAwareDraftInterceptor(
            TenantProvider tenantProvider,
            @Value("${demo.default-tenant}") String defaultTenant
    ) {
        this.tenantProvider = tenantProvider;
        this.defaultTenant = defaultTenant;
    }

    @Override
    public void beforeSave(@NotNull TenantAwareDraft draft, @Nullable TenantAware original) {
        if (!ImmutableObjects.isLoaded(draft, TenantAwareProps.TENANT)) {
            String tenant = tenantProvider.get();
            if (tenant == null) {
                tenant = defaultTenant;
            }
            draft.setTenant(tenant);
        }
    }
}
