package com.moelyon.learning.concurrent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {

    static ConnecionPool pool = new ConnecionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;

        end = new CountDownLatch(threadCount);
        int count =20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger nogot = new AtomicInteger();

        for(int i=0; i<threadCount; ++i){
            Thread thread = new Thread(new ConnetionRunner(count,got,nogot)," ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: "+threadCount*count);
        System.out.println("got connection "+got.intValue());
        System.out.println("notgot connection "+nogot.intValue());

    }

    static class ConnetionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notgot;

        public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notgot) {
            this.count = count;
            this.got = got;
            this.notgot = notgot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count-- > 0){
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if(connection != null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }

                    }else{
                        notgot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            end.countDown();
        }
    }
}
