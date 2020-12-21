package com.big.data.mapreduce.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author whh
 * @desc
 * @date 2020/12/10 17:12
 */
public class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {


    @Override
    protected void reduce(Text text, Iterable<IntWritable> iterable, Context context) throws IOException, InterruptedException {

        // 1. 将map阶段同一个key对应的value值求和
        int sum = 0;
        for (IntWritable intWritable : iterable) {
            sum += intWritable.get();
        }
        if (!"".equals(text.toString().trim())) {
            //将结果输出
            context.write(text, new IntWritable(sum));
        }
    }
}
