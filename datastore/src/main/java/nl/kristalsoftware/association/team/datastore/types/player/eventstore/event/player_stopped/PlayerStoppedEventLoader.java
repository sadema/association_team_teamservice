package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_stopped;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.event.player_stopped.PlayerStoppedEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.eventstore.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlayerStoppedEventLoader implements EventLoader<Player, PlayerStoppedEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return PlayerStoppedEventEntity.class;
    }

    @Override
    public void loadEventData(Player player, PlayerStoppedEventEntity eventEntity) {
        log.info("PlayerStoppedEvent: {} {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName());
        PlayerStoppedEventData playerStoppedEventData = PlayerStoppedEventData.builder()
                .build();
        player.loadData(playerStoppedEventData);
    }

}
