//package com.storm.stormhbase.main;
//
//import com.storm.stormhbase.controller.CountBolt;
//import com.storm.stormhbase.controller.DataSourceSpout;
//import com.storm.stormhbase.controller.SplitBolt;
//import org.apache.storm.Config;
//import org.apache.storm.LocalCluster;
//import org.apache.storm.hbase.bolt.HBaseBolt;
//import org.apache.storm.hbase.bolt.mapper.SimpleHBaseMapper;
//import org.apache.storm.topology.TopologyBuilder;
//import org.apache.storm.tuple.Fields;
//
//import java.util.HashMap;
//
///**
// * @author whh
// * @desc
// * @date 2020/12/29 15:26
// */
//public class WordCountToHBaseApp {
//
//    private static final String DATA_SOURCE_SPOUT = "dataSourceSpout";
//    private static final String SPLIT_BOLT = "splitBolt";
//    private static final String COUNT_BOLT = "countBolt";
//    private static final String HBASE_BOLT = "hbaseBolt";
//
//    public static void main(String[] args) {
//
//        // storm的配置
//        Config config = new Config();
//
//        // HBase的配置
//        HashMap<String, Object> hbConfig = new HashMap<>(16);
//        hbConfig.put("hbase.rootdir", "hdfs://192.168.126.128:9000/hbase");
//        hbConfig.put("hbase.zookeeper.quorum", "192.168.126.128,192.168.126.129,192.168.126.130");
//        hbConfig.put("zookeeper.znode.parent", "/hbase");
//
//        // 将HBase的配置传入Storm的配置中
//        config.put("hbase.conf", hbConfig);
//
//        // 定义流数据与HBase中数据的映射
//        SimpleHBaseMapper mapper = new SimpleHBaseMapper()
//                .withRowKeyField("word")
//                .withColumnFields(new Fields("word", "count"))
//                .withColumnFamily("info");
//
//        //  给HBaseBolt传入表名、数据映射关系、和HBase的配置信息
//        //  表需要预先创建: create 'WordCount','info'
//        HBaseBolt hBaseBolt = new HBaseBolt("WordCount", mapper)
//                .withConfigKey("hbase.conf");
//
//        // 构建Topology
//        TopologyBuilder builder = new TopologyBuilder();
//
//        builder.setSpout(DATA_SOURCE_SPOUT, new DataSourceSpout(), 1);
//        builder.setBolt(SPLIT_BOLT, new SplitBolt(), 1).shuffleGrouping(DATA_SOURCE_SPOUT);
//        builder.setBolt(COUNT_BOLT, new CountBolt(), 1).shuffleGrouping(SPLIT_BOLT);
//        // save to HBase
//        builder.setBolt(HBASE_BOLT, hBaseBolt, 1).shuffleGrouping(COUNT_BOLT);
//
//
////        // 如果外部传参cluster则代表线上环境启动,否则代表本地启动
////        if (args.length > 0 && args[0].equals("cluster")) {
////            try {
////                StormSubmitter.submitTopology("WordCountToHBaseApp", config, builder.createTopology());
////            } catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
////                e.printStackTrace();
////            }
////        } else {
////            // 创建本地集群
////            LocalCluster cluster = new LocalCluster();
////            cluster.submitTopology("WordCountToHBaseApp",
////                    config, builder.createTopology());
////        }
//
//        // 创建本地集群
//        LocalCluster cluster = new LocalCluster();
//        cluster.submitTopology("WordCountToHBaseApp",
//                config, builder.createTopology());
//
//    }
//
//}
