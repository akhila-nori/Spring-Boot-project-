package com.module2.module2.lambda;

import java.util.List;
import java.util.stream.Stream;

public class StreamsAndLambda {
    public static void main(String[] args) {

        //Older way of dealing
//        Walkable w = new Walkfast();
//        w.walk(5);

        //lambda expression introduced in java 8
        Walkable obj = (steps, isEnabled) -> {

            System.out.println("Walking fast "+steps + " steps.");
            return 2*steps;
        };

        Walkable obj2 = (steps, isEnabled) -> 2*steps; //returning , 1 line of body, avoiding curly braces

        obj.walk(4,true);


        //convert List<> into Stream and perform stream related operations on this converted stream
        List<String> fruits = List.of("Apple","Kiwi","Banana");

        Stream<String> stream = fruits.stream();

        stream.forEach((f) -> {
            System.out.println(f);
        });

    }
}

@FunctionalInterface
interface Walkable {
    int walk(int steps, boolean isEnabled);
}

//using lambda expression on functional interfaces you avoid defining a permanent named class, instead you define anonymous class
//class Walkfast implements Walkable {
//
//    @Override
//    public int walk(int steps) {
//        System.out.println("Walking fast "+steps + " steps.");
//        return 2*steps;
//    }
//}
