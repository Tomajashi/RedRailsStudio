package de.gts.redrail.game;

import java.time.Duration;
import java.time.OffsetDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.gts.redrail.game.component.Runtimeclock;
import de.gts.redrail.game.models.Resources;
import de.gts.redrail.game.models.TrainsInfo;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class sessionController {

    private final Runtimeclock runtimeClock;

    
    // TODO: kommunikation mit dem Frontend um die reessourcen zu berechnen und es zuruckgeben
    // TODO: Kommunikation mit dem Frontend wenn der spieler die ressourcen benutzt um die ressourcen zu updaten
    

    @GetMapping("/api/resources")
    public Resources getResources() {
        Resources resources = new Resources();
        resources.setDB_coin(0);
        resources.setEnergy_capacity(10);
        resources.setMan_power(10);
        return resources;
    }

    @GetMapping("/api/getTrainInfo/")
    public TrainsInfo getTrainInfo(Integer playerId) {
        TrainsInfo trainsInfo = new TrainsInfo();
        
        OffsetDateTime now = OffsetDateTime.now();
        Long time = Duration.between(now, runtimeClock.getStartTime()).toSeconds();
        return trainsInfo;
    }        


    
}
