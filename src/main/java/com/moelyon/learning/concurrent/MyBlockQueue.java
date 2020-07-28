package com.moelyon.learning.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockQueue<E> {

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private Object[] queue = null;
    private int count =0;
    private int addIndex =0;
    private int removeIndex =0;

    public MyBlockQueue(int size) {
        if(size <=0){
            throw new IllegalArgumentException("count can't be less than zero");
        }
        queue = new Object[size];
    }

    public E take() throws InterruptedException {
        lock.lock();
        // wait the queue is not empty
        while (count ==0){
            notEmpty.await();
        }

        // safe maintain
        E data = (E) queue[removeIndex];
        count--;
        if(++removeIndex == queue.length){
            removeIndex = 0;
        }

        // notify that producer cant put something to this queue
        notFull.signal();

        // must release the lock before
        try {
        }finally {
            lock.unlock();
        }
        return data;
    }


    public void put(E data) throws InterruptedException {
        lock.lock();

        while (count == queue.length){
            notFull.await();
        }

        queue[addIndex] = data;
        if(++addIndex == queue.length ){
            addIndex=0;
        }
        count--;
        notEmpty.signal();
        try {

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockQueue<String> queue = new MyBlockQueue<>(5);

        new Thread(()->{
            try {
                var str =  queue.take();
                System.out.println(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(2000);

        queue.put("qaq");
        System.out.println("qaq send success");
    }
}
