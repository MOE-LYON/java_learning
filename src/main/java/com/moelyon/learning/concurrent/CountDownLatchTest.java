package com.moelyon.learning.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch c = new CountDownLatch(2);


        new Thread(()->{

            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("233 thread 1 wait success");
        }).start();
        new Thread(()->{

            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("233 thread 2 wait success");
        }).start();

        Thread.sleep(2000);
        c.countDown();
        Thread.sleep(2000);
        c.countDown();

        System.out.println("count down is over");
    }


}
