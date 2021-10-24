package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_moved_to_another_team;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.event.player_moved_to_another_team.PlayerMovedToAnotherTeamEventData;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.eventstore.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlayerMovedToAnotherTeamEventLoader implements EventLoader<Player, PlayerMovedToAnotherTeamEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return PlayerMovedToAnotherTeamEventEntity.class;
    }

    @Override
    public void loadEventData(Player player, PlayerMovedToAnotherTeamEventEntity eventEntity) {
        log.info("PlayerMovedToAnotherTeamEvent: {} {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getTeamReference());
        PlayerMovedToAnotherTeamEventData playerMovedToAnotherTeamEventData = PlayerMovedToAnotherTeamEventData.builder()
                .teamReference(TeamReference.of(eventEntity.getTeamReference()))
                .build();
        player.loadData(playerMovedToAnotherTeamEventData);
    }

}
