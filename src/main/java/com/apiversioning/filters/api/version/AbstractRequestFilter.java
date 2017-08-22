package com.apiversioning.filters.api.version;

import com.apiversioning.configuration.ApiGatewayProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author salsulami
 * @since 8/6/2017
 */
public abstract class AbstractRequestFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(AbstractRequestFilter.class);


    @Autowired
    protected ApiGatewayProperties apiGatewayProperties;

    protected RequestContext getContext() {
        return RequestContext.getCurrentContext();
    }

    protected HttpServletRequest getRequest() {
        return getContext().getRequest();
    }

    protected String getHeaderValue(String headerName) {
        return getRequest().getHeader(headerName);
    }


    public String getLatestVersion() {
        return apiGatewayProperties.getLatest();
    }

    public String getRedirectContextPrefix() {
        return apiGatewayProperties.getRedirectContextPreFix();
    }
}
