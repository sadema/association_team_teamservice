package nl.kristalsoftware.association.team.domain.player.event.player_moved_to_another_team;

import lombok.Builder;
import lombok.Getter;
import nl.kristalsoftware.association.team.domain.player.PlayerRole;
import nl.kristalsoftware.association.team.domain.team.TeamReference;

@Builder
@Getter
public class PlayerMovedToAnotherTeamEventData {

    private TeamReference teamReference;

}
