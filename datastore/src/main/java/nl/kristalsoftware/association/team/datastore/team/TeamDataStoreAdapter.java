package nl.kristalsoftware.association.team.datastore.team;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.association.team.datastore.team.eventstore.TeamEventStore;
import nl.kristalsoftware.association.team.datastore.team.eventstore.event.team_registered.TeamRegisteredEventEntity;
import nl.kristalsoftware.association.team.datastore.team.viewstore.TeamViewStore;
import nl.kristalsoftware.association.team.domain.team.Team;
import nl.kristalsoftware.association.team.domain.team.TeamDataStore;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TeamDataStoreAdapter implements TeamDataStore {

    private final TeamEventStore playerEventStore;

    private final TeamViewStore teamViewStore;

    @Override
    public Team loadAggregate(ApplicationEventPublisher eventPublisher) {
        return Team.of(TeamReference.of(UUID.randomUUID()), eventPublisher);
    }

    @Transactional
    @Override
    public void saveTeamRegisteredEvent(TeamEventData eventData) {
        TeamRegisteredEventEntity teamRegisteredEventEntity = TeamRegisteredEventEntity.of(eventData);
        playerEventStore.saveEventEntity(teamRegisteredEventEntity);
        teamViewStore.saveRegisteredTeam(eventData);
    }

}
