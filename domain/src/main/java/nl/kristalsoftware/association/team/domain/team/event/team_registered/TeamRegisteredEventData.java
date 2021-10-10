package nl.kristalsoftware.association.team.domain.team.event.team_registered;

import lombok.Builder;
import lombok.Getter;
import nl.kristalsoftware.association.team.domain.team.TeamCategory;
import nl.kristalsoftware.association.team.domain.team.TeamDescription;
import nl.kristalsoftware.association.team.domain.team.TeamName;

@Builder
@Getter
public class TeamRegisteredEventData {

    private TeamName teamName;

    private TeamCategory teamCategory;

    private TeamDescription teamDescription;

}
