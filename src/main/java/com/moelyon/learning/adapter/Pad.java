package com.moelyon.learning.adapter;

public class Pad {
    private final ThreePlugable threePlug;

    public Pad(ThreePlugable threePlug) {
        this.threePlug = threePlug;
    }

    public void charge(){
        threePlug.charge();
    }
}
