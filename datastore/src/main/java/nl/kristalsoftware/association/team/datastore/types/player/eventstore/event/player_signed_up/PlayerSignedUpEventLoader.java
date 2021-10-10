package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_signed_up;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.PlayerBirthDate;
import nl.kristalsoftware.association.team.domain.player.PlayerName;
import nl.kristalsoftware.association.team.domain.player.event.player_signed_up.PlayerSignedUpEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlayerSignedUpEventLoader implements EventLoader<Player, PlayerSignedUpEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return PlayerSignedUpEventEntity.class;
    }

    @Override
    public void loadEventData(Player player, PlayerSignedUpEventEntity eventEntity) {
        log.info("PlayerSignedUpEvent: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getFirstName() + " " + eventEntity.getLastName());
        PlayerSignedUpEventData playerSignedUpEventData = PlayerSignedUpEventData.builder()
                .playerName(PlayerName.of(eventEntity.getFirstName(), eventEntity.getLastName()))
                .playerBirthDate(PlayerBirthDate.of(eventEntity.getBirthDate()))
                .build();
        player.loadData(playerSignedUpEventData);
    }

}
