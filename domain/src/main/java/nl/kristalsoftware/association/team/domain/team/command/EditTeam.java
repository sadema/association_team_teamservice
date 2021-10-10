package nl.kristalsoftware.association.team.domain.team.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.team.domain.team.TeamCategory;
import nl.kristalsoftware.association.team.domain.team.TeamDescription;
import nl.kristalsoftware.association.team.domain.team.TeamName;

@Data
@AllArgsConstructor(staticName = "of")
public class EditTeam {

    private TeamName teamName;

    private TeamCategory teamCategory;

    private TeamDescription teamDescription;

}
