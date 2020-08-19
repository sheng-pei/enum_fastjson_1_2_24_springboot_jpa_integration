package org.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {

    @PostMapping("/test")
    public TestParam test(@RequestBody TestParam param) {
        return param;
    }

    @GetMapping("/test1")
    public String test1() {
        return "hello world";
    }

}
