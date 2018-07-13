package com.yuan.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @RequestMapping(value = "/api/test", produces = "application/json;charset=utf-8")
    public String test(){
        return "api 测试~";
    }
}
