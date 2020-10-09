package com.fujiang.weiji.test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class function {
    private int age;

    public static void main(String[] args) {
        PageSupplier<Integer, String> supplier = new PageSupplier<Integer, String>();
        supplier.setFunc(function::compute);
        supplier.setParam1(111);
        supplier.setParam("222");
        //Stream.generate(supplier).peek()

        Stream.generate(supplier).peek((data) -> {
            System.out.println(data);
        });
        System.out.println("1111111111111111111111111111111111111111111111");
        Stream.generate(supplier).limit(1).forEach(System.out::println);
        System.out.println("1111111111111111111111111111111111111111111111");
        Stream.generate(new NatualSupplier()).limit(20).forEach(System.out::println);
    }

    private static Integer compute(Integer integer, String string) {
        return 5;// integer + integer1.length();
    }

    static class NatualSupplier implements Supplier<Integer> {
        int n = 0;
        public Integer get() {
            n++;
            return n;
        }
    }

    public static void biFunction(String[] args) {
        int a = compute(5, value -> value * value);
        int b = compute2(2, value -> value * 3, value -> value * value);
        int c = compute3(2, value -> value * 3, value -> value * value);
        int d = compute4(2, 3, (v1, v2) -> v1 + v2);
        int e = compute5(2, 3, (v1, v2) -> v1 + v2, v1 -> v1 * v1);
    }

    public static int compute(int a, Function<Integer, Integer> function) {
        int result = function.apply(a);
        return result;
    }

    public static int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }

    public static int compute3(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }

    public static int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    public static int compute5(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function) {
        return biFunction.andThen(function).apply(a, b);
    }
}
