package nl.kristalsoftware.association.team.datastore.types.team.eventstore.event.team_registered;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.team.Team;
import nl.kristalsoftware.association.team.domain.team.TeamCategory;
import nl.kristalsoftware.association.team.domain.team.TeamDescription;
import nl.kristalsoftware.association.team.domain.team.TeamName;
import nl.kristalsoftware.association.team.domain.team.event.team_registered.TeamRegisteredEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeamRegisteredEventLoader implements EventLoader<Team, TeamRegisteredEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return TeamRegisteredEventEntity.class;
    }

    @Override
    public void loadEventData(Team team, TeamRegisteredEventEntity eventEntity) {
        log.info("TeamRegisteredEvent: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName());
        TeamRegisteredEventData teamRegisteredEventData = TeamRegisteredEventData.builder()
                .teamName(TeamName.of(eventEntity.getName()))
                .teamCategory(TeamCategory.of(eventEntity.getCategory()))
                .teamDescription(TeamDescription.of(eventEntity.getDescription()))
                .build();
        team.loadData(teamRegisteredEventData);
    }

}
