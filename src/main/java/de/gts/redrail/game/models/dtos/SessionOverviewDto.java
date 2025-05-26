package de.gts.redrail.game.models.dtos;

import de.gts.redrail.game.constants.GameStateEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SessionOverviewDto {
    String sessionName;
    List<PlayerOverviewDto> players;
    GameStateEnum gameState;
    OffsetDateTime sessionStarted;
    OffsetDateTime sessionEnded;
}
