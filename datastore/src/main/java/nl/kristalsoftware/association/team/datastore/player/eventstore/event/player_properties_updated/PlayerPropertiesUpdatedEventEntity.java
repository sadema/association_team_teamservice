package nl.kristalsoftware.association.team.datastore.player.eventstore.event.player_properties_updated;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "PlayerPropertiesUpdatedEvent")
public class PlayerPropertiesUpdatedEventEntity extends BaseEventEntity {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private PlayerPropertiesUpdatedEventEntity(
            UUID reference,
            String domainEventName,
            String firstName,
            String lastName
    ) {
        super(reference, domainEventName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static PlayerPropertiesUpdatedEventEntity of(PlayerEventData playerEventData) {
        PlayerPropertiesUpdatedEventEntity entity = new PlayerPropertiesUpdatedEventEntity(
                UUID.fromString(playerEventData.getReference()),
                playerEventData.getDomainEventName(),
                playerEventData.getFirstName(),
                playerEventData.getLastName()
        );
        entity.birthDate = entity.getLocalDateFromMillis(playerEventData.getBirthDate());
        return entity;
    }

}
