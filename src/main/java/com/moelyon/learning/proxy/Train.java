package com.moelyon.learning.proxy;

import java.util.Random;

/**
 * @author moelyon
 */
public class Train {

    public void move(){
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
