package com;

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
}
