package com.apiversioning.filters.api.precondtions.processors;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author salsulami
 * @since 8/8/2017
 */
public interface ApiPreconditionRequestProcessor {


    boolean processed(final HttpServletRequest request);

    Object getResponse();
}
