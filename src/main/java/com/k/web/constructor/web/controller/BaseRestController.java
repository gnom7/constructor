package com.k.web.constructor.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseRestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

}
