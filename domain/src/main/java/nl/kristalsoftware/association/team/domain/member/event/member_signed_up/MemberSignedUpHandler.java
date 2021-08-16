package nl.kristalsoftware.association.team.domain.member.event.member_signed_up;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.registration.MemberEventData;
import nl.kristalsoftware.association.team.domain.player.PlayerService;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberSignedUpHandler implements DomainEventHandler<MemberEventData> {

    private final PlayerService playerService;

    @Override
    public String appliesTo() {
        return MemberSignedUp.class.getSimpleName();
    }

    @Override
    public void saveEvent(MemberEventData eventData) {
        playerService.signUpPlayer(eventData.getFirstName(), eventData.getLastName(), eventData.getBirthDate());
    }

}
