package nl.kristalsoftware.association.team.domain.player.event.player_signed_up;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.domain.player.PlayerDataStore;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlayerSignedUpHandler implements DomainEventHandler<PlayerEventData> {

    private final PlayerDataStore playerDataStore;

    @Override
    public String appliesTo() {
        return PlayerSignedUp.class.getSimpleName();
    }

    @Override
    public void saveEvent(PlayerEventData eventData) {
        playerDataStore.savePlayerSignedUpEvent(eventData);
    }
}
