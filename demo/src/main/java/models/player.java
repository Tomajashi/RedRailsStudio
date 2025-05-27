package models;
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
}
