package com.big.data.hdfs.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;


/**
 * @author whh
 * @desc
 * @date 2020/11/26 16:39
 */
public class HdfsCreate {

    public static void main(String[] args) throws IOException {

        test("/test/testA","China is my motherland I love China");
        test("/test/testB","I am from  China");

    }

    /**
     * 在 fdfs 上面创建文件
     */
    private static void test(String fileName, String content) {
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://192.168.126.128:9000");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            FileSystem fs = FileSystem.get(conf);
            FSDataOutputStream os = fs.create(new Path(fileName));
            os.write(content.getBytes(), 0, content.getBytes().length);
            os.close();
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
