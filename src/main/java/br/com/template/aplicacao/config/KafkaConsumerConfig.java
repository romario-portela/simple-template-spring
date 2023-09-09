package br.com.template.aplicacao.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.KafkaHeaderMapper;
import org.springframework.util.backoff.FixedBackOff;

import java.time.Duration;

@Configuration
public class KafkaConsumerConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Value("${kafka.consumer.concurrency}")
    private Integer consumerConcurrency;

    @Value("${kafka.consumer.retries}")
    private Integer maxAttempts;

    @Value("${kafka.consumer.auto-startup}")
    private boolean autoStartup;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> genericConsumerFactory() {
        return listenerContainerFactory(consumerConcurrency);
    }

    private ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory(Integer concurrency) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setAutoStartup(autoStartup);
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.setErrorHandler(errorHandler());
        return factory;
    }

    private ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public SeekToCurrentErrorHandler errorHandler() {
        SeekToCurrentErrorHandler handler = new SeekToCurrentErrorHandler(
                new FixedBackOff(Duration.ofSeconds(15L).toMillis(), maxAttempts));
        handler.addNotRetryableExceptions(JsonProcessingException.class);
        return handler;
    }

    @Bean("kafkaBinderHeaderMapper")
    public KafkaHeaderMapper kafkaBinderHeaderMapper() {
        DefaultKafkaHeaderMapper mapper = new DefaultKafkaHeaderMapper();
        mapper.setEncodeStrings(true);
        return mapper;
    }

}
