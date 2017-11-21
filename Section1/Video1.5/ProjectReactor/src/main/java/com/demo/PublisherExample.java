package com.demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PublisherExample {
	static void fluxSubscriber(){
		Flux<Integer> seq1 = Flux.range(1, 3);
		List<String> iterable = Arrays.asList("foo", "bar", "foobar");
		Flux<String> seq2 = Flux.fromIterable(iterable);
		seq1.subscribe(i -> System.out.println(i));
		seq2.subscribe(i -> System.out.println(i));
		
		Flux<String> flux = Flux.just("{A}", "{B}", "{C}");
		Flux<String> intervalFlux3 = Flux
									.interval(Duration.ofMillis(500))
									.zipWith(flux, (i, item) -> "item " + i + ": " + item);
		
		intervalFlux3.subscribe(System.out::println);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void monoSubscriber(){
		Mono<String> noData = Mono.empty(); 
		Mono<String> data = Mono.just("foo");
		data.subscribe(i -> System.out.println(i),
			    error -> System.err.println("Error " + error),
			    () -> {System.out.println("Done");});
		
		/*Error signals and completion signals are both terminal events and are exclusive of one another (you never get both). To make the completion consumer work, we must take care not to trigger an error.

		The completion matcher is a pair of empty parentheses because it matches the run method in the Runnable interface, which has no parameters. The preceding code produces the following output:*/
	}
}
