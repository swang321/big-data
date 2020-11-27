package com.big.data.hdfs.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author whh
 * @desc
 * @date 2020/11/26 16:39
 */
public class HdfsCreate {

    public static void main(String[] args) throws IOException {

//        create("/test/testA","China is my motherland I love China");
        create("/test/wordcount","");

//        read("/test", "testA");
//        read("/test", "testB");


    }

    /**
     * 读取 hdfs 文件系统上面的文件
     */
    private static void read(String path, String fileName) {
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://192.168.126.128:9000");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            FileSystem fs = FileSystem.get(conf);
            Path file = new Path(path + "/" + fileName);
            FSDataInputStream in = fs.open(file);
            BufferedReader d = new BufferedReader(new InputStreamReader(in));
            String content = d.readLine();
            System.out.println(content);
            d.close();
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在 fdfs 上面创建文件
     */
    private static void create(String fileName, String content) {
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
