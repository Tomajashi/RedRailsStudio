package com;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
@Component
public class runtimeclock {
    
    private Instant startTime;
   public void Init(){
    this.startTime = Instant.now();
 }

}
