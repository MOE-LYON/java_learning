package com.moelyon.learning.observe;


import java.util.Observable;
import java.util.concurrent.Flow;

/**
 * @author moelyon
 */
public class ConcreteWeatherSubject extends Observable {

    private String weather;

    public void setWeather(String weather){
        this.weather = weather;
        this.setChanged();

        this.notifyObservers(weather);
    }


    public String getWeather() {
        return weather;
    }
}
