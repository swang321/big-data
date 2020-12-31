package producer.customize;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author whh
 * @desc 自定义分区器
 * @date 2020/12/30 18:33
 */
public class CustomPartitioner implements Partitioner {

    private int passLine;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //  key 值为分数，当分数大于分数线时候，分配到 1 分区，否则分配到 0 分区
        return (Integer) key >= passLine ? 1 : 0;
    }

    @Override
    public void close() {
        System.out.println("分区器关闭");
    }

    @Override
    public void configure(Map<String, ?> map) {
        // 从生产者配置中获取分数线
        passLine = (Integer) map.get("pass.line");
    }
}
