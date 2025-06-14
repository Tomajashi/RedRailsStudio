package de.gts.redrail.game.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.gts.redrail.game.constants.GameStateEnum;
import static de.gts.redrail.game.constants.ResponseText.CREATED_SESSION;
import static de.gts.redrail.game.constants.ResponseText.CREATE_SESSION_FAILED_SESSION_IS_ALREADY_CREATED;
import static de.gts.redrail.game.constants.ResponseText.END_SESSION;
import static de.gts.redrail.game.constants.ResponseText.GET_RESOURCE_FAILED_SESSION_IS_NOT_RUNNING;
import static de.gts.redrail.game.constants.ResponseText.PLAYER_JOINED_SESSION;
import static de.gts.redrail.game.constants.ResponseText.PLAYER_JOIN_SESSION_FAILED;
import static de.gts.redrail.game.constants.ResponseText.PLAYER_JOIN_SESSION_FAILED_SESSION_IS_RUNNING_OR_FINISHED;
import static de.gts.redrail.game.constants.ResponseText.STARTED_SESSION;
import static de.gts.redrail.game.constants.ResponseText.START_SESSION_FAILED_NO_PLAYER;
import static de.gts.redrail.game.constants.ResponseText.START_SESSION_FAILED_SESSION_IS_NOT_CREATED_IS_RUNNING_OR_FINISHED;
import de.gts.redrail.game.models.dtos.JoinSessionResponseDto;
import de.gts.redrail.game.models.dtos.PlayerDto;
import de.gts.redrail.game.models.dtos.PlayerOverviewDto;
import de.gts.redrail.game.models.dtos.SessionOverviewDto;
import de.gts.redrail.game.models.entities.ActionResult;
import de.gts.redrail.game.service.SessionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SessionController {

    public final SessionService sessionService;

    @PostMapping("/session/player/{playerUid}/resource")
    public ResponseEntity<String> getResource(@PathVariable(name = "playerUid") @NotNull String playerUid) {
        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().body(GET_RESOURCE_FAILED_SESSION_IS_NOT_RUNNING);
        }

        PlayerDto playerDto = sessionService.getPlayerStatus(playerUid);

        if (playerDto != null) {
            return ResponseEntity.ok(playerDto.toString());
        } else {
            return ResponseEntity.badRequest().body("Player not found");
        }
    }


    @PostMapping("/session/{sessionName}")
    public ResponseEntity<String> createSession(@PathVariable(name = "sessionName") @NotNull String sessionName) {
        if (!sessionService.getGameState().equals(GameStateEnum.NOT_CREATED)) {
            return ResponseEntity.badRequest().body(CREATE_SESSION_FAILED_SESSION_IS_ALREADY_CREATED);
        }

        sessionService.createSession(sessionName);

        return ResponseEntity.ok(CREATED_SESSION);
    }

    @PatchMapping("/session/{sessionName}/start")
    public ResponseEntity<String> startSession(@PathVariable(name = "sessionName") @NotNull String sessionName) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.NOT_STARTED)) {
            return ResponseEntity.badRequest().body(START_SESSION_FAILED_SESSION_IS_NOT_CREATED_IS_RUNNING_OR_FINISHED);
        }

        boolean result = sessionService.startSession();

        if (result) {
            return ResponseEntity.ok(STARTED_SESSION);
        } else {
            return ResponseEntity.badRequest().body(START_SESSION_FAILED_NO_PLAYER);
        }
    }

    @PatchMapping("/session/{sessionName}/end")
    public ResponseEntity<String> endSession(@PathVariable(name = "sessionName") @NotNull String sessionName) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        sessionService.endSession();

        return ResponseEntity.ok(END_SESSION);
    }

    @GetMapping("/session")
    public ResponseEntity<SessionOverviewDto> getSessionOverview() {
        return ResponseEntity.ok(sessionService.createCurrentSessionOverview());
    }

    @PostMapping("/session/{sessionName}/{playerName}")
    public ResponseEntity<JoinSessionResponseDto> joinSession(
            @PathVariable(name = "sessionName") @NotNull String sessionName,
            @PathVariable(name = "playerName") @NotNull String playerName
            ) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.ok(new JoinSessionResponseDto(false, null, "Session name does not match"));
        }

        if (!sessionService.getGameState().equals(GameStateEnum.NOT_STARTED)) {
            return ResponseEntity.ok(new JoinSessionResponseDto(false, null, PLAYER_JOIN_SESSION_FAILED_SESSION_IS_RUNNING_OR_FINISHED));
        }
        PlayerOverviewDto playerOverviewDto = new PlayerOverviewDto();
        playerOverviewDto.setUId(UUID.randomUUID().toString());
        playerOverviewDto.setName(playerName);

        boolean result = sessionService.joinSession(playerOverviewDto);

        if (result) {
            return ResponseEntity.ok(new JoinSessionResponseDto(true, playerOverviewDto.getUId(), PLAYER_JOINED_SESSION));
        } else {
            return ResponseEntity.ok(new JoinSessionResponseDto(false, null, PLAYER_JOIN_SESSION_FAILED));
        }
    }
    @GetMapping("/session/{sessionName}/GetPlayers")
    public ResponseEntity<List<PlayerOverviewDto>> getPlayers(@PathVariable(name = "sessionName") @NotNull String sessionName) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().build();
        }

        List<PlayerOverviewDto> players = sessionService.getPlayers();

        if (players != null && !players.isEmpty()) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    

    @GetMapping("/session/{sessionName}/player/{playerUid}")
    public ResponseEntity<PlayerDto> getPlayerStatus(@PathVariable(name = "sessionName") @NotNull String sessionName, @PathVariable(name = "playerUid") @NotNull String playerUid) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.noContent().build();
        }

        PlayerDto playerDto = sessionService.getPlayerStatus(playerUid);

        if (playerDto != null) {
            return ResponseEntity.ok(playerDto);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/session/{sessionName}/player/{playerUid}/rail")
    public ResponseEntity<String> buyRail(@PathVariable(name = "sessionName") @NotNull String sessionName, @PathVariable(name = "playerUid") @NotNull String playerUid) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().build();
        }

        ActionResult actionResult = sessionService.buyRail(playerUid);

        return handleActionResult(actionResult);
    }

    @GetMapping("/session/{sessionName}/player/{playerUid}/rail/{railUid}")
    public ResponseEntity<String> buyRail(@PathVariable(name = "sessionName") @NotNull String sessionName, @PathVariable(name = "playerUid") @NotNull String playerUid, @PathVariable(name = "railUid") @NotNull String railUid) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().build();
        }

        ActionResult actionResult = sessionService.upgradeRail(playerUid, railUid);

        return handleActionResult(actionResult);
    }
    @GetMapping("/session/{sessionName}/player/{playerUid}/train/{trainUid}")
    public ResponseEntity<String> upgradeTrain(@PathVariable(name = "sessionName") @NotNull String sessionName, @PathVariable(name = "playerUid") @NotNull String playerUid, @PathVariable(name = "trainUid") @NotNull String trainUid) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }
        if (trainUid == null || trainUid.isEmpty()) {
            return ResponseEntity.badRequest().body("Train UID must not be null or empty");
        }
        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().build();
        }

        ActionResult actionResult = sessionService.upgradeTrain(playerUid, trainUid);

        return handleActionResult(actionResult);
    }
    @PostMapping("/session/{sessionName}/player/{playerUid}/train")
    public ResponseEntity<String> buyTrain(@PathVariable(name = "sessionName") @NotNull String sessionName, @PathVariable(name = "playerUid") @NotNull String playerUid) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().build();
        }

        ActionResult actionResult = sessionService.buyTrain(playerUid);

        return handleActionResult(actionResult);
    }
    @PostMapping("/session/{sessionName}/player/{playerUid}/station")
    public ResponseEntity<String> buyStation(@PathVariable(name = "sessionName") @NotNull String sessionName, @PathVariable(name = "playerUid") @NotNull String playerUid) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().build();
        }

        ActionResult actionResult = sessionService.buyStation(playerUid);

        return handleActionResult(actionResult);
    }
    @GetMapping("/session/{sessionName}/player/{playerUid}/station/{stationUid}")
    public ResponseEntity<String> upgradeStation(@PathVariable(name = "sessionName") @NotNull String sessionName, @PathVariable(name = "playerUid") @NotNull String playerUid, @PathVariable(name = "stationUid") @NotNull String stationUid) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().build();
        }
        if (stationUid == null || stationUid.isEmpty()) {
            return ResponseEntity.badRequest().body("Station UID must not be null or empty");
        }
        ActionResult actionResult = sessionService.upgradeStation(playerUid, stationUid);

        return handleActionResult(actionResult);
    }
    
    private ResponseEntity<String> handleActionResult(ActionResult actionResult) {
        if (actionResult.isSuccessful()) {
            return ResponseEntity.ok(actionResult.getMessage());
        } else {
            return ResponseEntity.badRequest().body(actionResult.getMessage());
        }
    }
}
