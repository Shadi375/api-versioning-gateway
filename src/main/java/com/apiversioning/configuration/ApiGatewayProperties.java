package com.apiversioning.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author salsulami
 * @since 8/6/2017
 */
@Configuration
@ConfigurationProperties(prefix = "api.gateway")
public class ApiGatewayProperties {


    private String latest;
    private String redirectContextPreFix;

    public String getLatest(){
        return latest;
    }

    public void setLatest(final String latest){
        this.latest= latest;
    }

    public String getRedirectContextPreFix(){
        return this.redirectContextPreFix;
    }

    public void setRedirectContextPreFix(final String redirectContextPreFix){
        this.redirectContextPreFix = redirectContextPreFix;
    }
}
