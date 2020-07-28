package com.moelyon.learning.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("can't be zero");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (; ; ) {
                int currentState = getState();
                int newState = currentState - arg;

                if (newState < 0 || compareAndSetState(currentState, newState)) {
                    return newState;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int currentState = getState();
                int newState = currentState + arg;

                if (compareAndSetState(currentState, newState)) {
                    return true;
                }
            }
        }
    }

    private Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

class TwinsLockTest {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new TwinsLock();

        class Worker implements Runnable {

            @Override
            public void run() {
                while (true) {
                    lock.lock();

                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Thread " + Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        for (int i = 0; i < 5; ++i) {
            Thread t = new Thread(new Worker());
            t.setDaemon(true);
            t.start();
        }

        for (int i = 0; i < 10; ++i) {
            TimeUnit.SECONDS.sleep(4);
            System.out.println();
        }
    }

    void test() {
        System.out.println("233");
    }
}
