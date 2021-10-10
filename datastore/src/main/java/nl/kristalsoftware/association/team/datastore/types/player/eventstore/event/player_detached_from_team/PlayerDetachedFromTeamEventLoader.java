package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_detached_from_team;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.event.player_detached_from_team.PlayerDetachedFromTeamEventData;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlayerDetachedFromTeamEventLoader implements EventLoader<Player, PlayerDetachedFromTeamEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return PlayerDetachedFromTeamEventEntity.class;
    }

    @Override
    public void loadEventData(Player player, PlayerDetachedFromTeamEventEntity eventEntity) {
        log.info("PlayerDetachedFromTeamEvent: {} {}", eventEntity.getReference(), eventEntity.getDomainEventName());
        PlayerDetachedFromTeamEventData playerDetachedFromTeamEventData = PlayerDetachedFromTeamEventData.builder()
                .build();
        player.loadData(playerDetachedFromTeamEventData);
    }

}
