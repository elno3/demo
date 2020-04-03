package com.example.demo;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CollectionTest {
	@Test
	void name() {
		List<Person> strings = Arrays.asList(new Person(5, "one"), new Person(4, "two"));
		strings.get(0).age = 60;
		final List<Person> finalList = Collections.unmodifiableList(strings);
		// strings.get(0);
		finalList.get(0).age = 70;
		assertEquals("", finalList.get(0).age, 70);

	}

	@Test
	void name2() {
		final var names = new ArrayList<>(List.of("Tim", "Tom", "Mike", "Peter", "Tom", "Tim"));

		final var immutableNames = names.stream().collect(Collectors.toUnmodifiableList());

		System.out.println("immutableNames type: " + immutableNames.getClass());

		final var uniqueImmutableNames = names.stream().collect(Collectors.toUnmodifiableSet());

		System.out.println("uniqueImmutableNames content: " + uniqueImmutableNames);

		System.out.println("uniqueImmutableNames type: " + uniqueImmutableNames.getClass());
	}

	@Test
	void name3() {
		WeakHashMap<String, String> map = new WeakHashMap<>();
		Person p = new Person(4, "vv");
		String s = new String("imageName");
		map.put(s, "bigImage");
		assertTrue(map.containsKey(s));

		s = null;
		System.gc();

		await().atMost(10, TimeUnit.SECONDS).until(() -> map.isEmpty());
	}

	@Data
	@AllArgsConstructor
	public static class Person {
		int age;
		String name;
	}

	@Test
	public void givenHashMap_whenSumParallel_thenError() throws Exception {
		Map<String, Integer> map = new HashMap<>();
		List<Integer> sumList = parallelSum100(map, 100);

		assertNotEquals(1, sumList.stream().distinct().count());
		long wrongResultCount = sumList.stream().filter(num -> num != 100).count();

		assertTrue(wrongResultCount > 0);

	}

	List<Integer> parallelSum100(Map<String, Integer> map, int executionTimes) throws InterruptedException {
		List<Integer> sumList = new ArrayList<>(1000);
		for (int i = 0; i < executionTimes; i++) {
			ExecutorService executorService = Executors.newFixedThreadPool(4);
			map.put("test", 0);
			for (int j = 0; j < 100; j++) {
				executorService.execute(() -> {
					map.computeIfPresent("test", (key, value) -> value + 1);
				});
			}
			executorService.shutdown();
			executorService.awaitTermination(5, TimeUnit.SECONDS);
			sumList.add(map.get("test"));
		}
		return sumList;
	}

	@Test
	public void givenConcurrentMap_whenSumParallel_thenCorrect() throws Exception {
		Map<String, Integer> map = new ConcurrentHashMap<>();
		List<Integer> sumList = parallelSum100(map, 1000);
		assertEquals(1, sumList.stream().distinct().count());
		long wrongResultCount = sumList.stream().filter(num -> num != 100).count();

		assertEquals(0, wrongResultCount);
	}

	@Test
	void name4() {
		BlockingDeque<String> deque = new LinkedBlockingDeque<>();
	}

	@Test
	void name5() {
		Queue<Person> queue = new ConcurrentLinkedDeque<>();
		queue.add(new Person(1, "a"));
		queue.add(new Person(2, "b"));

		final Person element = queue.element();
		final Person peek = queue.peek();
		peek.age = 30;
		// final Person poll = queue.poll();
		// poll.age = 50;
		queue.add(new Person(3, "c"));
		queue.offer(new Person(4, "c"));
		System.out.println(queue);
	}

	@Test
	void name6() {
		Deque<String> deque = new ArrayDeque<>();
	}
}
