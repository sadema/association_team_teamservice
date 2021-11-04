package nl.kristalsoftware.association.team.domain.player;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.domain.player.command.AssignPlayerRole;
import nl.kristalsoftware.association.team.domain.player.command.MakePlayerMemberOfTeam;
import nl.kristalsoftware.association.team.domain.player.command.SignUpPlayer;
import nl.kristalsoftware.association.team.domain.player.command.StopPlaying;
import nl.kristalsoftware.association.team.domain.player.command.UpdatePlayerProperties;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import nl.kristalsoftware.domain.base.annotations.DomainService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@DomainService
public class PlayerService {

    private final PlayerDataStore playerDataStore;

    private final ApplicationEventPublisher eventPublisher;

    public void signUpPlayer(
            String reference,
            String firstName,
            String lastName,
            Long birthDate,
            String kind) {
        Player player = playerDataStore.loadAggregate(PlayerReference.of(reference), eventPublisher);
        player.handleCommand(SignUpPlayer.of(firstName, lastName, birthDate, kind));
    }

    public void updatePlayer(
            PlayerReference playerReference,
            TeamReference teamReference,
            PlayerRole playerRole) {
        Player player = playerDataStore.loadAggregate(playerReference, eventPublisher);
        player.handleCommand(new MakePlayerMemberOfTeam(teamReference));
        player.handleCommand(new AssignPlayerRole(playerRole));
    }

    public void updatePlayerProperties(String reference, String firstName, String lastName, Long birthDate) {
        Player player = playerDataStore.loadAggregate(PlayerReference.of(UUID.fromString(reference)), eventPublisher);
        player.handleCommand(UpdatePlayerProperties.of(firstName, lastName, birthDate));
    }

    public void stopPlaying(String reference, String kind) {
        if (kind.equals("PLAYER")) {
            Player player = playerDataStore.loadAggregate(PlayerReference.of(UUID.fromString(reference)), eventPublisher);
            player.handleCommand(StopPlaying.of());
        }
    }

    public void startOrStopAsPlayer(String reference, String firstName, String lastName, Long birthDate, String kind) {
        if (kind.equals("PLAYER")) {
            Player player = playerDataStore.loadAggregate(PlayerReference.of(reference), eventPublisher);
            player.handleCommand(SignUpPlayer.of(firstName, lastName, birthDate, kind));
        }
        else {
            Player player = playerDataStore.loadAggregate(PlayerReference.of(reference), eventPublisher);
            player.handleCommand(StopPlaying.of());
        }

    }

    public Player getPlayer(String reference) {
        return playerDataStore.loadAggregate(PlayerReference.of(reference), eventPublisher);
    }
}
