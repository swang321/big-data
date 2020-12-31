package producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author whh
 * @desc 向主题发送消息
 * @date 2020/12/30 18:06
 */
public class SimProducer {

    public static void main(String[] args) {

        String topicName = "Hello-Kafka";

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.126.128:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);

        sent(topicName, producer);
        sentOne(topicName, producer);
        sentTwo(topicName, producer);

        producer.close();
    }

    /**
     * 向主题发送消息
     */
    private static void sent(String topicName, Producer<String, String> producer) {

        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, "hello" + "word" + i);
            producer.send(record);
        }
    }

    /**
     * 同步发送消息
     */
    private static void sentOne(String topicName, Producer<String, String> producer) {
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, "k" + i, "world" + i);
            /*同步发送消息*/
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.printf("topic=%s, partition=%d, offset=%s \n",
                        metadata.topic(), metadata.partition(), metadata.offset());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 异步发送
     */
    private static void sentTwo(String topicName, Producer<String, String> producer) {
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, "k" + i, "world" + i);

            /*异步发送*/
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null) {
                        System.out.println("异常处理");
                    } else {
                        System.out.printf("topic=%s, partition=%d, offset=%s \n",
                                recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                    }
                }
            });

        }
    }

}
