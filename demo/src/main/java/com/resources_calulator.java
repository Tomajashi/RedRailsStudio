package com;
import models.TrainsInfo;
import models.Resources;
import org.springframework.context.annotation.Configuration;


@Configuration

public class resources_calulator {
    private int DB_coin;
    private int energy_capacity; 
    private int man_power;
    
    
    public int DB_coin_calculator() {
        Resources resources = new Resources();
        TrainsInfo trainsInfo = new TrainsInfo();
        int coins = resources.getDB_coin()+(trainsInfo.getTotalTrains()*trainsInfo.getTotalPassengers()*trainsInfo.getTotalRailways());
        return coins;


//TODO: coins sollen wahrend des spiels hoch gezählt werden.
    }



}

