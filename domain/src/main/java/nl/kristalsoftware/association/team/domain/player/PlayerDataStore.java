package nl.kristalsoftware.association.team.domain.player;

import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.domain.base.annotations.DomainRepository;
import org.springframework.context.ApplicationEventPublisher;

@DomainRepository
public interface PlayerDataStore {

    Player loadAggregate(ApplicationEventPublisher eventPublisher);

    Player loadAggregate(PlayerReference playerReference, ApplicationEventPublisher eventPublisher);

    void savePlayerAddedToTeamEvent(PlayerEventData eventData);

    void savePlayerSignedUpEvent(PlayerEventData eventData);

    void savePlayerMovedToAnotherTeamEvent(PlayerEventData eventData);

    void savePlayerRoleAssignedEvent(PlayerEventData eventData);

    void savePlayerPropertiesUpdatedEvent(PlayerEventData eventData);

}
