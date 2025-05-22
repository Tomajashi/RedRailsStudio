package com;
import models.TrainsInfo;
import models.Resources;
import java.time.Duration;
import org.springframework.context.annotation.Configuration;
import java.time.OffsetDateTime;

@Configuration

public class resourcesCalulator {
    private Resources resources = new Resources();
    private boolean calculateResources;
    private int coins = resources.getDB_coin();
    
    private final int coinCap = 100_000;
    private final int coinIncrementPerSecond = 5;
    private final int trainsInfoIntervalSeconds = 10;

    public int DB_coin_calculator(boolean calculateResources) {
        TrainsInfo trainsInfo = new TrainsInfo();
        int coinsToAdd = trainsInfo.getTotalTrains() * trainsInfo.getTotalPassengers() * trainsInfo.getTotalRailways();
        coins = Math.min(coins + coinsToAdd, coinCap);
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
                        coins = Math.min(coins + (int)(coinIncrementPerSecond * secondsElapsed), coinCap);
                        lastIncrement = lastIncrement.plusSeconds(secondsElapsed);
                    }

                    // TrainsInfo calculation every 30 seconds
                    if (trainsInfoDuration.getSeconds() >= trainsInfoIntervalSeconds) {
                        DB_coin_calculator(true);
                        lastTrainsInfoCalc = lastTrainsInfoCalc.plusSeconds(
                            (trainsInfoDuration.getSeconds() / trainsInfoIntervalSeconds) * trainsInfoIntervalSeconds
                        );
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            //TODO: the calcualtion of the resources should be done without the need of a thread
        });
        coinThread.setDaemon(true);
        coinThread.start();
    }



}

