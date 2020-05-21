package com.moelyon.learning.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author moelyon
 */
public class CglibProxy implements MethodInterceptor {

    private final Enhancer enhancer = new Enhancer();

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("start proxy");
        long before = System.currentTimeMillis();
        proxy.invokeSuper(obj,args);

        long after = System.currentTimeMillis();
        System.out.println("end proxy");
        System.out.println("time cost "+(after-before)+" ms");
        return null;
    }
}
