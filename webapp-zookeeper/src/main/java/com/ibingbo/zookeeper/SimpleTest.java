package com.ibingbo.zookeeper;

/**
 * Created by bing on 17/7/19.
 */
public class SimpleTest {
    public static void main(String[] args) throws Exception {
        final SimpleProducer producer = new SimpleProducer();
        final SimpleConsumer consumer = new SimpleConsumer();



        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        producer.sendMessage("message" + i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "produce-thread").start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    consumer.init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
