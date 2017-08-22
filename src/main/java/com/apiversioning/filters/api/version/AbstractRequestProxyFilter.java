package com.apiversioning.filters.api.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

import java.util.Map;

/**
 * @Author salsulami
 * @since 8/6/2017
 */

public abstract class AbstractRequestProxyFilter extends AbstractRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(AbstractRequestProxyFilter.class);

    @Autowired
    private ZuulProperties zuulProperties;


    protected boolean isRequestProxied() {
        return getProxiedUri() != null;
    }

    private Map.Entry<String,ZuulRoute> findRouteEntry() {
        final String requestUrl = getRequest().getRequestURI();
        for (Map.Entry<String, ZuulRoute> route : zuulProperties.getRoutes().entrySet()) {
            if (requestUrl.startsWith(forwardSlashDecorator(route.getKey()))) {
                return route;
            }
        }
        return null;
    }

    private ZuulRoute findRoute() {
        Map.Entry<String,ZuulRoute> routeEntry = findRouteEntry();
        if(routeEntry != null){
            return routeEntry.getValue();
        }
        return null;
    }

    protected String getProxiedUri() {
        final String requestUrl = getRequest().getRequestURI();
        final Map.Entry<String,ZuulRoute> route = findRouteEntry();
        if (route != null) {
            return requestUrl.substring(forwardSlashDecorator(route.getKey()).length());
        }
        return null;
    }


    protected String getHttpOrigin() {
        ZuulRoute route = findRoute();
        return route != null ? route.getUrl() : null;
    }

    private String forwardSlashDecorator(final String str) {
        return "/" + str;
    }
}
