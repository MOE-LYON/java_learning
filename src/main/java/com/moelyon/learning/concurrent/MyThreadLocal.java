package com.moelyon.learning.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author moelyon
 */
public class MyThreadLocal {

    public static void main(String[] args) {
        ThreadLocal<AtomicInteger> threadLocal = ThreadLocal.withInitial(()->new AtomicInteger(0));
        threadLocal.get();
    }
}
