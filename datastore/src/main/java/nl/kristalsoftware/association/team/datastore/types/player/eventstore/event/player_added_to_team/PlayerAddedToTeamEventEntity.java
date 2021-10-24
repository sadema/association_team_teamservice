package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_added_to_team;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "PlayerAddedToTeamEvent")
public class PlayerAddedToTeamEventEntity extends BaseEventEntity {

    private UUID teamReference;

    private PlayerAddedToTeamEventEntity(
            UUID reference,
            String domainEventName,
            UUID teamReference
    ) {
        super(reference, domainEventName);
        this.teamReference = teamReference;
    }

    public static PlayerAddedToTeamEventEntity of(PlayerEventData teamplayerEventData) {
        return new PlayerAddedToTeamEventEntity(
                UUID.fromString(teamplayerEventData.getReference()),
                teamplayerEventData.getDomainEventName(),
                UUID.fromString(teamplayerEventData.getTeamReference())
        );
    }

}
