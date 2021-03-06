package nl.kristalsoftware.association.team.datastore.player.eventstore.event.player_moved_to_another_team;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "PlayerMovedToAnotherTeamEvent")
public class PlayerMovedToAnotherTeamEventEntity extends BaseEventEntity {

    private UUID teamReference;

    private PlayerMovedToAnotherTeamEventEntity(
            UUID reference,
            String domainEventName,
            UUID teamReference
    ) {
        super(reference, domainEventName);
        this.teamReference = teamReference;
    }

    public static PlayerMovedToAnotherTeamEventEntity of(PlayerEventData teamplayerEventData) {
        return new PlayerMovedToAnotherTeamEventEntity(
                UUID.fromString(teamplayerEventData.getReference()),
                teamplayerEventData.getDomainEventName(),
                UUID.fromString(teamplayerEventData.getTeamReference())
        );
    }

}
