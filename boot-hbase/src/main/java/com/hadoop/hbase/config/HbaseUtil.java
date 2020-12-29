package com.hadoop.hbase.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.List;

/**
 * @author whh
 * @desc
 * @date 2020/11/26 15:20
 */
@Slf4j
public class HbaseUtil {


    public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;

    public static void main(String[] args) throws IOException {

//        // 定义流数据与HBase中数据的映射
//        SimpleHBaseMapper mapper = new SimpleHBaseMapper()
//                .withRowKeyField("word")
//                .withColumnFields(new Fields("word","count"))
//                .withColumnFamily("info");
        init();
//        createTable("WordCount", new String[]{"info"});
//        insertData("WordCount", "hbase", "info", "hbase", "6");
        getData("WordCount", "hbase", "info", "hbase");
        getData("WordCount", "Flink", "info", "hbase");
        close();


//        init();
//        createTable("student", new String[]{"score"});
//        insertData("student", "whh", "score", "语文", "100");
//        getData("student", "whh", "score", "语文");
//        close();
    }

    /**
     * @param myTableName 表名
     * @param colFamily   列族数组
     */
    public static void createTable(String myTableName, String[] colFamily) throws IOException {

        TableName tableName = TableName.valueOf(myTableName);
        if (admin.tableExists(tableName)) {
            System.out.println("table exists!");
        } else {
            TableDescriptorBuilder descriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
            List<ColumnFamilyDescriptor> lists = Lists.newArrayList();
            for (String str : colFamily) {
                ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(str.getBytes()).build();
                lists.add(columnFamilyDescriptor);
            }
            descriptorBuilder.setColumnFamilies(lists);
            TableDescriptor build = descriptorBuilder.build();
            admin.createTable(build);
            log.info("创建HBase 表成功! 表->{} ,列族->{} !", tableName, StringUtils.join(lists, ','));
        }
    }

    /**
     * @param tableName 表名
     * @param rowKey    行键
     * @param colFamily 列族
     * @param col       列限定符
     * @param val       数据
     */
    public static void insertData(String tableName, String rowKey, String colFamily, String col, String val) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes());
        put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
        table.put(put);
        table.close();
    }


    /**
     * 获取某单元格数据
     *
     * @param tableName 表名
     * @param rowKey    行键
     * @param colFamily 列族
     * @param col       列限定符
     */
    public static void getData(String tableName, String rowKey, String colFamily, String col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        get.addColumn(colFamily.getBytes(), col.getBytes());
        //获取的result数据是结果集，还需要格式化输出想要的数据才行
        Result result = table.get(get);
        System.out.println(new String(result.getValue(colFamily.getBytes(), col.getBytes())));
        table.close();
    }


    public static void init() {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.rootdir", "hdfs://192.168.126.128:9000/hbase");
        configuration.set("hbase.zookeeper.quorum", "192.168.126.128,192.168.126.129,192.168.126.130");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("zookeeper.znode.parent", "/hbase");
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (admin != null) {
                admin.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
