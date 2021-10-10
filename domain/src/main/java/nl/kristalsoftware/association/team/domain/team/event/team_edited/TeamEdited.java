package nl.kristalsoftware.association.team.domain.team.event.team_edited;

import lombok.Getter;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class TeamEdited implements BaseEvent<TeamEventData> {

    @Getter
    private final TeamEventData eventData;

    public TeamEdited(TeamEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
