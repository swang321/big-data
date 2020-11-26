package com.hadoop.hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class HbaseApplicationTests {

    /**
     * 内部已实现线程安全的连接池
     */
    @Autowired
    private Connection hbaseConnection;
    @Test
    void contextLoads() throws IOException {
        Table t_user = hbaseConnection.getTable(TableName.valueOf("t_user"));
        System.out.println(t_user);
    }

}
