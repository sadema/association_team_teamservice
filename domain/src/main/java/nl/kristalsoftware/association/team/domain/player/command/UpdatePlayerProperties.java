package nl.kristalsoftware.association.team.domain.player.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class UpdatePlayerProperties {

    private String firstName;

    private String lastName;

    private Long birthDate;

}
