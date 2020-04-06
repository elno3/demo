package com.example.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        var i = 6;
        var s = "strings";
        Function<String, String> oneArgfun = (x) -> " i'm fuinction and receivd x: " + x;
        System.out.println(oneArgfun.apply("5"));
        
        
        BiFunction<Integer, Integer, String> twoArgFunc = (x, y) -> "sum: " + (x + y);
        //String result = bifun.apply(5, 8);
        
        //assertEquals("sum: 13",result);
        
//        twoArgFunc.andThen(oneArgfun).apply(4, 6);
        
        BiFunction<Integer, Integer, String> bigFunc = twoArgFunc.andThen(oneArgfun);
		String tes = bigFunc.apply(4, 6);
        assertEquals(" i'm fuinction and receivd x: sum: 10", tes);
        
        BiFunction<Integer, Integer, String> rewriteBigFunc = new BiFunction<Integer, Integer, String>() {
        	@Override
        	public String apply(Integer x, Integer y) {
        		String resultFromTwoArgFunc = twoArgFunc.apply(x, y);
        		String resultFromOneArgFunc = oneArgfun.apply(resultFromTwoArgFunc);
        		return resultFromOneArgFunc;
        	}
        };
        
        String test2 = rewriteBigFunc.apply(4, 6);
        assertEquals(" i'm fuinction and receivd x: sum: 10", test2);
        
        Consumer c = x -> System.out.println("consumed");
        c.accept("x");

        Supplier<String> supplier = () -> "String supplier";
  
        ss(supplier);
        Predicate<Integer> p = (x) -> x > 0;
        Predicate<Integer> p1 = (x) -> x < 100;
        final Predicate<Integer> and = p.and(p1);
        assertFalse("expected predicate false for and function",and.test(500));
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

    //@Test
    public void lambda() throws Exception {
        invoke(() -> target());
    }

    //@Test
    public void methodReference() throws Exception {
        invoke(DemoApplicationTests::target);
    }

}
