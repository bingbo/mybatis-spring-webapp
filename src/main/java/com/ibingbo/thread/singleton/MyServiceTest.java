package com.ibingbo.thread.singleton;

/**
 * Created by bing on 2017/3/2.
 */
public class MyServiceTest {

    public static void main(String[] args) {
        for (int i=0;i<10;i++) {
            new ThreadService().start();
            //new ThreadObject().start();
        }
    }
    static class ThreadService extends Thread {
        @Override
        public void run() {
            System.out.println("myService " + MyService.getInstance().hashCode());
        }
    }

    static class ThreadObject extends Thread{
        @Override
        public void run() {
            System.out.println("myObject " + MyServiceEnum.myServiceFactory.getObject().hashCode());
        }
    }

}
