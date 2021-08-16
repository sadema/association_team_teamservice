package nl.kristalsoftware.association.team.domain.member.event.member_properties_updated;

import lombok.Getter;
import nl.kristalsoftware.association.registration.MemberEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class MemberPropertiesUpdated implements BaseEvent<MemberEventData> {

    @Getter
    private final MemberEventData eventData;

    public MemberPropertiesUpdated(MemberEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
