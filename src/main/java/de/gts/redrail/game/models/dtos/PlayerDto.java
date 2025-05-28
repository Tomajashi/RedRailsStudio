package de.gts.redrail.game.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    String uId; 
    String name;

    Integer dbCoin;
    Integer employees;
    Integer power;

    List<StationDto> stationDtos;
    List<TrainDto> trainDtos;
    List<RailDto> railDtos;
}
