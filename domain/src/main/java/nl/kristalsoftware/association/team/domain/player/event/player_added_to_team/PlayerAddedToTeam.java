package nl.kristalsoftware.association.team.domain.player.event.player_added_to_team;

import lombok.Getter;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class PlayerAddedToTeam implements BaseEvent<PlayerEventData> {

    @Getter
    private final PlayerEventData eventData;

    public PlayerAddedToTeam(PlayerEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
