package com.storm.stormhdfs.mainapp;

import com.storm.stormhdfs.controller.CountBolt;
import com.storm.stormhdfs.controller.DataSourceSpout;
import com.storm.stormhdfs.controller.SplitBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileSizeRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @author whh
 * @desc
 * @date 2020/12/29 10:52
 */
public class DataToHdfsApp {

    private static final String DATA_SOURCE_SPOUT = "dataSourceSpout";

    private static final String SPLIT_BOLT = "splitBolt";
    private static final String COUNT_BOLT = "countBolt";
    private static final String HDFS_BOLT = "hdfsBolt";

    public static void main(String[] args) {

        // 指定Hadoop的用户名 如果不指定,则在HDFS创建目录时候有可能抛出无权限的异常(RemoteException: Permission denied)
        System.setProperty("HADOOP_USER_NAME", "root");

        // 定义输出字段(Field)之间的分隔符
        DelimitedRecordFormat recordFormat = new DelimitedRecordFormat();
        DelimitedRecordFormat format = recordFormat.withFieldDelimiter("|");

        // 同步策略: 每100个tuples之后就会把数据从缓存刷新到HDFS中
        CountSyncPolicy countSyncPolicy = new CountSyncPolicy(100);

        // 文件策略: 每个文件大小上限1M,超过限定时,创建新文件并继续写入
        FileSizeRotationPolicy rotationPolicy = new FileSizeRotationPolicy(1.0f, FileSizeRotationPolicy.Units.MB);

        // 定义存储路径
        DefaultFileNameFormat defaultFileNameFormat = new DefaultFileNameFormat();
        DefaultFileNameFormat fileNameFormat = defaultFileNameFormat.withPath("/storm-hdfs/");

        // 定义HdfsBolt
        HdfsBolt hdfsBolt = new HdfsBolt()
                .withFsUrl("hdfs://192.168.126.128:9000")
                .withFileNameFormat(fileNameFormat)
                .withRecordFormat(format)
                .withRotationPolicy(rotationPolicy)
                .withSyncPolicy(countSyncPolicy);

        // 构建Topology
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout(DATA_SOURCE_SPOUT, new DataSourceSpout(), 1);
        builder.setBolt(SPLIT_BOLT, new SplitBolt(), 1).shuffleGrouping(SPLIT_BOLT);
        builder.setBolt(COUNT_BOLT, new CountBolt(), 1).shuffleGrouping(COUNT_BOLT);
        // save to HDFS
        builder.setBolt(HDFS_BOLT, hdfsBolt, 1).shuffleGrouping(DATA_SOURCE_SPOUT);

        // 如果外部传参cluster则代表线上环境启动,否则代表本地启动
        if (args.length > 0 && args[0].equals("cluster")) {
            try {
                StormSubmitter.submitTopology("DataToHdfsApp", new Config(), builder.createTopology());
            } catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
                e.printStackTrace();
            }
        } else {
            // 创建本地集群
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("DataToHdfsApp",
                    new Config(), builder.createTopology());
        }

//        // 创建本地集群
//        LocalCluster cluster = new LocalCluster();
//        cluster.submitTopology("DataToHdfsApp",
//                new Config(), builder.createTopology());


    }


}
