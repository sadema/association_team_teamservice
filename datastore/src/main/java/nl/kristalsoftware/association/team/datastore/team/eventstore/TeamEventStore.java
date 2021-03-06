package nl.kristalsoftware.association.team.datastore.team.eventstore;

import nl.kristalsoftware.association.team.datastore.player.eventstore.EventStoreRepository;
import nl.kristalsoftware.association.team.domain.team.Team;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import nl.kristalsoftware.datastore.base.event.BaseEventEntity;
import nl.kristalsoftware.datastore.base.event.BaseEventStore;
import nl.kristalsoftware.datastore.base.event.EventLoader;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TeamEventStore extends BaseEventStore<Team> {

    private final EventStoreRepository eventStoreRepository;

    public TeamEventStore(
            EventStoreRepository eventStoreRepository,
            List<EventLoader> loaders) {
        super(loaders);
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    protected Iterable<BaseEventEntity> findAllByReference(Team aggregateRoot) {
        return eventStoreRepository.findAllByReference(aggregateRoot.getReference().getValue());
    }

    @Override
    protected Team createAggregateRoot(UUID teamReference, ApplicationEventPublisher applicationEventPublisher) {
        return Team.of(TeamReference.of(teamReference), applicationEventPublisher);
    }

    @Override
    public <T extends BaseEventEntity> void saveEventEntity(T eventEntity) {
        eventStoreRepository.save(eventEntity);
    }

}
