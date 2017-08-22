package com.apiversioning.filters.api.version;

import com.apiversioning.filters.api.FilterOrders;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Author salsulami
 * @since 8/6/2017
 */
@Component
public class VersionBasedRedirectFilter extends AbstractVersionBasedRedirectFilter {

    private static Logger log = LoggerFactory.getLogger(VersionBasedRedirectFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterOrders.API_VERSIONING_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = getContext();
        final String apiVersion = getApiVersion();
        String url = UriComponentsBuilder.fromHttpUrl(getHttpOrigin())
                .path(getRedirectContextPrefix() + apiVersion + "/" + getProxiedUri()).build().toString();
        log.info("redirect to : " + url);
        context.set("requestURI", url);
        return null;
    }
}
