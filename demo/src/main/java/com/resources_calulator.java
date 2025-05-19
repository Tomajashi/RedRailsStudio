package com;
import models.TrainsInfo;
import models.Resources;
import java.time.Duration;
import org.springframework.context.annotation.Configuration;
import java.time.OffsetDateTime;

@Configuration

public class Resources_calulator {
    private Resources resources = new Resources();
    private boolean calculateResources;
    private int coins = resources.getDB_coin();
    
    private final int COIN_CAP = 100_000;
    private final int COIN_INCREMENT_PER_SECOND = 5;
    private final int TRAINSINFO_INTERVAL_SECONDS = 30;

    public int DB_coin_calculator(boolean calculateResources) {
        TrainsInfo trainsInfo = new TrainsInfo();
        int coinsToAdd = trainsInfo.getTotalTrains() * trainsInfo.getTotalPassengers() * trainsInfo.getTotalRailways();
        coins = Math.min(coins + coinsToAdd, COIN_CAP);
        return coins;
    }

    public void startCoinIncrementThread() {
        Thread coinThread = new Thread(() -> {
            OffsetDateTime lastIncrement = OffsetDateTime.now();
            OffsetDateTime lastTrainsInfoCalc = OffsetDateTime.now();
            while (this.calculateResources) {
                try {
                    Thread.sleep(200);
                    OffsetDateTime now = OffsetDateTime.now();
                    Duration duration = Duration.between(lastIncrement, now);
                    Duration trainsInfoDuration = Duration.between(lastTrainsInfoCalc, now);

                    // Increment coins per second
                    if (duration.getSeconds() >= 1) {
                        long secondsElapsed = duration.getSeconds();
                        coins = Math.min(coins + (int)(COIN_INCREMENT_PER_SECOND * secondsElapsed), COIN_CAP);
                        lastIncrement = lastIncrement.plusSeconds(secondsElapsed);
                    }

                    // TrainsInfo calculation every 30 seconds
                    if (trainsInfoDuration.getSeconds() >= TRAINSINFO_INTERVAL_SECONDS) {
                        DB_coin_calculator(true);
                        lastTrainsInfoCalc = lastTrainsInfoCalc.plusSeconds(
                            (trainsInfoDuration.getSeconds() / TRAINSINFO_INTERVAL_SECONDS) * TRAINSINFO_INTERVAL_SECONDS
                        );
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        coinThread.setDaemon(true);
        coinThread.start();
    }



}

