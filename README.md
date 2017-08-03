# mybatis-spring-webapp

mybatis-spring-webapp

* mvn eclipse:eclipse 转变为eclipse工程，方便导入eclipse
* java build path里添加runtime server tomcat类库
* project Facets里转变为dynamic web module，即转换为web工程
* deployment assembly里添加相应的source及deploy path，以便找到并加载web.xml配置文件，加载相应的spring配置


## 多数据源，master和slave读写分离

1. 通过继承AbstractRoutingDataSource实现数据源的选取

    ```java
    protected DataSource determineTargetDataSource() {
        DataSource dataSource = null;
        if (DataSourceHolder.isMaster()) {
            dataSource = master;
        } else if (DataSourceHolder.isSlave()) {
            // 简单轮循
            int count = counter.getAndIncrement();
            if (count > 100000) {
                counter.set(0);
            }
            int index = count % slaves.size();
            dataSource = slaves.get(index);
        } else {
            dataSource = master;
        }
        return dataSource;
    }
    ```
    
1. 由该线程DataSourceHolder保存要使用的数据源

    ```java
    public class DataSourceHolder {
        private static final String MASTER = "master";
        private static final String SLAVE = "slave";

        private static final ThreadLocal<String> dataSource = new ThreadLocal<>();
        private static final ThreadLocal<DataSource> masterLocal = new ThreadLocal<>();
        private static final ThreadLocal<DataSource> slaveLocal = new ThreadLocal<>();

        private static void setDataSource(String dataSourceKey) {
            dataSource.set(dataSourceKey);
        }

        private static String getDataSource() {
            return dataSource.get();
        }

        public static boolean isMaster() {
            return getDataSource() == MASTER;
        }

        public static boolean isSlave() {
            return getDataSource() == SLAVE;
        }

        public static void setSlave(DataSource dataSource) {
            slaveLocal.set(dataSource);
        }

        public static void setMaster(DataSource dataSource) {
            masterLocal.set(dataSource);
        }

        public static void setMaster() {
            setDataSource(MASTER);
        }

        public static void setSlave() {
            setDataSource(SLAVE);
        }

        public static void clearDataSource() {
            dataSource.remove();
            masterLocal.remove();
            slaveLocal.remove();
        }

    }
    ```

1. 通过事务或AOP来确定使用的数据源

> 具体可参考`com.ibingbo.support.multi`包中多数据源方案

## kafka应用

更多详情见[官网](https://kafka.apache.org/)

### 安装与Shell交互

> 下载安装包并解压

```bash
tar -xzf kafka_2.11-0.11.0.0.tgz
cd kafka_2.11-0.11.0.0
```

> 启动kafka服务

```bash
##依赖于zookeeper，先启动zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

##再启动kafka
bin/kafka-server-start.sh config/server.properties
```

> 创建一个主题topic

```bash
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

##查看有哪些主题
bin/kafka-topics.sh --list --zookeeper localhost:2181
```

> 发送消息

```bash
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
This is a message
This is another message
```

> 消费消息

```bash
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
```

### Java API应用

> 添加依赖

```xml
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>0.10.2.1</version>
</dependency>
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-streams</artifactId>
    <version>0.10.2.1</version>
</dependency>
```

> producer生产者

```java
public class MsgProducer {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
//        props.put("bootstrap.servers", "10.94.239.21:8092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i=0;i<100;i++) {
            Thread.sleep(1000);
            producer.send(new ProducerRecord<String, String>("test", "key"+i, "value"+i));
        }
        producer.close();
    }
}
```

> consumer消费者

```java
public class MsgConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "10.94.239.21:8092");
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("group.id", "testConsumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        final Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("test"));
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                consumer.close();
            }
        });
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
            System.out.println();
            }
        }
    }
}
```