package nl.kristalsoftware.association.team.domain.player.event.player_added_to_team;

import lombok.Builder;
import lombok.Getter;
import nl.kristalsoftware.association.team.domain.team.TeamReference;

@Builder
@Getter
public class PlayerAddedToTeamEventData {

    private TeamReference teamReference;

}
