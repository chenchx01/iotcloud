package com.iotcloud.system.web.control;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowordControl {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World.";
    }

}
