package nl.kristalsoftware.association.team.domain.member.event.member_signed_up;

import lombok.Getter;
import nl.kristalsoftware.association.registration.MemberEventData;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.DomainEvent;

@DomainEvent
public class MemberSignedUp implements BaseEvent<MemberEventData> {

    @Getter
    private final MemberEventData eventData;

    public MemberSignedUp(MemberEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
