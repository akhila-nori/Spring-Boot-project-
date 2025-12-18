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
        List<String> fruits = List.of("Apple", "Kiwi", "Banana","Mango");

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
//        Map<String, Integer> frui = fruits
                Map<Integer, Long> fr = fruits
                .stream()
//                .map(f -> f.length())
//                .collect(Collectors.toMap(fr -> fr, fruVal -> fruVal.length() ));  //lambda version


//                  .collect(Collectors.toMap(fr -> fr, String:: length)); //method referencing version ,//It tells Java: "I have a String here; please just run its .length() method and give me the result."


                  .collect(Collectors.groupingBy(
                          String::length,
                          Collectors.counting()
                  ));


                //Method - 4 main logic ********************************
                Map<Integer,List<String>> result = fruits
                        .stream()
                        .collect(Collectors.groupingBy(String::length));
                //How to find the KEY
        //Look at the Classifier (the first argument inside the parentheses).
        //
        //Code: String::length --> length() of String calculation --> method referencing
        //
        //Rule: Whatever this function returns becomes your Key.
        //
        //Logic: Since .length() returns a number, your Key is an Integer.


//        How to find the VALUE
//        Look at the Source of the Stream and the Arguments.
//
//        Rule A (Default): If there is only one argument in groupingBy, the Value is always a List of the objects currently in the stream.
//
//        Source: You started with fruits.stream(), which is a stream of String.
//
//                Logic: Therefore, your Value is List<String>.





                //Look at each item: It takes every String in your stream (e.g., "Apple", "Kiwi").Calculate the "Label" (The Key): It runs String::length on each item."Apple" $\rightarrow$ 5"Kiwi" $\rightarrow$ 4"Banana" $\rightarrow$ 6"Mango" $\rightarrow$ 5
       //Group them into Lists (The Value): It creates a Map where the Keys are the lengths <Integer>, and the Values are Lists containing all strings that have that length List<String>
//The Resulting Map: { 4=["Kiwi"], 5=["Apple", "Mango"], 6=["Banana"] }

        System.out.println("fruits using collector ..." + result);
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
