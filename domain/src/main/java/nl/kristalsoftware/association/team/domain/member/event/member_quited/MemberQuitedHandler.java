package nl.kristalsoftware.association.team.domain.member.event.member_quited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.team.domain.player.PlayerService;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberQuitedHandler implements DomainEventHandler<MemberEventData> {

    private final PlayerService playerService;

    @Override
    public String appliesTo() {
        return MemberQuited.class.getSimpleName();
    }

    @Override
    public void saveEvent(MemberEventData eventData) {
        if (eventData.getKind().equals("PLAYER")) {
            playerService.stopPlaying(eventData.getReference(), eventData.getKind());
        }
    }

}
