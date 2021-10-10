~/Documents/develop/confluent-kafka/confluent-5.2.1/bin/kafka-avro-console-producer \
 --topic public.association.memberservice.member \
 --broker-list localhost:9092 \
 --property key.schema='{"type":"string"}' \
 --property schema.registry.url="http://127.0.0.1:8181" \
 --property value.schema="$(< ../../domain/src/main/resources/avro/MemberEventData.avsc)" \
 --property parse.key=true \
 --property key.separator=":"
