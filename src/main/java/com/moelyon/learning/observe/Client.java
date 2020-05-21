package com.moelyon.learning.observe;

/**
 * @author moelyon
 */
public class Client {
    public static void main(String[] args) {
        ConcreteWeatherSubject subject = new ConcreteWeatherSubject();

        ConcreteObserve girl = new ConcreteObserve("girl friend");
        ConcreteObserve mom = new ConcreteObserve("mom");

        subject.addObserver(girl);
        subject.addObserver(mom);

        subject.setWeather("beautify cloud,hello world");
    }
}
