package de.gts.redrail.game.models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;



@AllArgsConstructor 
@Data
public class TrainsInfo {
    private  int totalTrains;
    private  int totalPassengers;
    private  int totalRailways;
    private ArrayList<train> trains = new ArrayList<>();
    private ArrayList<stations> stations = new ArrayList<>();
    private ArrayList<rail> rails = new ArrayList<>();
    private int playerId;
    
    public TrainsInfo( int playerId) {
        this.totalTrains = 1;
        this.totalPassengers = 100;
        this.totalRailways = 1;
        this.playerId = playerId;
    }
    


}
