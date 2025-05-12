package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final RuntimeClock runtimeClock;

    public Controller(RuntimeClock runtimeClock) {
        this.runtimeClock = runtimeClock;
    }
    // TODO: kommunikation mit dem Frontend um die reessourcen zu berechnen und es zuruckgeben
    // TODO: Kommunikation mit dem Frontend wenn der spieler die ressourcen benutzt um die ressourcen zu updaten
    

    @GetMapping("/api/resources")
    public Resources getResources() {
        
    }

    @PostMapping("/api/wait")
    public String waitForClock(@RequestBody String trigger) {
        if ("start".equalsIgnoreCase(trigger)) {
            runtimeClock.Init(); // Start the runtime clock
            return "Runtime clock started!";
        }
        return "Invalid trigger!";
    }
}
