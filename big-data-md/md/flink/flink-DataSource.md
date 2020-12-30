## 一、内置 Data Source

Flink Data Source 用于定义 Flink 程序的数据来源，Flink 官方提供了多种数据获取方法，用于帮助开发者简单快速地构建输入流，具体如下：

### 1.1 基于文件构建

**1. readTextFile(path)**：按照 TextInputFormat 格式读取文本文件，并将其内容以字符串的形式返回。示例如下：

```java
env.readTextFile(filePath).print();
```

**2. readFile(fileInputFormat, path)** ：按照指定格式读取文件。

**3. readFile(inputFormat, filePath, watchType, interval, typeInformation)**：按照指定格式周期性的读取文件。其中各个参数的含义如下：

+ **inputFormat**：数据流的输入格式。
+ **filePath**：文件路径，可以是本地文件系统上的路径，也可以是 HDFS 上的文件路径。
+ **watchType**：读取方式，它有两个可选值，分别是 `FileProcessingMode.PROCESS_ONCE` 和 `FileProcessingMode.PROCESS_CONTINUOUSLY`：前者表示对指定路径上的数据只读取一次，然后退出；后者表示对路径进行定期地扫描和读取。需要注意的是如果 watchType 被设置为 `PROCESS_CONTINUOUSLY`，那么当文件被修改时，其所有的内容 (包含原有的内容和新增的内容) 都将被重新处理，因此这会打破 Flink 的 *exactly-once* 语义。
+ **interval**：定期扫描的时间间隔。
+ **typeInformation**：输入流中元素的类型。