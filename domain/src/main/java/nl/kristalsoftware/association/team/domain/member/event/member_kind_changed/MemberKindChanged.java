package nl.kristalsoftware.association.team.domain.member.event.member_kind_changed;

import lombok.Getter;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class MemberKindChanged implements BaseEvent<MemberEventData> {

    @Getter
    private final MemberEventData eventData;

    public MemberKindChanged(MemberEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
