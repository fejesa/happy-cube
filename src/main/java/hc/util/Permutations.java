package hc.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Generates permutation from a given list of type.
 *
 */
public class Permutations {

	private Permutations() {}

	public static <T> Stream<List<T>> generate(List<T> items) {
		long permutations = Permutations.factorial(items.size());
		return LongStream
				.range(0, permutations)
				.mapToObj(i -> Permutations.permutation(i, items));
	}

	public static long factorial(int n) {
		if (n > 10 || n < 0) {
			throw new IllegalArgumentException(n + " is out of range");
		}
		return LongStream
				.rangeClosed(2, n)
				.reduce(1, (a, b) -> a * b);
	}

	public static <T> List<T> permutation(long no, List<T> items) {
		return helper(no, new LinkedList<>(Objects.requireNonNull(items)), new ArrayList<>());
	}

	private static <T> List<T> helper(long no, LinkedList<T> in, List<T> out) {
		if (in.isEmpty()) {
			return out;
		}
		long subFactorial = factorial(in.size() - 1);
		out.add(in.remove((int) (no / subFactorial)));
		return helper((int) (no % subFactorial), in, out);
	}
}