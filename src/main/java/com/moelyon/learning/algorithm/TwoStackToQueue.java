package com.moelyon.learning.algorithm;

import java.util.Deque;
import java.util.LinkedList;

public class TwoStackToQueue<E> {

    private Deque<E> inputStack;
    private Deque<E> outputStack;


    public TwoStackToQueue() {
        inputStack = new LinkedList<>();
        outputStack = new LinkedList<>();
    }

    public void put(E data){
        inputStack.push(data);
    }

    public E get(){
        if(outputStack.size()>0){
            return outputStack.removeLast();
        }

        while (!inputStack.isEmpty()){
            outputStack.push(inputStack.pop());
        }

        if(outputStack.isEmpty()){
            return null;
        }

        return outputStack.removeLast();
    }

    public static void main(String[] args) {
        TwoStackToQueue<String> queue = new TwoStackToQueue<>();

    }
}
class CQueue {
    private Deque<Integer> inputStack;
    private Deque<Integer> outputStack;

    public CQueue() {
        inputStack = new LinkedList<>();
        outputStack = new LinkedList<>();
    }

    public void appendTail(int value) {
        inputStack.push(value);
    }

    public int deleteHead() {
        if(outputStack.size()>0){
            return outputStack.removeLast();
        }

        while (!inputStack.isEmpty()){
            outputStack.addFirst(inputStack.removeLast());
        }

        if(outputStack.isEmpty()){
            return -1;
        }

        return outputStack.removeLast();
    }
}
