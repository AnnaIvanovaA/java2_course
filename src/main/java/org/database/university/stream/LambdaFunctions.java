package org.database.university.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaFunctions {
    public static void main(String[] args) {

        List<Integer> values = new ArrayList<>();
        values.add(145);
        values.add(13);
        values.add(2);
        values.add(6);
        values.add(8);
        values.add(5);
        values.add(21);
        values.add(14);
        values.add(121);

        int maxBound = 10;

//        values.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                //<0 - when o1<o2 : -1
//                // 0 - when o1=o2 : 0
//                //>0 - when o1>o2 : 1
//
//                return -Integer.compare(o1, o2);
//            }
//        });
        values.sort((o1, o2) -> -Integer.compare(o1, o2));

        System.out.println(values);

        int minBound = 10;


        //1. Interface contains only one method to override
        //2. (optional) Interface has annotation @FunctionalInterface

        //   values.removeIf(new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer val) {
//                return val < minBound;
//            }
//        });
        values.removeIf((val) -> val < minBound);
        System.out.println(values);


//        List<Integer> y = new ArrayList<Integer>(){
//            @Override
//            public boolean add(Integer integer) {
//                System.out.println("Value: " + integer);
//                return super.add(integer);
//            }
//        };
//        y.add(77);
//        y.add(4);

        System.out.println("foreach loop");
        for (Integer val : values) {
            System.out.println(val);

        }
        System.out.println("lambda foreach");
        values.forEach(val -> System.out.println("val: " + val));

        List<String> resultList = new ArrayList<>();
        for (Integer val : values) {
            String result = Integer.toHexString(val) + " " + Integer.toOctalString(val);
            resultList.add(result);
        }
        //the same with streams
        List<String> hexOctValues = values.stream()
                .map(val -> Integer.toHexString(val) + " " + Integer.toOctalString(val))
                .collect(Collectors.toList());
        System.out.println("Hex/Oct values");
        hexOctValues.forEach(val -> System.out.println("hex/oct:" + val));

    }
}
