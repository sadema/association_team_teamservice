package nl.kristalsoftware.association.team.domain.player.event.player_signed_up;

import lombok.Getter;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class PlayerSignedUp implements BaseEvent<PlayerEventData> {

    @Getter
    private final PlayerEventData eventData;

    public PlayerSignedUp(PlayerEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
