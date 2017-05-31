package com.ibingbo.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by bing on 17/5/31.
 */
public class SimpleConsumerDeom {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", "127.0.0.1:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("key.serializer.class", "kafka.serializer.StringEncoder");
        properties.put("partitioner.class", "com.ibingbo.kafka.PartitionerDemo");
        properties.put("request.required.acks", "1");
        ConsumerConfig config = new ConsumerConfig(properties);

    }
}
