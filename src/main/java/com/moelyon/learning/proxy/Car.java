package com.moelyon.learning.proxy;

import java.util.Random;

/**
 * @author moelyon
 */
public class Car implements Moveable {
    @Override
    public void move() {
        try {
            System.out.println("car start move");
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
