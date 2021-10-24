package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_signed_up;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "PlayerSignedUpEvent")
public class PlayerSignedUpEventEntity extends BaseEventEntity {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private PlayerSignedUpEventEntity(
            UUID reference,
            String domainEventName,
            String firstName,
            String lastName
    ) {
        super(reference, domainEventName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public static PlayerSignedUpEventEntity of(PlayerEventData playerEventData) {
        PlayerSignedUpEventEntity entity = new PlayerSignedUpEventEntity(
                UUID.fromString(playerEventData.getReference()),
                playerEventData.getDomainEventName(),
                playerEventData.getFirstName(),
                playerEventData.getLastName()
        );
        entity.birthDate = entity.getLocalDateFromMillis(playerEventData.getBirthDate());
        return entity;
    }

}
