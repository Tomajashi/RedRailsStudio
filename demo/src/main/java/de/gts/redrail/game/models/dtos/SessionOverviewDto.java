package de.gts.redrail.game.models.dtos;

import de.gts.redrail.game.constants.SessionStateEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SessionOverviewDto {
    private  SessionStateEnum sessionState ;    
}
