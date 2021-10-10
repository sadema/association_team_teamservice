package nl.kristalsoftware.association.team.datastore.types.team.eventstore.event.team_registered;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "TeamRegisteredEvent")
public class TeamRegisteredEventEntity extends BaseEventEntity {

    private String name;

    private String category;

    private String description;

    public TeamRegisteredEventEntity(UUID reference, String domainEventName, String name, String category, String description) {
        super(reference, domainEventName);
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public static TeamRegisteredEventEntity of(TeamEventData eventData) {
        return new TeamRegisteredEventEntity(
                UUID.fromString(eventData.getReference()),
                eventData.getDomainEventName(),
                eventData.getName(),
                eventData.getCategory(),
                eventData.getDescription()
        );
    }
}
