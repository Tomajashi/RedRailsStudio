package main.java.de.gts.redrail.game.utils;

import de.gts.redrail.game.models.entities.Station;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

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
