package de.gts.redrail.game.utils;

import de.gts.redrail.game.models.entities.Rail;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

public class RailUtil {

    public static Optional<Rail> getRailByUid(List<Rail> railList, String uid) {
        if (StringUtils.isEmpty(uid)) {
            return Optional.empty();
        }

        for (Rail rail : railList) {
            if (rail.getUId().equals(uid)) {
                return Optional.of(rail);
            }
        }

        return Optional.empty();
    }
}
