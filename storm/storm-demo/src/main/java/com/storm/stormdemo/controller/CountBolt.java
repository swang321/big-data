package com.storm.stormdemo.controller;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author whh
 * @desc
 * @date 2020/12/28 16:37
 */
public class CountBolt extends BaseRichBolt {

    private Map<String, Integer> counts = new HashMap<>();

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

    }

    @Override
    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Integer count = counts.get(word);
        if (count == null) {
            count = 0;
        }
        count++;
        counts.put(word, count);
        // 输出
        System.out.print("当前实时统计结果:");
        counts.forEach((key, value) -> System.out.print(key + ":" + value + "; "));
        System.out.println();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
