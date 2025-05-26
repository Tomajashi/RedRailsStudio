package de.gts.redrail.game.constants;

public interface ResourceCost {
    Integer NEW_RAIL = 65;
    Integer NEW_TRAIN = 355;
    Integer NEW_STATION = 715;

    Integer UPGRADE_RAIL_FACTOR = NEW_RAIL + (NEW_RAIL / 2);
    Integer UPGRADE_TRAIN_FACTOR = NEW_TRAIN + (NEW_TRAIN / 2);
    Integer UPGRADE_STATION_FACTOR = NEW_STATION + (NEW_STATION / 2);
}
