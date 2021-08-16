package nl.kristalsoftware.association.team.domain.team.event;

import lombok.Getter;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class TeamRegistered implements BaseEvent<TeamEventData> {

    @Getter
    private final TeamEventData eventData;

    public TeamRegistered(TeamEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
