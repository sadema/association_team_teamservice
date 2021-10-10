package nl.kristalsoftware.association.team.datastore.types.team.eventstore.event.team_edited;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.team.Team;
import nl.kristalsoftware.association.team.domain.team.TeamCategory;
import nl.kristalsoftware.association.team.domain.team.TeamDescription;
import nl.kristalsoftware.association.team.domain.team.TeamName;
import nl.kristalsoftware.association.team.domain.team.event.team_edited.TeamEditedEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeamEditedEventLoader implements EventLoader<Team, TeamEditedEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return TeamEditedEventEntity.class;
    }

    @Override
    public void loadEventData(Team team, TeamEditedEventEntity eventEntity) {
        log.info("TeamEditedEvent: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName());
        TeamEditedEventData teamEditedEventData = TeamEditedEventData.builder()
                .teamName(TeamName.of(eventEntity.getName()))
                .teamCategory(TeamCategory.of(eventEntity.getCategory()))
                .teamDescription(TeamDescription.of(eventEntity.getDescription()))
                .build();
        team.loadData(teamEditedEventData);
    }

}
