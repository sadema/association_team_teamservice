package nl.kristalsoftware.association.team.datastore.types.player.eventstore;

import nl.kristalsoftware.association.team.domain.player.Player;
import nl.kristalsoftware.association.team.domain.player.PlayerReference;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.event.BaseEventStore;
import nl.kristalsoftware.datastore.base.event.EventLoader;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PlayerEventStore extends BaseEventStore<Player> {

    private final EventStoreRepository eventStoreRepository;

    public PlayerEventStore(
            EventStoreRepository eventStoreRepository,
            List<EventLoader> loaders) {
        super(loaders);
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    protected Iterable<BaseEventEntity> findAllByReference(Player aggregateRoot) {
        return eventStoreRepository.findAllByReference(aggregateRoot.getReference().getValue());
    }

    @Override
    protected Player createAggregateRoot(UUID playerReference, ApplicationEventPublisher applicationEventPublisher) {
        return Player.of(PlayerReference.of(playerReference), applicationEventPublisher);
    }

    public Player loadAggregate(PlayerReference playerReference, ApplicationEventPublisher eventPublisher) {
        return loadEvents(playerReference.getValue(), eventPublisher);
    }

    @Override
    public <T extends BaseEventEntity> void saveEventEntity(T eventEntity) {
        eventStoreRepository.save(eventEntity);
    }

}
