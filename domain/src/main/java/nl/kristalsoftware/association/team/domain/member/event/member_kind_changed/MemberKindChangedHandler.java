package nl.kristalsoftware.association.team.domain.member.event.member_kind_changed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.PlayerService;
import nl.kristalsoftware.association.team.domain.player.command.SignUpPlayer;
import nl.kristalsoftware.association.team.domain.player.command.StopPlaying;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberKindChangedHandler implements DomainEventHandler<MemberEventData> {

    private final PlayerService playerService;

    @Override
    public String appliesTo() {
        return MemberKindChanged.class.getSimpleName();
    }

    @Override
    public void saveEvent(MemberEventData eventData) {
        Player player = playerService.getPlayer(eventData.getReference());
        if (memberBecomesPlayer(player, eventData.getKind()))  {
            player.handleCommand(SignUpPlayer.of(
                    eventData.getFirstName(),
                    eventData.getLastName(),
                    eventData.getBirthDate(),
                    eventData.getKind()));
        }
        else {
            if (playerStops(player, eventData.getKind())) {
                player.handleCommand(StopPlaying.of());
            }
        }
    }

    private Boolean isPlayer(String kind) {
        return kind.equals("PLAYER");
    }

    private Boolean isNotAPlayer(String kind) {
        return !isPlayer(kind);
    }

    private Boolean memberBecomesPlayer(Player player, String kind) {
        return isPlayer(kind) && player.notExists();
    }

    private Boolean playerStops(Player player, String kind) {
        return isNotAPlayer(kind) && player.exists();
    }

}
