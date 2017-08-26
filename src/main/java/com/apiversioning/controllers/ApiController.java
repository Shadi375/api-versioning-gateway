package com.apiversioning.controllers;

import com.apiversioning.utils.HashMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @Author shadialsulami
 * @Since 8/22/17
 */
@RestController
public class ApiController {

    @Autowired
    private RequestMappingHandlerMapping mappings;

    @RequestMapping("/")
    public Map<String,Object> landingApi(){

        return HashMapBuilder.builder()
                .put("version",1.6)
                .put("timestamp", LocalDateTime.now())
                .put("handers",mappings.getHandlerMethods().keySet())
                .build();
    }


    @RequestMapping("/ci-test")
    public String ciTesting(){
        return "it works";
    }


    /**
     * trigger deployment
     * @return
     */
    @RequestMapping("/now")
    public Date ciTesting2(){
        return new Date();
    }


    @RequestMapping("/gate-way")
    public String apiGateway(){
        return "api-gateway";
    }
}
