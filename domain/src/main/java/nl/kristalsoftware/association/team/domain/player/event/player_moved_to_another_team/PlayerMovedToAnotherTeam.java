package nl.kristalsoftware.association.team.domain.player.event.player_moved_to_another_team;

import lombok.Getter;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class PlayerMovedToAnotherTeam implements BaseEvent<PlayerEventData> {

    @Getter
    private final PlayerEventData eventData;

    public PlayerMovedToAnotherTeam(PlayerEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
