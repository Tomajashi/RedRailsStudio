package com;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class controller {
    @GetMapping("api/method")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
