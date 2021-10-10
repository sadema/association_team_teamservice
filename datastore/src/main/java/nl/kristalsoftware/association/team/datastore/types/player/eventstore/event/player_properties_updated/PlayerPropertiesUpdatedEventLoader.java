package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_properties_updated;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.PlayerBirthDate;
import nl.kristalsoftware.association.team.domain.player.PlayerName;
import nl.kristalsoftware.association.team.domain.player.event.player_properties_updated.PlayerPropertiesUpdatedEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlayerPropertiesUpdatedEventLoader implements EventLoader<Player, PlayerPropertiesUpdatedEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return PlayerPropertiesUpdatedEventEntity.class;
    }

    @Override
    public void loadEventData(Player player, PlayerPropertiesUpdatedEventEntity eventEntity) {
        log.info("PlayerPropertiesUpdatedEvent: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getFirstName() + " " + eventEntity.getLastName());
        PlayerPropertiesUpdatedEventData playerPropertiesUpdatedEventData = PlayerPropertiesUpdatedEventData.builder()
                .playerName(PlayerName.of(eventEntity.getFirstName(), eventEntity.getLastName()))
                .playerBirthDate(PlayerBirthDate.of(eventEntity.getBirthDate()))
                .build();
        player.loadData(playerPropertiesUpdatedEventData);
    }

}
