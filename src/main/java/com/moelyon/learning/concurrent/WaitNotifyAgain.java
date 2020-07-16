package com.moelyon.learning.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotifyAgain {

    static boolean flag = true;
    static Object lock = new Object();
    public static void main(String[] args) {
        Thread wautThread = new Thread(new Wait(),"WaitThread");
        wautThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        notifyThread.start();
    }

    private static class Wait implements Runnable{

        @Override
        public void run() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            synchronized (lock){
                if (flag){
                    try {
                        System.out.println(Thread.currentThread() + " flag is true wait. @"+dateFormat.format(new Date()));
                        lock.wait();
                    }catch (InterruptedException e) {
                    }
                }
            }
            System.out.println(Thread.currentThread() + " flag is false running. @"+dateFormat.format(new Date()));
        }
    }

    private static class Notify implements Runnable{

        @Override
        public void run() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock notify. @"+dateFormat.format(new Date()));
                lock.notify();
                flag = false;
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again. @"+dateFormat.format(new Date()));

                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
