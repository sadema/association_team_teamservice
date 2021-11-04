package nl.kristalsoftware.association.team.domain.player.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class SignUpPlayer {

    private String firstName;

    private String lastName;

    private Long birthDate;

    private String kind;

}
