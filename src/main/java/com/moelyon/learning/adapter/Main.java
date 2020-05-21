package com.moelyon.learning.adapter;

/**
 * @author moelyon
 */
public class Main {
    public static void main(String[] args) {

        ThreePlugable threePlug = null;
        Pad pad = new Pad(threePlug);

        pad.charge();
    }
}
