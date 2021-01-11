package com.hadoop.hbase.config;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whh
 * @desc
 * @date 2021/1/5 11:15
 */
@RestController
public class TestController {
    @Data
    class UserVO{
        private String id;
        private String name;
    }
    @Data
    class UserDTO{
        private UserVO userVO;
        private String [] ids;
        private String [] names;
    }

    @RequestMapping("test")
    @ResponseBody
    public String test(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        return "test";
    }
    @RequestMapping("test1")
    @ResponseBody
    public String test1(){
        return "test1";
    }
}
