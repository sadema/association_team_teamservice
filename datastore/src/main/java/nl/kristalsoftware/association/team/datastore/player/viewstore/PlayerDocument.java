package nl.kristalsoftware.association.team.datastore.player.viewstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.datastore.DocumentType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDocument {

    private String _id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String _rev;

    private String type;

    private String firstName;

    private String lastName;

    private Long birthDate;

    private String team_reference;

    private String role;

    public static PlayerDocument of(PlayerEventData playerEventData) {
        return new PlayerDocument(
                playerEventData.getReference(),
                null,
                DocumentType.PLAYER.toString(),
                playerEventData.getFirstName(),
                playerEventData.getLastName(),
                playerEventData.getBirthDate(),
                null,
                null
        );
    }

}
