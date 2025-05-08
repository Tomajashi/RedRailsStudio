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
    // TODO: kommunikation mit dem Frontend um die reessourcen zu berechnen und es zuruckgeben
    // TODO: Kommunikation mit dem Frontend wenn der spieler die ressourcen benutzt um die ressourcen zu updaten
    
}
