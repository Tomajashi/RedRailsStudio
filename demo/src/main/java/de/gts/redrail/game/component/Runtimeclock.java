package de.gts.redrail.game.component;


import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class Runtimeclock {
    private OffsetDateTime startTime;
    private OffsetDateTime clock;
    public void startClock() {
        this.startTime = OffsetDateTime.now();
    }


    public void updateClock() {
        clock = OffsetDateTime.now();
    }
}
