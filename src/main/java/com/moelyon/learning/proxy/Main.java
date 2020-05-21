package com.moelyon.learning.proxy;

import java.lang.reflect.Proxy;

/**
 * @author moelyon
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        Class<?> cls = Car.class;
        Moveable moveable = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new LogHandler(car));

        moveable.move();
    }


}
class CglibMain{
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();

        Car car = cglibProxy.getProxy(Car.class);
        car.move();
    }
}
