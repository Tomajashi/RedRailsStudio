package com;

import org.springframework.stereotype.Component;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Component
public class RuntimeClock {

    private OffsetDateTime clock;

    public void Init() {

        clock = OffsetDateTime.now();
        System.out.println("Runtime clock started at: " + clock);
        // Simulate the clock ticking every second        
    }
}
