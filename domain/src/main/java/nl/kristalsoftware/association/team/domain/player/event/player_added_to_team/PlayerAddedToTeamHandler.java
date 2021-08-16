package nl.kristalsoftware.association.team.domain.player.event.player_added_to_team;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.domain.player.PlayerDataStore;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlayerAddedToTeamHandler implements DomainEventHandler<PlayerEventData> {

    private final PlayerDataStore playerDataStore;

    @Override
    public String appliesTo() {
        return PlayerAddedToTeam.class.getSimpleName();
    }

    @Override
    public void saveEvent(PlayerEventData eventData) {
        playerDataStore.savePlayerAddedToTeamEvent(eventData);
    }
}
