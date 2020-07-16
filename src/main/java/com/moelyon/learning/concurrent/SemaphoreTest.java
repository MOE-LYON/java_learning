package com.moelyon.learning.concurrent;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        IntStream.range(1, 10).forEach((i)->{
            new Thread(new MyThread(i, semaphore)).start();
        });
    }

    private static class MyThread implements Runnable {

        private int num;
        private Semaphore semaphore;

        public MyThread(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                semaphore.acquire();
                System.out.println(String.format(Locale.CHINA, "当前线程是%d, 还剩%d个资源，还有%d个线程在等待",num,semaphore.availablePermits(), semaphore.getQueueLength()));
                Random random = new Random();
                int n = random.nextInt(1000);
                Thread.sleep(random.nextInt(1000));
                System.out.println(num+" "+n);
                semaphore.release();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}