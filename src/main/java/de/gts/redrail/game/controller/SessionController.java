package de.gts.redrail.game.controller;

import de.gts.redrail.game.constants.GameStateEnum;
import de.gts.redrail.game.models.entities.ActionResult;
import de.gts.redrail.game.models.dtos.PlayerDto;
import de.gts.redrail.game.models.dtos.PlayerOverviewDto;
import de.gts.redrail.game.models.dtos.SessionOverviewDto;
import de.gts.redrail.game.service.SessionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static de.gts.redrail.game.constants.ResponseText.*;

@RestController
@RequiredArgsConstructor
public class SessionController {

    public final SessionService sessionService;

    @PostMapping("/session/player/{playerUid}/resource")
    public ResponseEntity<String> getResource(@PathVariable(name = "playerUid") @NotNull String playerUid) {
        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().body(GET_RESOURCE_FAILED_SESSION_IS_NOT_RUNNING);
        }

        ActionResult actionResult = sessionService.getResource(playerUid);

        if (actionResult.isSuccessful()) {
            return ResponseEntity.ok(actionResult.getMessage());
        } else {
            return ResponseEntity.badRequest().body(actionResult.getMessage());
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

    @PostMapping("/session/{sessionName}/player")
    public ResponseEntity<String> joinSession(@PathVariable(name = "sessionName") @NotNull String sessionName, @RequestBody PlayerOverviewDto playerOverviewDto) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.NOT_STARTED)) {
            return ResponseEntity.badRequest().body(PLAYER_JOIN_SESSION_FAILED_SESSION_IS_RUNNING_OR_FINISHED);
        }

        boolean result = sessionService.joinSession(playerOverviewDto);

        if (result) {
            return ResponseEntity.ok(PLAYER_JOINED_SESSION);
        } else {
            return ResponseEntity.badRequest().body(PLAYER_JOIN_SESSION_FAILED);
        }
    }
    @PostMapping("/session/{sessionName}/GetPlayers")
    public ResponseEntity<String> getPlayers(@PathVariable(name = "sessionName") @NotNull String sessionName) {
        if (!sessionService.isSessionNameMatching(sessionName)) {
            return ResponseEntity.noContent().build();
        }

        if (!sessionService.getGameState().equals(GameStateEnum.RUNNING)) {
            return ResponseEntity.badRequest().body(GET_PLAYERS_FAILED_SESSION_IS_NOT_RUNNING);
        }

        String players = sessionService.getPlayers();

        if (players != null) {
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

    @PatchMapping("/session/{sessionName}/player/{playerUid}/rail/{railUid}")
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

    private ResponseEntity<String> handleActionResult(ActionResult actionResult) {
        if (actionResult.isSuccessful()) {
            return ResponseEntity.ok(actionResult.getMessage());
        } else {
            return ResponseEntity.badRequest().body(actionResult.getMessage());
        }
    }
}
