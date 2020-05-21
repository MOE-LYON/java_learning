package com.moelyon.learning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author moelyon
 */
public class LogHandler implements InvocationHandler {

    private final Object target;

    public LogHandler(Object target) {
        this.target = target;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("begin log");
        //System.out.println(proxy);
        method.invoke(target,args);

        System.out.println("end log");
        return null;
    }
}
