package br.com.template.aplicacao.v1.example.messaging;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaExampleConsumer {

    @KafkaListener(containerFactory = "genericConsumerFactory",
            topics = {
                    "${kafka.example.consumer.topic}"
            },
            groupId = "template-java17")
    public void consume(ConsumerRecord<String, String> message) {
        log.info("consume >> param [{}]", message);
        log.info("consume << ");
    }
}
