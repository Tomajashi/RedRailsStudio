package de.gts.redrail.game.models;
import lombok.AllArgsConstructor;
import lombok.Data;



@AllArgsConstructor 

@Data
public class Resources {
    private int playerId; 
    private int DB_coin;
    private int energy_capacity;
    private int man_power;

   public Resources(int playerId)
    {
         this.DB_coin = 1000;
         this.energy_capacity = 5;
         this.man_power = 20;
        this.playerId = playerId;
    }
}