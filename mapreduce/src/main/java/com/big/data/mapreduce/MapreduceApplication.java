package com.big.data.mapreduce;

import com.big.data.mapreduce.mapper.WordCountMapper;
import com.big.data.mapreduce.reduce.WordCountReduce;
import com.big.data.mapreduce.run.WordCountDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MapreduceApplication {

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        test(args);
        SpringApplication.run(MapreduceApplication.class, args);
    }

    private static void test(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("-----------");
        // 1.获取配置信息
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 2.设置加载jar的位置路径,直接传入当前Class对象
        job.setJarByClass(WordCountDriver.class);

        // 3.设置map和reduce类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);

        // 4.设置map的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);


        // 5.设置最终的输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 6.设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("/user/input.text"));
        FileOutputFormat.setOutputPath(job, new Path("/user/output.text"));

        // 7.提交
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

}
