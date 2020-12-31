package com.kafka.bootkafka.kafka;

import com.google.gson.GsonBuilder;
import com.kafka.bootkafka.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author whh
 * @desc
 * @date 2020/12/31 11:05
 */
@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topicUser;

    public void sendMsg() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");

        User user= User.builder()
                .name("WHH")
                .age("18")
                .build();

        String message = builder.create().toJson(user);
        kafkaTemplate.send(topicUser, message);
        log.info("生产消息至Kafka:{}", message);
    }
}
