package de.gts.redrail.game.mappers.entities;

import de.gts.redrail.game.models.dtos.PlayerOverviewDto;
import de.gts.redrail.game.models.entities.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player map(PlayerOverviewDto playerOverviewDto) {
        if (playerOverviewDto == null) {
            return null;
        }

        Player player = new Player();

        player.setUId(playerOverviewDto.getUId());
        player.setName(playerOverviewDto.getName());

        return player;
    }
}
