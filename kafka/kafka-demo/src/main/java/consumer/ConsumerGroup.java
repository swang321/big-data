package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author whh
 * @desc Kafka消费者和消费者组
 * @date 2020/12/31 9:40
 */
public class ConsumerGroup {


    public static void main(String[] args) {

//        String topic = "access";
        String group = "group-user";
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.126.128:9092,192.168.126.129:9092,192.168.126.130:9092");
        //  指定分组ID
        properties.put("group.id", group);
        properties.put("enable.auto.commit", true);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        List<String> list = new ArrayList<>();
        list.add("access");
        list.add("ugchead");
        list.add("ugctail");
        consumer.subscribe(list);


        try {
            while (true) {
                //  轮询获取数据
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("topic = %s,partition = %d, key = %s, value = %s, offset = %d,\n",
                            record.topic(), record.partition(), record.key(), record.value(), record.offset());
                }
            }
        } finally {
            consumer.close();
        }

    }


}
