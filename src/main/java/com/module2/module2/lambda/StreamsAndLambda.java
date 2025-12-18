package com.module2.module2.lambda;

public class StreamsAndLambda {
    public static void main(String[] args) {
        Walkable w = new Walkfast();
        w.walk(5);
    }
}

interface Walkable {
    int walk(int steps);
}

class Walkfast implements Walkable {

    @Override
    public int walk(int steps) {
        System.out.println("Walking fast "+steps + " steps.");
        return 2*steps;
    }
}
