package br.com.template.aplicacao.v1.example.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaExampleProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendKafkaIntegration(Object message, String topic) throws JsonProcessingException {
        var ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var jsonMessage = ow.writeValueAsString(message);

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, jsonMessage);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult result) {
                log.info("Sent integration to topic " + topic + " with  message=[" + jsonMessage + "] and with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send integration to topic " + topic + " with message=[" + jsonMessage + "] due to : " + ex.getMessage());
            }
        });
    }
}
