package com.module2.module2.lambda;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

public class StreamsAndLambda {
    public static void main(String[] args) {

        //Older way of dealing
//        Walkable w = new Walkfast();
//        w.walk(5);

        //lambda expression introduced in java 8
        Walkable obj = (steps, isEnabled) -> {

            System.out.println("Walking fast " + steps + " steps.");
            return 2 * steps;
        };

        Walkable obj2 = (steps, isEnabled) -> 2 * steps; //returning , 1 line of body, avoiding curly braces

        obj.walk(4, true);


        //convert List<> into Stream and perform stream related operations on this converted stream
        List<String> fruits = List.of("Apple", "Kiwi", "Banana");

        Stream<String> stream = fruits.stream();

        //METHOD - 1
//        stream.forEach((f) -> {
//            System.out.println(f);
//        });

        //METHOD -2
//        stream
//                .filter(frui -> frui.length() < 5)
//                .sorted()
//                .map(f -> f.length()) //getting this stream
//                .map(fruitLegnth -> 2*fruitLegnth)
//                .forEach(fr -> System.out.println(fr));
//
//    }

        //METHOD 3
        Map<String, Integer> frui = fruits
                .stream()
//                .map(f -> f.length())
                .collect(Collectors.toMap(fr -> fr, fruVal -> fruVal.length() ));

        System.out.println("fruits using collector ..." + frui);
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
