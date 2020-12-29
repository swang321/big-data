package com.storm.stormdemo.controller;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @author whh
 * @desc  启动 `WordCountApp` 的 main 方法即可运行，采用本地模式 Storm 会自动在本地搭建一个集群
 * @date 2020/12/28 16:40
 */
public class LocalWordCountApp {

    public static void main(String[] args) {


        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("DataSourceSpout", new DataSourceSpout());

        // 指明将 DataSourceSpout 的数据发送到 SplitBolt 中处理
        builder.setBolt("SplitBolt", new SplitBolt()).shuffleGrouping("DataSourceSpout");

        //  指明将 SplitBolt 的数据发送到 CountBolt 中 处理
        builder.setBolt("CountBolt", new CountBolt()).shuffleGrouping("SplitBolt");

        // 创建本地集群用于测试 这种模式不需要本机安装 storm,直接运行该 Main 方法即可
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("LocalWordCountApp",
                new Config(), builder.createTopology());
//        控制台输出
//        当前实时统计结果:Storm:5; Hive:2; Hadoop:3; Spark:4; Flink:5; HBase:5;
//        当前实时统计结果:Storm:5; Hive:2; Hadoop:3; Spark:5; Flink:5; HBase:5;
//        当前实时统计结果:Storm:5; Hive:2; Hadoop:4; Spark:5; Flink:5; HBase:5;
//        当前实时统计结果:Storm:5; Hive:2; Hadoop:4; Spark:5; Flink:6; HBase:5;
//        当前实时统计结果:Storm:5; Hive:2; Hadoop:4; Spark:5; Flink:6; HBase:6;
//        当前实时统计结果:Storm:5; Hive:3; Hadoop:4; Spark:5; Flink:6; HBase:6;
    }
}
