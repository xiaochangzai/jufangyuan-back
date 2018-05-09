package com.jufangyuan.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test.do")
    @ResponseBody
    public String test(){
        return "你想干嘛！";
    }

}
