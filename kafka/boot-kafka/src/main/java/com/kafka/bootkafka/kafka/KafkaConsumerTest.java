package com.kafka.bootkafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author whh
 * @desc
 * @date 2020/12/31 11:21
 */
@Slf4j
@Component
public class KafkaConsumerTest {

    /**
     * topic名称
     */
    @Value("${spring.kafka.template.default-topic}")
    private String topicUser;

    public void consume() {
        Properties props = new Properties();
        // 必须设置的属性
        props.put("bootstrap.servers", "92.168.126.128:9092,192.168.126.129:9092,192.168.126.130:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "group-user");

        //根据上面的配置，新增消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topicUser));

        while (true) {
            //  从服务器开始拉取数据
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record -> {
                System.out.printf("成功消费消息：topic = %s ,partition = %d,offset = %d, key = %s, value = %s%n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
            });
        }

    }

}
