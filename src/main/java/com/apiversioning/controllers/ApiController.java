package com.apiversioning.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shadialsulami
 * @Since 8/22/17
 */
@RestController
public class ApiController {


    @RequestMapping("/")
    public String apiGateway(){
        return "api-gateway";
    }
}
