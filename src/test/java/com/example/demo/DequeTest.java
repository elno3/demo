package com.example.demo;

import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.*;

public class DequeTest {
    @Test
    void name() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ArrayBlockingQueue deque = new ArrayBlockingQueue(5);

        String result = null;
        final Future<String> trying_to_add_over = service.submit(() -> {

                    System.out.println("Thread executing: " + Thread.currentThread().getId());
                    deque.offer("2");
                    deque.add("1");
                    deque.add("1");
                    deque.add("1");
                    deque.add("1");
                    System.out.println("trying to add over");
                    try {
                        deque.offer("1", 2, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);

                    }
                    return "done";
                }
        );
        final String o = trying_to_add_over.get();

        System.out.println("object is: " + o);
        System.out.println("result is: " + result);
        service.shutdown();
        final boolean b = service.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("terminated : " + b);
    }

    @Test
    void name2() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        queue.add("1");

    }
}
