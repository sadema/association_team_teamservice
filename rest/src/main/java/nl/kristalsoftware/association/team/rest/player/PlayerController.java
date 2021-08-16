package nl.kristalsoftware.association.team.rest.player;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.PlayerReference;
import nl.kristalsoftware.association.team.domain.player.PlayerRole;
import nl.kristalsoftware.association.team.domain.player.PlayerService;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class PlayerController {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final PlayerService playerService;

    @PutMapping(value = "/players/{playerReference}", consumes = "application/json")
    public ResponseEntity<Void> updatePlayer(@RequestBody PlayerData playerData, @PathVariable String playerReference) {
        playerService.updatePlayer(
                PlayerReference.of(UUID.fromString(playerReference)),
                TeamReference.of(playerData.getTeamReference()),
                PlayerRole.of(playerData.getPlayerRole())
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
