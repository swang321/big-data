package org.flink.socket;

/**
 * @author whh
 * @desc
 * @date 2020/12/30 16:06
 */
public class StreamingSocketJob {
    //### 1.3  基于 Socket 构建
    //
    //Flink 提供了 socketTextStream 方法用于构建基于 Socket 的数据流，socketTextStream 方法有以下四个主要参数：
    //
    //- **hostname**：主机名；
    //- **port**：端口号，设置为 0 时，表示端口号自动分配；
    //- **delimiter**：用于分隔每条记录的分隔符；
    //- **maxRetry**：当 Socket 临时关闭时，程序的最大重试间隔，单位为秒。设置为 0 时表示不进行重试；设置为负值则表示一直重试。示例如下：
}
