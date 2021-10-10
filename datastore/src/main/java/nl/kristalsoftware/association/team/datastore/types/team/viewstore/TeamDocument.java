package nl.kristalsoftware.association.team.datastore.types.team.viewstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.association.team.datastore.DocumentType;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class TeamDocument {

    private String _id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String _rev;

    private String type;

    private String teamName;

    private String teamCategory;

    private String teamDescription;

    public static TeamDocument of(TeamEventData teamEventData) {
        return new TeamDocument(
                teamEventData.getReference(),
                null,
                DocumentType.TEAM.toString(),
                teamEventData.getName(),
                teamEventData.getCategory(),
                teamEventData.getDescription()
        );
    }

}
