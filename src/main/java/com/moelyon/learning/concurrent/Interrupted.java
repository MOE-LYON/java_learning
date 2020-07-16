package com.moelyon.learning.concurrent;

public class Interrupted {


    public static class  TimeRunner implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("233 sleep exception");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new TimeRunner());
        t.start();
        Thread.sleep(1000);
        t.interrupt();

    }
}
