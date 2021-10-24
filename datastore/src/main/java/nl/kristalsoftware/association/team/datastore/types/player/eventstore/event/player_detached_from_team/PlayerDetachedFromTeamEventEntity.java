package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_detached_from_team;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "PlayerDetachedFromTeamEvent")
public class PlayerDetachedFromTeamEventEntity extends BaseEventEntity {

    private PlayerDetachedFromTeamEventEntity(
            UUID reference,
            String domainEventName
    ) {
        super(reference, domainEventName);
    }

    public static PlayerDetachedFromTeamEventEntity of(PlayerEventData teamplayerEventData) {
        return new PlayerDetachedFromTeamEventEntity(
                UUID.fromString(teamplayerEventData.getReference()),
                teamplayerEventData.getDomainEventName()
        );
    }

}
