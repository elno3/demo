package com.example.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class DemoApplicationTests {

    @Test
    void contextLoads() {
        var i = 6;
        var s = "strings";
        Function<String, String> f = x -> "i'm fuinction";
        System.out.println(f.apply("5"));

        Consumer c = x -> System.out.println("consumed");
        c.accept("x");

        Supplier<String> supplier = () -> "String supplier";
        ss(supplier);
        Predicate<Integer> p = (x) -> x > 0;
        Predicate<Integer> p1 = (x) -> x < 100;
        final Predicate<Integer> and = p.and(p).and(p1);
        System.out.println(and.test(500));
    }

    private void ss(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }

    private static void invoke(final Runnable r) {
        r.run();
    }

    private static void target() {
        new Exception().printStackTrace();
    }

    @Test
    public void lambda() throws Exception {
        invoke(() -> target());
    }

    @Test
    public void methodReference() throws Exception {
        invoke(DemoApplicationTests::target);
    }

}
