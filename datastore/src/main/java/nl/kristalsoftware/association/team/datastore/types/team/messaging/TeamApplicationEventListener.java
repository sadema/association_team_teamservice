package nl.kristalsoftware.association.team.datastore.types.team.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.datastore.base.messaging.GenericEventProducer;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TeamApplicationEventListener {

    private final GenericEventProducer<TeamEventData> eventProducer;

    private final KafkaTemplate<String, TeamEventData> kafkaTemplate;

    @Value("${team.kafka.team.topicname}")
    private String topicname;

    @EventListener
    public void onApplicationEvent(BaseEvent<TeamEventData> teamEvent) {
        eventProducer.produceEvent(
                kafkaTemplate,
                topicname,
                teamEvent.getEventData().getReference(),
                teamEvent.getEventData());
    }
}
