## Transformations 分类

Flink 的 Transformations 操作主要用于将一个和多个 DataStream 按需转换成新的 DataStream。它主要分为以下三类：

- **DataStream Transformations**：进行数据流相关转换操作；
- **Physical partitioning**：物理分区。Flink 提供的底层 API ，允许用户定义数据的分区规则；
- **Task chaining and resource groups**：任务链和资源组。允许用户进行任务链和资源组的细粒度的控制。