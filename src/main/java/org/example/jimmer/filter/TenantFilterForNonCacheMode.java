package org.example.jimmer.filter;


import org.babyfish.jimmer.sql.filter.Filter;
import org.babyfish.jimmer.sql.filter.FilterArgs;
import org.example.jimmer.TenantProvider;
import org.example.jimmer.entity.common.TenantAwareProps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/*
 * see JSqlClient.Builder.addFilters
 * 
 * This bean is only be used when cache is NOT used.
 */
@ConditionalOnMissingBean(TenantFilterForCacheMode.class)
@Component
public class TenantFilterForNonCacheMode implements Filter<TenantAwareProps> { // ❶

    protected final TenantProvider tenantProvider;

    public TenantFilterForNonCacheMode(TenantProvider tenantProvider) {
        this.tenantProvider = tenantProvider;
    }

    @Override
    public void filter(FilterArgs<TenantAwareProps> args) {
        String tenant = tenantProvider.get();
        if (tenant != null) {
            args.where(args.getTable().tenant().eq(tenant));
        }
    }
}

/*----------------Documentation Links----------------
❶ https://babyfish-ct.github.io/jimmer/docs/query/global-filter/user-filter
---------------------------------------------------*/
