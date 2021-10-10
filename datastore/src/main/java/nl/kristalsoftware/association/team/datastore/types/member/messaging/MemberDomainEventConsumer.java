package nl.kristalsoftware.association.team.datastore.types.member.messaging;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.registration.MemberEventData;
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
public class MemberDomainEventConsumer extends EventConsumer<MemberEventData> {

    public MemberDomainEventConsumer(List<DomainEventHandler<MemberEventData>> eventList, TopicPartitionHandler topicPartitionHandler) {
        super(eventList, topicPartitionHandler);
    }

    @Transactional
    @KafkaListener(topics = "${team.kafka.member.topicname}") //, containerFactory = "memberKafkaListenerContainerFactory")
    public void consumeData(@Payload ConsumerRecord<String, MemberEventData> record) {
        log.info("Player: Key: {}, Value: {}, Partition: {}, Offset: {}",
                record.partition(), record.offset(), record.key(), record.value());
        super.consumeData(record, record.value().getDomainEventName());
    }

}
