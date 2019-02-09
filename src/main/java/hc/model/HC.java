package hc.model;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import hc.util.Permutations;

/**
 * A happy cube composition that contains 6 pieces/faces. Faces positions are
 * being changed when we try to find the final ones. The searching space is
 * defined but is generated on the fly while the solver iterates on it. During
 * the iteration one of the face position is changed or a new face folding is
 * generated.
 * 
 * @author andras
 *
 */
public class HC implements Iterable<Folding> {

	private final List<Face> faces;

	private static final int NUMBER_OF_FACES = 6;

	public HC(List<Face> faces) {
		Objects.requireNonNull(faces);
		if (faces.size() != NUMBER_OF_FACES) {
			throw new IllegalArgumentException("Cube must have 6 faces");
		}
		this.faces = faces;
	}

	@Override
	public Iterator<Folding> iterator() {
		return new SearchSpaceIterator();
	}

	private class SearchSpaceIterator implements Iterator<Folding> {

		/** Number of rotation can be executed within a permutation. In our case it is 8^6 */
		private final int rotations;

		/** Total number of cases that can be checked. In our case it is 8^6 * 6! */
		private final long total;

		private List<Face> work;

		/** The current execution number. */
		private int current;

		public SearchSpaceIterator() {
			long permutations = Permutations.factorial(NUMBER_OF_FACES);
			this.rotations = Double.valueOf(Math.pow(8, NUMBER_OF_FACES)).intValue();
			this.total = permutations * rotations;
		}

		@Override
		public boolean hasNext() {
			return current < total;
		}

		@Override
		public Folding next() {
			// Each piece can be rotated or turned 8 times
			// So the total is 8^6; if all of the are tried
			// the pieces are reordered and continue...
			if (work == null) {
				nextPermutation();
			} else {
				if (current % rotations == 0) {
					nextPermutation();
				} else {
					work.get(current % NUMBER_OF_FACES).nextPos();
				}
				++current;
			}
			return new BaseFolding(work);
		}
		
		/**
		 * We have to use the original faces for the next permutation generation.
		 * A face rotation changes its internal state, so we clone the faces and working on those.
		 * Creates a clone of the original faces.
		 */
		private void nextPermutation() {
			work = Permutations.permutation(current / rotations, faces)
					.stream()
					.map(Face::new)
					.collect(Collectors.toList());
		}
	}
}