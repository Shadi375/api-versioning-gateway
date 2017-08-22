package com.apiversioning.filters.api.precondtions.processors;

import com.apiversioning.configuration.ApiGatewayProperties;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author salsulami
 * @since 8/8/2017
 */
@Component
public class SimpleProcessorImp implements ApiPreconditionRequestProcessor {

    @Override
    public boolean processed(final HttpServletRequest request) {
        return true;
    }

    @Override
    public Object getResponse() {
        Map<String,Object> response =Maps.newHashMap();
        response.put("data","can't serve right now, service is closed");
        return response;
    }
}
