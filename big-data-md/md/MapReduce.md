### MapReduce编程模型之执行步骤

**1. 执行步骤**

- 准备map处理的输入数据
- Mapper处理
- Shuffle
- Reduce处理
- 结果输出

### MapReduce核心概念

- Split：交由MapReduce作业来处理的数据块，是MapReduce中最小的计算单元
  - HDFS：blocksize 是HDFS中最小的存储单元，默认128M
  - 默认情况下：他们两是一一对应的，也可以手工设置他们之间的关系(不建议)
- InputFormat：将我们的输入数据进行分片(split)
  - 用的比较多的是子类TextInputFormat：处理文本格式的数据
- OutputFormat：输出
- Combiner
- Partitioner