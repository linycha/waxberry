package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("test")
public class TestController {

    @GetMapping("aaaacl")
    public String test(){
        return "我来了 aaaacl!!!";
    }
}
