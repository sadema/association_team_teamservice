package nl.kristalsoftware.association.team.domain.team.event.team_registered;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.association.team.domain.team.TeamDataStore;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeamRegisteredHandler implements DomainEventHandler<TeamEventData> {

    private final TeamDataStore teamDataStore;

    @Override
    public String appliesTo() {
        return TeamRegistered.class.getSimpleName();
    }

    @Override
    public void saveEvent(TeamEventData eventData) {
        teamDataStore.saveTeamRegisteredEvent(eventData);
    }
}
