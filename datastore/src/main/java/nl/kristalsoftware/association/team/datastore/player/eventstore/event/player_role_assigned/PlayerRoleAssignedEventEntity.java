package nl.kristalsoftware.association.team.datastore.player.eventstore.event.player_role_assigned;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "PlayerRoleAssignedEvent")
public class PlayerRoleAssignedEventEntity extends BaseEventEntity {

    private String role;

    private PlayerRoleAssignedEventEntity(
            UUID reference,
            String domainEventName,
            String role
    ) {
        super(reference, domainEventName);
        this.role = role;
    }

    public static PlayerRoleAssignedEventEntity of(PlayerEventData teamplayerEventData) {
        return new PlayerRoleAssignedEventEntity(
                UUID.fromString(teamplayerEventData.getReference()),
                teamplayerEventData.getDomainEventName(),
                teamplayerEventData.getRole()
        );
    }

}
