package com;

import java.time.Duration;
import java.time.OffsetDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Data
public class Runtimeclock {
    private OffsetDateTime startTime;

    public  OffsetDateTime runtimeclock() {
        this.startTime = OffsetDateTime.now();
        return startTime;
    }


    public  long interval(OffsetDateTime startTime, OffsetDateTime now) {
        Duration interval = Duration.between(startTime, now);
        long seconds = interval.getSeconds();
        return seconds;
    }
}
