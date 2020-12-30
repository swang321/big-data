#### 1 .消费者和消费者群组

#### 2. 分区再均衡

#### 3. 创建Kafka消费者

在创建消费者的时候以下以下三个选项是必选的：

- **bootstrap.servers** ：指定 broker 的地址清单，清单里不需要包含所有的 broker 地址，生产者会从给定的 broker 里查找 broker 的信息。不过建议至少要提供两个 broker 的信息作为容错；
- **key.deserializer** ：指定键的反序列化器；
- **value.deserializer** ：指定值的反序列化器。

除此之外你还需要指明你需要想订阅的主题，可以使用如下两个 API :

+  **consumer.subscribe(Collection\<String> topics)**  ：指明需要订阅的主题的集合；
+  **consumer.subscribe(Pattern pattern)**  ：使用正则来匹配需要订阅的集合。