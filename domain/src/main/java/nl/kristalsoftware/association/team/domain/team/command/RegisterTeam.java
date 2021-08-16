package nl.kristalsoftware.association.team.domain.team.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class RegisterTeam {

    private String teamName;

    private String teamCategory;

    private String teamDescription;

}
