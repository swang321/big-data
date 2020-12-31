package com.kafka.bootkafka.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author whh
 * @desc
 * @date 2020/12/31 11:07
 */
@Builder
@Data
public class User {

    private String name;

    private String age;

}
