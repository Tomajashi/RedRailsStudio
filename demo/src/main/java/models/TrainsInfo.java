package models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor 
@NoArgsConstructor
@Data
public class TrainsInfo {
    private  int totalTrains;
    private  int totalPassengers;
    private  int totalRailways;
    private ArrayList<train> trains = new ArrayList<>();
    private ArrayList<stations> stations = new ArrayList<>();
    private int playerId;
    
    
}
