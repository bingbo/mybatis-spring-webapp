package com.ibingbo.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by bing on 17/5/31.
 */
public class ProducerDemo {
    public static void main(String[] args) {
        Random rnd = new Random();
        int events = 100;

        Properties properties = new Properties();
        properties.put("metadata.broker.list", "127.0.0.1:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("key.serializer.class", "kafka.serializer.StringEncoder");
        properties.put("partitioner.class", "com.ibingbo.kafka.PartitionerDemo");
        properties.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(properties);

        Producer<String, String> producer = new Producer<String, String>(config);

        long start = System.currentTimeMillis();
        for (long i=0;i<events;i++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + i;
            String msg= runtime + ", www.ibingbo.com,"+ip;
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
            producer.send(data);

        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        producer.close();
    }
}
