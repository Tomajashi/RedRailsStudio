package de.gts.redrail.game.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor 
@NoArgsConstructor
@Data

public class train {
    private int trainId;
    private int maxCapacity;
    private int trainLevel;
}
