package com.moelyon.learning.observe;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Flow;

/**
 * @author moelyon
 */
public class ConcreteObserve implements Observer {

    private String name;

    public ConcreteObserve(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println(String.format(Locale.CHINESE,"%s receive %s",name,arg));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
