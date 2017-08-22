package com.apiversioning.filters.api.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringTokenizer;

/**
 * @Author salsulami
 * @since 8/6/2017
 */
public abstract class AbstractVersionBasedRedirectFilter extends AbstractRequestProxyFilter{

    private static final Logger log = LoggerFactory.getLogger(AbstractVersionBasedRedirectFilter.class);
    private static final String MEDIA_VENDOR_ACCEPT_HEADER = "vnd.api.";


    public AbstractVersionBasedRedirectFilter(){}

    protected String getApiVersion(){
        String headerValue =  getHeaderValue("Accept");
        return parseAcceptHeader(headerValue);
    }

    private String parseAcceptHeader(final String acceptHeaderValue){
        StringTokenizer stringTokenizer =  new StringTokenizer(acceptHeaderValue,";");
        while(stringTokenizer.hasMoreElements()){
            String mediaType = stringTokenizer.nextToken();
            if(mediaType.contains(MEDIA_VENDOR_ACCEPT_HEADER)){
                int indexOfMediaVersion = mediaType.indexOf(MEDIA_VENDOR_ACCEPT_HEADER);
                int indexOfPlusSing = mediaType.indexOf("+");
                String vnd = mediaType.substring(indexOfMediaVersion,indexOfPlusSing);
                return vnd.substring(MEDIA_VENDOR_ACCEPT_HEADER.length());
            }
        }
        return getLatestVersion();
    }
}
