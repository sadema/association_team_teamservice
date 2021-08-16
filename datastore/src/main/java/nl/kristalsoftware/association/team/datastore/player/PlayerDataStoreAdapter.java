package nl.kristalsoftware.association.team.datastore.player;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.datastore.player.eventstore.PlayerEventStore;
import nl.kristalsoftware.association.team.datastore.player.eventstore.event.player_added_to_team.PlayerAddedToTeamEventEntity;
import nl.kristalsoftware.association.team.datastore.player.eventstore.event.player_moved_to_another_team.PlayerMovedToAnotherTeamEventEntity;
import nl.kristalsoftware.association.team.datastore.player.eventstore.event.player_role_assigned.PlayerRoleAssignedEventEntity;
import nl.kristalsoftware.association.team.datastore.player.eventstore.event.player_signed_up.PlayerSignedUpEventEntity;
import nl.kristalsoftware.association.team.datastore.player.viewstore.PlayerViewStore;
import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.PlayerReference;
import nl.kristalsoftware.association.team.domain.player.PlayerDataStore;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PlayerDataStoreAdapter implements PlayerDataStore {

    private final PlayerEventStore playerEventStore;

    private final PlayerViewStore playerViewStore;

    @Override
    public Player loadAggregate(ApplicationEventPublisher eventPublisher) {
        return Player.of(PlayerReference.of(UUID.randomUUID()), eventPublisher);
    }

    @Override
    public Player loadAggregate(PlayerReference playerReference, ApplicationEventPublisher eventPublisher) {
        return playerEventStore.loadAggregate(playerReference, eventPublisher);
    }

    @Transactional
    @Override
    public void savePlayerSignedUpEvent(PlayerEventData eventData) {
        PlayerSignedUpEventEntity playerSignedUpEventEntity = PlayerSignedUpEventEntity.of(eventData);
        playerEventStore.saveEventEntity(playerSignedUpEventEntity);
        playerViewStore.playerSignedUp(eventData);
    }

    @Transactional
    @Override
    public void savePlayerAddedToTeamEvent(PlayerEventData eventData) {
        PlayerAddedToTeamEventEntity playerAddedToTeamEventEntity = PlayerAddedToTeamEventEntity.of(eventData);
        playerEventStore.saveEventEntity(playerAddedToTeamEventEntity);
        playerViewStore.playerAddedToTeam(eventData);
    }

    @Transactional
    @Override
    public void savePlayerMovedToAnotherTeamEvent(PlayerEventData eventData) {
        PlayerMovedToAnotherTeamEventEntity playerMovedToAnotherTeamEntity = PlayerMovedToAnotherTeamEventEntity.of(eventData);
        playerEventStore.saveEventEntity(playerMovedToAnotherTeamEntity);
        playerViewStore.playerMovedToAnotherTeam(eventData);
    }

    @Transactional
    @Override
    public void savePlayerRoleAssignedEvent(PlayerEventData eventData) {
        PlayerRoleAssignedEventEntity playerRoleAssignedEventEntity = PlayerRoleAssignedEventEntity.of(eventData);
        playerEventStore.saveEventEntity(playerRoleAssignedEventEntity);
        playerViewStore.playerRoleAssigned(eventData);
    }

    @Override
    public void savePlayerPropertiesUpdatedEvent(PlayerEventData eventData) {

    }

}
