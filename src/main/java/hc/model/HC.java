package hc.model;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import hc.util.Permutations;

/**
 * A happy cube composition that contains 6 pieces/faces in any order and
 * position. Faces positions are being changed when we try to find the final
 * ones. The searching space is defined but is generated on the fly while the
 * solver iterates on it. During the iteration the position of the one of the
 * faces is changed or a new face folding is generated.
 * 
 * @author andras
 *
 * @param <F> {@link Folding} type
 */
public class HC<F extends Folding> implements Iterable<F> {

    private static final int NUMBER_OF_FACES = 6;
    
    /** List of cube faces that must be checked. */
    private final List<Face> faces;

    /** The type of the folding instance that will be generated. */
    private final Class<F> type;

    public HC(List<Face> faces, Class<F> type) {
	Objects.requireNonNull(faces);
	if (faces.size() != NUMBER_OF_FACES) {
	    throw new IllegalArgumentException("Cube must have 6 faces");
	}
	this.faces = faces;
	this.type = type;
    }

    @Override
    public Iterator<F> iterator() {
	return new SearchSpaceIterator();
    }

    private class SearchSpaceIterator implements Iterator<F> {

	/**
	 * Number of rotations can be executed within a permutation. In our case it is
	 * 8^6
	 */
	private final int rotations;

	/** Total number of cases that can be checked. In our case it is 8^6 * 6! */
	private final long total;

	private List<Face> work;

	/** The current execution number. */
	private int current;

	public SearchSpaceIterator() {
	    long permutations = Permutations.factorial(NUMBER_OF_FACES);
	    this.rotations = (int) Math.pow(8, NUMBER_OF_FACES);
	    this.total = permutations * rotations;
	}

	@Override
	public boolean hasNext() {
	    return current < total;
	}

	@Override
	public F next() {
	    if (!hasNext()) {
		throw new NoSuchElementException();
	    }
	    // Each piece can be rotated or turned 8 times
	    // So the total is 8^6; if all of the are tried
	    // the pieces are reordered and continue...
	    if (current % rotations == 0) {
		nextPermutation();
	    } else {
		work.get(current % NUMBER_OF_FACES).nextPos();
	    }
	    ++current;

	    try {
		F folding = type.newInstance();
		folding.setFaces(work);
		return folding;
	    } catch (InstantiationException | IllegalAccessException e) {
		throw new IllegalArgumentException("Unsupported folding type");
	    }
	}

	/**
	 * Creates a clone of the original faces.</br>
	 * We have to use the original faces for the next permutation generation. A face
	 * rotation changes its internal state, so we clone the faces and work on
	 * those ones.
	 */
	private void nextPermutation() {
	    work = Permutations
		    .permutation(current / rotations, faces)
		    .stream()
		    .map(Face::new)
		    .collect(Collectors.toList());
	}
    }
}