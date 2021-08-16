package nl.kristalsoftware.association.team.domain.team;

import nl.kristalsoftware.association.team.TeamEventData;
import org.springframework.context.ApplicationEventPublisher;

public interface TeamDataStore {

    Team loadAggregate(ApplicationEventPublisher eventPublisher);

    void saveTeamRegisteredEvent(TeamEventData eventData);

}
