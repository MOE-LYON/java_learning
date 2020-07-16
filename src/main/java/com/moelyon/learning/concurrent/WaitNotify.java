package com.moelyon.learning.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class WaitNotify {
    public static class Thread1 implements Runnable {
        private final Object lock;
        private final String name;

        public Thread1(final Object lock, final String name) {
            this.lock = lock;
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; ++i) {

                    System.out.println("thread " + name + " " + i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                }
                lock.notify();
            }

        }
    }

    public static void main(final String[] args) throws InterruptedException {
        final Object lock = new Object();
        final Thread a = new Thread(new Thread1(lock, "A"));
        final Thread b = new Thread(new Thread1(lock, "B"));
        a.start();
        Thread.sleep(200L);
        b.start();
        AtomicInteger i = new AtomicInteger();
        i.set(5);
        AtomicLong aLong = new AtomicLong();
        aLong.set(2L);
    }
}