package nl.kristalsoftware.association.team.datastore.types.team.messaging;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.datastore.base.messaging.EventConsumer;
import nl.kristalsoftware.datastore.base.messaging.offsetmanagement.TopicPartitionHandler;
import nl.kristalsoftware.domain.base.DomainEventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class TeamDomainEventConsumer extends EventConsumer<TeamEventData> {

    public TeamDomainEventConsumer(List<DomainEventHandler<TeamEventData>> eventList, TopicPartitionHandler topicPartitionHandler) {
        super(eventList, topicPartitionHandler);
    }

    @Transactional
    @KafkaListener(topics = "${team.kafka.team.topicname}") //, containerFactory = "teamKafkaListenerContainerFactory")
    public void consumeData(@Payload ConsumerRecord<String, TeamEventData> record) {
        log.info("Team: Key: {}, Value: {}, Partition: {}, Offset: {}",
                record.partition(), record.offset(), record.key(), record.value());
        super.consumeData(record, record.value().getDomainEventName());
    }

}
