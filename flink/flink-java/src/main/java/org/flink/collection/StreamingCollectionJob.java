package org.flink.collection;

import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author whh
 * @desc
 * @date 2020/12/30 15:54
 */
public class StreamingCollectionJob {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        //基于集合构建
//        env.fromCollection(Arrays.asList(1, 2, 3, 4, 5)).print();
//
//        //基于元素构建
//        env.fromElements(1,2,3,4,5).print();

        //基于给定的序列区间进行构建
//        env.generateSequence(0,100);

        // 基于迭代器进行构建
        env.fromCollection(new CustomIterator(), BasicTypeInfo.INT_TYPE_INFO).print();


        //fromParallelCollection(SplittableIterator, Class)**：
        // 方法接收两个参数，第二个参数用于定义输出元素的类型，
        // 第一个参数 SplittableIterator 是迭代器的抽象基类，它用于将原始迭代器的值拆分到多个不相交的迭代器中。

        env.execute();


    }

}
