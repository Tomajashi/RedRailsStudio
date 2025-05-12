package com;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RuntimeClock {

    private Instant startTime;

    public void Init() {
        this.startTime = Instant.now();
        System.out.println("Runtime clock started at: " + startTime);
    }
}
