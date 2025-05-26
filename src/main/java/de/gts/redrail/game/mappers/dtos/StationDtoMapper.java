package de.gts.redrail.game.mappers.dtos;

import de.gts.redrail.game.models.dtos.StationDto;
import de.gts.redrail.game.models.entities.Station;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StationDtoMapper {

    public StationDto map(Station station) {
        if (station == null) {
            return null;
        }

        StationDto stationDto = new StationDto();

        stationDto.setUId(station.getUId());
        stationDto.setLevel(station.getLevel());

        return stationDto;
    }

    public List<StationDto> map(List<Station> stationList) {
        if (stationList == null) {
            return null;
        }

        List<StationDto> stationDtoList = new ArrayList<>();

        for (Station station : stationList) {
            StationDto stationDto = map(station);

            if (stationDto != null) {
                stationDtoList.add(stationDto);
            }
        }

        return stationDtoList;
    }
}
