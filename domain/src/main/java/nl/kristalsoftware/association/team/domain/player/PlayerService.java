package nl.kristalsoftware.association.team.domain.player;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.domain.player.command.AssignPlayerRole;
import nl.kristalsoftware.association.team.domain.player.command.MakePlayerMemberOfTeam;
import nl.kristalsoftware.association.team.domain.player.command.SignUpPlayer;
import nl.kristalsoftware.association.team.domain.player.command.UpdatePlayerProperties;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import nl.kristalsoftware.domain.base.annotations.DomainService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@DomainService
public class PlayerService {

    private final PlayerDataStore playerDataStore;

    private final ApplicationEventPublisher eventPublisher;

    public void signUpPlayer(
            String firstName,
            String lastName,
            Long birthDate) {
        Player player = playerDataStore.loadAggregate(eventPublisher);
        player.handleCommand(SignUpPlayer.of(firstName, lastName, birthDate));
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
}
