package nl.kristalsoftware.association.team.datastore.types.team.eventstore.event.team_edited;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "TeamEditedEvent")
public class TeamEditedEventEntity extends BaseEventEntity {

    private String name;

    private String category;

    private String description;

    public TeamEditedEventEntity(UUID reference, String domainEventName, String name, String category, String description) {
        super(reference, domainEventName);
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public static TeamEditedEventEntity of(TeamEventData eventData) {
        return new TeamEditedEventEntity(
                UUID.fromString(eventData.getReference()),
                eventData.getDomainEventName(),
                eventData.getName(),
                eventData.getCategory(),
                eventData.getDescription()
        );
    }
}
