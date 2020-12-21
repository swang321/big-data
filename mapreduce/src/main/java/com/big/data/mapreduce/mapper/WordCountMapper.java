package com.big.data.mapreduce.mapper;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author whh
 * @desc
 * @date 2020/12/10 17:11
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text k = new Text();

    private IntWritable v = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 将每次读入的一行进行分割
        String line = value.toString();
        // 2 转换成String类型进行分割
        String[] words = line.split(" ");
        // 3 将每个键值对都写出
        for (String word : words) {
            String trim = word.trim();
            if (!" ".equals(trim)) {
                k.set(trim);
                // 4 map阶段将单词拆分，并不合并，所以固定值为1
                context.write(k, v);
            }
        }

    }


}
