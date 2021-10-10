package nl.kristalsoftware.association.team.datastore.types.player.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.datastore.base.messaging.GenericEventProducer;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PlayerApplicationEventListener {

    private final GenericEventProducer<PlayerEventData> eventProducer;

    private final KafkaTemplate<String, PlayerEventData> kafkaTemplate;

    @Value("${team.kafka.player.topicname}")
    private String topicname;

    @EventListener
    public void onApplicationEvent(BaseEvent<PlayerEventData> playerEvent) {
        eventProducer.produceEvent(
                kafkaTemplate,
                topicname,
                playerEvent.getEventData().getReference(),
                playerEvent.getEventData());
    }
}
