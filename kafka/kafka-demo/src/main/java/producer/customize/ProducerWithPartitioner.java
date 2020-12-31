package producer.customize;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @author whh
 * @desc        自定义分区器
 * @date 2020/12/30 18:37
 */
public class ProducerWithPartitioner {

    public static void main(String[] args) {

        //  kafka-topics.sh --create --zookeeper master:2181,slave1:2181,slave2:2181 --replication-factor 1 --partitions 2 --topic kafka-test

        String topicName = "kafka-test";

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.126.128:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //    传递自定义分区器
        properties.put("partitioner.class", "com.producer.customize.CustomPartitioner");
        //  传递分区器所需的参数
        properties.put("pass.line", 6);

        Producer<Integer, String> producer = new KafkaProducer<Integer, String>(properties);


        for (int i = 0; i < 10; i++) {
            final String score = "score:" + i;

            ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(topicName, i, score);

            /*异步发送消息*/
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.printf("%s, partition=%d, \n", score, metadata.partition());
                }
            });


        }

        producer.close();
    }

}
