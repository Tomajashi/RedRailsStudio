package de.gts.redrail.game.component;

import de.gts.redrail.game.models.entities.Player;
import de.gts.redrail.game.models.entities.Rail;
import de.gts.redrail.game.utils.RailUtil;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static de.gts.redrail.game.constants.ResourceCost.*;

@Component
public class ResourceValidator {

    public boolean canBuyNewRail(Player player) {
        return player.getResourceRack().getDbCoin() >= NEW_RAIL;
    }

    public boolean canUpgradeRail(Player player, String railUid) {
        Optional<Rail> railOptional = RailUtil.getRailByUid(player.getRails(), railUid);

        if (railOptional.isEmpty()) {
            return false;
        }

        return player.getResourceRack().getDbCoin() >= (railOptional.get().getLevel() + 1) * UPGRADE_RAIL_FACTOR;
    }

    public boolean requirmentsForTrain(Player player) {
        return player.getStations().size() >= 2 && player.getRails().size() >= player.getTrains().size() + 1;
    }

    public boolean canBuyNewTrain(Player player) {
        return player.getResourceRack().getDbCoin() >= NEW_TRAIN;
    }

    public boolean canBuyNewStation(Player player) {
        return player.getResourceRack().getDbCoin() >= NEW_STATION;
    }
}
