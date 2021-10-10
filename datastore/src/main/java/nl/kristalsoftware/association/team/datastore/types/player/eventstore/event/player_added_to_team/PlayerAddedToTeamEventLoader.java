package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_added_to_team;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.event.player_added_to_team.PlayerAddedToTeamEventData;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlayerAddedToTeamEventLoader implements EventLoader<Player,PlayerAddedToTeamEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return PlayerAddedToTeamEventEntity.class;
    }

    @Override
    public void loadEventData(Player player, PlayerAddedToTeamEventEntity eventEntity) {
        log.info("PlayerAddedToTeamEvent: {} {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getTeamReference());
        PlayerAddedToTeamEventData playerAddedToTeamEventData = PlayerAddedToTeamEventData.builder()
                .teamReference(TeamReference.of(eventEntity.getTeamReference()))
                .build();
        player.loadData(playerAddedToTeamEventData);
    }

}
