package com.apiversioning.filters.api.precondtions;

import com.apiversioning.filters.api.FilterOrders;
import com.apiversioning.filters.api.precondtions.processors.ApiPreconditionRequestProcessor;
import com.apiversioning.filters.api.version.AbstractRequestProxyFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author salsulami
 * @since 8/8/2017
 */
@Component
public class Preconditions extends AbstractRequestProxyFilter{


    @Autowired
    private List<ApiPreconditionRequestProcessor> processors;

    private static Logger log = LoggerFactory.getLogger(Preconditions.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterOrders.PRECONDITIONS_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        final  RequestContext requestContext =  getContext();

       processors.forEach(processor->{
           if(!processor.processed(getRequest())){
               requestContext.unset();
               final HttpServletResponse response = requestContext.getResponse();
               serializeAndWriteResponse(processor.getResponse(),response);
           }
       });

        return null;
    }


    private void serializeAndWriteResponse(final Object responseBody,final HttpServletResponse response){
        ObjectMapper objectMapper =  new ObjectMapper();
        String serializedBody = null;
        response.setContentType("application/json");
        try {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            serializedBody = objectMapper.writeValueAsString(responseBody);
        } catch (IOException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
        }

        try {
            IOUtils.write(serializedBody.getBytes(),response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
