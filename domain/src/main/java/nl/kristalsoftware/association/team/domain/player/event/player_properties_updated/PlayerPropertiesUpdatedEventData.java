package nl.kristalsoftware.association.team.domain.player.event.player_properties_updated;

import lombok.Builder;
import lombok.Getter;
import nl.kristalsoftware.association.team.domain.player.PlayerBirthDate;
import nl.kristalsoftware.association.team.domain.player.PlayerName;

@Builder
@Getter
public class PlayerPropertiesUpdatedEventData {

    private PlayerName playerName;

    private PlayerBirthDate playerBirthDate;

}
