package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_stopped;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "PlayerStoppedEvent")
public class PlayerStoppedEventEntity extends BaseEventEntity {

    private PlayerStoppedEventEntity(
            UUID reference,
            String domainEventName
    ) {
        super(reference, domainEventName);
    }

    public static PlayerStoppedEventEntity of(PlayerEventData teamplayerEventData) {
        return new PlayerStoppedEventEntity(
                UUID.fromString(teamplayerEventData.getReference()),
                teamplayerEventData.getDomainEventName()
        );
    }

}
