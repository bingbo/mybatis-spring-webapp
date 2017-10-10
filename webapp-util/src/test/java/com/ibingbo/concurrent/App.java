package com.ibingbo.concurrent;

import com.ibingbo.concurrent.semaphore.HolderService;

/**
 * @author zhangbingbing
 * @title App
 * @date 17/10/9
 */
public class App {
    public static void main(String[] args) {
        HolderService service = new HolderService();
        PThread[] pThreads = new PThread[60];
        CThread[] cThreads = new CThread[60];
        for (int i = 0; i < 60; i++) {
            pThreads[i] = new PThread(service);
            cThreads[i] = new CThread(service);
        }

        for (int i = 0; i < 60; i++) {
            pThreads[i].start();
            cThreads[i].start();
        }
    }

    public static class PThread extends Thread {
        private HolderService service;

        @Override
        public void run() {
            this.service.get();

        }

        public PThread(HolderService service) {
            this.service = service;
        }
    }

    public static class CThread extends Thread {
        private HolderService service;

        @Override
        public void run() {
            this.service.set();

        }

        public CThread(HolderService service) {
            this.service = service;
        }
    }

}
