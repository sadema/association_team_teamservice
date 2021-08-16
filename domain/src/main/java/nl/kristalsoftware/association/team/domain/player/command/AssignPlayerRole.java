package nl.kristalsoftware.association.team.domain.player.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.team.domain.player.PlayerRole;
import nl.kristalsoftware.association.team.domain.team.TeamReference;

@Data
@AllArgsConstructor
public class AssignPlayerRole {

    private PlayerRole playerRole;

}
