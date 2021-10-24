package nl.kristalsoftware.association.team.datastore.types.player.eventstore.event.player_role_assigned;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.PlayerRole;
import nl.kristalsoftware.association.team.domain.player.event.player_role_assigned.PlayerRoleAssignedEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.eventstore.event.EventLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlayerRoleAssignedEventLoader implements EventLoader<Player, PlayerRoleAssignedEventEntity> {

    @Override
    public Class<? extends BaseEventEntity> appliesTo() {
        return PlayerRoleAssignedEventEntity.class;
    }

    @Override
    public void loadEventData(Player player, PlayerRoleAssignedEventEntity eventEntity) {
        log.info("PlayerRoleAssignedEvent: {} {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getRole());
        PlayerRoleAssignedEventData playerRoleAssignedEventData = PlayerRoleAssignedEventData.builder()
                .playerRole(PlayerRole.of(eventEntity.getRole()))
                .build();
        player.loadData(playerRoleAssignedEventData);
    }

}
