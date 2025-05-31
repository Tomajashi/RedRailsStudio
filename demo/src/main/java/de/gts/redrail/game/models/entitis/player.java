package de.gts.redrail.game.models.entitis;
import de.gts.redrail.game.models.Resources;
import de.gts.redrail.game.models.TrainsInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor 
@NoArgsConstructor
@Data
public class player {
    private int playerId;
    private TrainsInfo trainsInfo;
    private String playerName;
    private Resources resources;

}
