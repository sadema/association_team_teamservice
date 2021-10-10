package nl.kristalsoftware.association.team.domain.player.event.player_detached_from_team;

import lombok.Getter;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class PlayerDetachedFromTeam implements BaseEvent<PlayerEventData> {

    @Getter
    private final PlayerEventData eventData;

    public PlayerDetachedFromTeam(PlayerEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
