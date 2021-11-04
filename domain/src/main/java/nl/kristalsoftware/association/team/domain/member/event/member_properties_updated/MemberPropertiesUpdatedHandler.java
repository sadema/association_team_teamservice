package nl.kristalsoftware.association.team.domain.member.event.member_properties_updated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.team.domain.player.PlayerService;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberPropertiesUpdatedHandler implements DomainEventHandler<MemberEventData> {

    private final PlayerService playerService;

    @Override
    public String appliesTo() {
        return MemberPropertiesUpdated.class.getSimpleName();
    }

    @Override
    public void saveEvent(MemberEventData eventData) {
        if (hasPlayerPropertiesUpdated(eventData)) {
            playerService.updatePlayerProperties(eventData.getReference(), eventData.getFirstName(), eventData.getLastName(), eventData.getBirthDate());
        }
    }

    private boolean hasPlayerPropertiesUpdated(MemberEventData eventData) {
        if (eventData.getFirstName() != null ||
                eventData.getLastName() != null ||
                eventData.getBirthDate() != null
        ) {
            return true;
        }
        return false;
    }

}
