package nl.kristalsoftware.association.team.domain.player.event.player_role_assigned;

import lombok.Builder;
import lombok.Getter;
import nl.kristalsoftware.association.team.domain.player.PlayerRole;
import nl.kristalsoftware.association.team.domain.team.TeamReference;

@Builder
@Getter
public class PlayerRoleAssignedEventData {

    private PlayerRole playerRole;

}
