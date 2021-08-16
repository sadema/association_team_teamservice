package nl.kristalsoftware.association.team.domain.player.event.player_properties_updated;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.domain.player.PlayerDataStore;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlayerPropertiesUpdatedHandler implements DomainEventHandler<PlayerEventData> {

    private final PlayerDataStore playerDataStore;

    @Override
    public String appliesTo() {
        return PlayerPropertiesUpdated.class.getSimpleName();
    }

    @Override
    public void saveEvent(PlayerEventData eventData) {
        playerDataStore.savePlayerPropertiesUpdatedEvent(eventData);
    }
}
