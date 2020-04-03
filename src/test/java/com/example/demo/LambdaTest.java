package com.example.demo;

import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;
import java.util.function.Supplier;

public class LambdaTest {
    @Test
    void name() {
        System.out.println("Using lambda, does NOT hold on to unreferenced local variable:");
        Object strong0 = new Object();
        Supplier<WeakReference<Object>> s0 = lambda(strong0);
        System.out.println(strong0);
        System.out.println(s0.get());
        System.out.println(s0.get().get());

        System.out.println("\nnulling the strong reference");
        strong0 = null;
        System.out.println(strong0);
        System.out.println(s0.get());
        System.out.println(s0.get().get());

        System.out.println("\nrequesting a garbage collect");
        System.gc();
        System.out.println(strong0);
        System.out.println(s0.get());
        System.out.println(s0.get().get());

        System.out.println("\n\n\nUsing anon class, does NOT hold on to unreferenced local variable:");
        Object strong1 = new Object();
        Supplier<WeakReference<Object>> s1 = anon(strong1);
        System.out.println(strong1);
        System.out.println(s1.get());
        System.out.println(s1.get().get());

        System.out.println("\nnulling the strong reference");
        strong1 = null;
        System.out.println(strong1);
        System.out.println(s1.get());
        System.out.println(s1.get().get());

        System.out.println("\nrequesting a garbage collect");
        System.gc();
        System.out.println(strong1);
        System.out.println(s1.get());
        System.out.println(s1.get().get());

        System.out.println("\n\n\nUsing lambda, does NOT hold on to unreferenced wrapping class instance:");
        Object strong2 = new Object();
        ReferenceFactory factory2 = new ReferenceFactory();
        WeakReference<ReferenceFactory> weak2 = new WeakReference<>(factory2);
        Supplier<Object> s2 = factory2.lambda(strong2);
        System.out.println(strong2);
        System.out.println(factory2);
        System.out.println(weak2);
        System.out.println(weak2.get());
        System.out.println(s2.get());

        System.out.println("\nnulling the factory reference (the wrapping this of the lambda)");
        factory2 = null;
        System.out.println(strong2);
        System.out.println(factory2);
        System.out.println(weak2);
        System.out.println(weak2.get());
        System.out.println(s2.get());

        System.out.println("\nrequesting a garbage collect");
        System.gc();
        System.out.println(strong2);
        System.out.println(factory2);
        System.out.println(weak2);
        System.out.println(weak2.get());
        System.out.println(s2.get());

        System.out.println("\n\n\nUsing anon class, DOES hold on to unreferenced wrapping class instance:");
        Object strong3 = new Object();
        ReferenceFactory factory3 = new ReferenceFactory();
        WeakReference<ReferenceFactory> weak3 = new WeakReference<>(factory3);
        Supplier<Object> s3 = factory3.anon(strong3);
        System.out.println(strong3);
        System.out.println(factory3);
        System.out.println(weak3);
        System.out.println(weak3.get());
        System.out.println(s3.get());

        System.out.println("\nnulling the factory reference (the wrapping this of the lambda)");
        factory3 = null;
        System.out.println(strong3);
        System.out.println(factory3);
        System.out.println(weak3);
        System.out.println(weak3.get());
        System.out.println(s3.get());

        System.out.println("\nrequesting a garbage collect");
        System.gc();
        System.out.println(strong3);
        System.out.println(factory3);
        System.out.println(weak3);
        System.out.println(weak3.get());
        System.out.println(s3.get());
    }

    private static <T> Supplier<WeakReference<T>> lambda(T strong) {
        WeakReference<T> weak = new WeakReference<>(strong);
        return () -> weak;
    }

    private static <T> Supplier<WeakReference<T>> anon(T strong) {
        WeakReference<T> weak = new WeakReference<>(strong);
        return new Supplier<WeakReference<T>>() {
            @Override
            public WeakReference<T> get() {
                return weak;
            }
        };
    }

    private static final class ReferenceFactory {
        public <T> Supplier<T> lambda(T strong) {
            return () -> strong;
        }

        public <T> Supplier<T> anon(T strong) {
            return new Supplier<T>() {
                @Override
                public T get() {
                    return strong;
                }
            };
        }
    }


}
