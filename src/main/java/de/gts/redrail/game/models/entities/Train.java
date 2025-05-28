package de.gts.redrail.game.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Train {
    String uId;
    Integer level;
    Integer requiredEmployees;
    Integer requiredPower;
}
