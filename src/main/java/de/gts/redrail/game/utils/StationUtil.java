package de.gts.redrail.game.utils;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import de.gts.redrail.game.models.entities.Station;

public class StationUtil {

    public static Optional<Station> getStationByUid(List<Station> stationList, String uid) {
        if (StringUtils.isEmpty(uid)) {
            return Optional.empty();
        }

        for (Station station : stationList) {
            if (station.getUId().equals(uid)) {
                return Optional.of(station);
            }
        }
        // If no station with the given UID is found, return an empty Optional

        return Optional.empty();
    }
    
}
