package com.kafka.bootkafka.controller;

import com.kafka.bootkafka.kafka.KafkaConsumerTest;
import com.kafka.bootkafka.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whh
 * @desc
 * @date 2020/12/31 11:13
 */
@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumerTest kafkaConsumer;

    @RequestMapping("createMsg")
    public void createMsg() {
        kafkaProducer.sendMsg();
    }

//    @RequestMapping("readMsg")
//    public void readMsg() {
//        kafkaConsumer.consume();
//    }

}
