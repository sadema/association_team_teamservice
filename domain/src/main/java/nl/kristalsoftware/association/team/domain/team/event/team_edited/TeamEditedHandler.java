package nl.kristalsoftware.association.team.domain.team.event.team_edited;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.association.team.domain.team.TeamDataStore;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeamEditedHandler implements DomainEventHandler<TeamEventData> {

    private final TeamDataStore teamDataStore;

    @Override
    public String appliesTo() {
        return TeamEdited.class.getSimpleName();
    }

    @Override
    public void saveEvent(TeamEventData eventData) {
        teamDataStore.saveTeamEditedEvent(eventData);
    }
}
