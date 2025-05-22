package com;
import models.TrainsInfo;
import models.train;
import models.Resources;
import java.time.Duration;
import org.springframework.context.annotation.Configuration;
import java.time.OffsetDateTime;

@Configuration

public class resourcesCalulator {
    private Resources resources = new Resources();
    private boolean calculateResources;
    private int coins = resources.getDB_coin();
    private OffsetDateTime lastIncrement;
    private OffsetDateTime lastFortrainsIncrement;
    private TrainsInfo trainsInfo;
    
    private final int coinCap = 100_000;
    private final int coinIncrementPerSecond = 5;
    private final int trainsInfoIntervalSeconds = 10;

    public resourcesCalulator(TrainsInfo trainsInfo) {
        this.trainsInfo = trainsInfo;
    }

    public void incrementCoinsIfNecessary(OffsetDateTime currentTime) {
        if (lastIncrement == null) {
            lastIncrement = currentTime;
            
            return;
        }

        Duration duration = Duration.between(lastIncrement, currentTime);
        if (duration.getSeconds() >= 1 && duration.getSeconds() < trainsInfoIntervalSeconds) {
            long secondsElapsed = duration.getSeconds();
            coins = Math.min(coins + (int)(coinIncrementPerSecond * secondsElapsed), coinCap);
            lastIncrement = currentTime;
        }
        else if (duration.getSeconds() >= trainsInfoIntervalSeconds) {
            long secondsElapsed = duration.getSeconds();
            coins = Math.min(coins + (int)((coinIncrementPerSecond * secondsElapsed)* (trainsInfo.getTotalPassengers() * trainsInfo.getTotalRailways()*trainsInfo.getTotalTrains())), coinCap);
            lastIncrement = currentTime;
        }
    }



}

