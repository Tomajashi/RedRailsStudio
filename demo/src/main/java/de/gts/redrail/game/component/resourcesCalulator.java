package de.gts.redrail.game.component;
import java.time.Duration;
import org.springframework.stereotype.Component;

import game.models.*;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
@Component
@RequiredArgsConstructor

public class resourcesCalulator {
    private Resources resources;                                           
    private final Runtimeclock runtimeClock;
    private int coins;
    private int playerId;
    private OffsetDateTime lastIncrement;
    private TrainsInfo trainsInfo;
    
    private final int coinIncrementPerSecond = 5;


    public resourcesCalulator( int playerId) {
        this.playerId = playerId;
        this.resources = new Resources(playerId);
        this.lastIncrement = null;
        this.trainsInfo = new TrainsInfo(playerId);
        this.coins = resources.getDB_coin();
    }

    public void incrementCoinsIfNecessary(OffsetDateTime currentTime) {
        if (lastIncrement == null) {
            lastIncrement = currentTime;
            
            return;
        }

        Duration duration = Duration.between(lastIncrement, currentTime);
        if (duration.getSeconds() >= 1) {
            long secondsElapsed = duration.getSeconds();
            coins = coins + (int)((coinIncrementPerSecond * secondsElapsed)* (trainsInfo.getTotalPassengers() * trainsInfo.getTotalRailways()*trainsInfo.getTotalTrains()));
            lastIncrement = currentTime;
        }
    }



}

