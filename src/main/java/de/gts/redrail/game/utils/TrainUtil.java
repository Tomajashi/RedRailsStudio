package de.gts.redrail.game.utils;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import de.gts.redrail.game.models.entities.Train;

public class TrainUtil {
    
    public static Optional<Train> getTrainByUid(List<Train> trainList, String uid) {
        if (StringUtils.isEmpty(uid)) {
            return Optional.empty();
        }

        for (Train train : trainList) {
            if (train.getUId().equals(uid)) {
                return Optional.of(train);
            }
        }

        // If no train with the given UID is found, return an empty Optional
        return Optional.empty();
    }
}
