package nl.kristalsoftware.association.team.domain.member.event.member_quited;

import lombok.Getter;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class MemberQuited implements BaseEvent<MemberEventData> {

    @Getter
    private final MemberEventData eventData;

    public MemberQuited(MemberEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
