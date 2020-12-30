package com.producer.customize;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author whh
 * @desc
 * @date 2020/12/30 18:37
 */
public class ProducerWithPartitioner {

    public static void main(String[] args) {


        String topicName = "kafka-CustomPartitioner";

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.126.128:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

        //    传递自定义分区器
        properties.put("partitioner.class", "com.producer.customize.CustomPartitioner");
        //  传递分区器所需的参数
        properties.put("pass.line", 6);

        Producer<Integer, Integer> producer = new KafkaProducer<Integer, Integer>(properties);


        for (int i = 0; i < 10; i++) {
            String score = "score:" + i;
            ProducerRecord<Integer, Integer> record = new ProducerRecord<Integer, Integer>(topicName, i, i);
            try {
                Future<RecordMetadata> send = producer.send(record);
                RecordMetadata metadata = send.get();
                System.out.printf("topic=%s, partition=%d, offset=%s \n",
                        metadata.topic(), metadata.partition(), metadata.offset());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        producer.close();
    }

}
