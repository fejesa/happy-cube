package hc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hc.model.Folding;
import hc.model.HC;

/**
 * Find the solutions of the Happy Cube.
 * 
 * @author andras
 *
 */
public class Solver {

    private final HC cube;
    private final Path path;

    public Solver(HC cube, Path path) {
	this.cube = cube;
	this.path = path;
    }

    /**
     * Finds a solution of the problem and writes the result to the given
     * file.
     * 
     * @return The result or null if not found.
     * @throws IOException If the write operation is failed.
     */
    public Folding findAny() throws IOException {
	Folding result = null;
	for (Iterator<Folding> i = cube.iterator(); i.hasNext();) {
	    Folding folding = i.next();
	    if (folding.isAllEdgesValid() && folding.isAllCornersValid()) {
		write(folding);
		result = folding;
		break;
	    }
	}
	return result;
    }

    /**
     * Finds all solutions of the problem, writes them into the given file.
     * Current implementation does not support unique solution finding.
     * 
     * @return List of all solutions.
     * @throws IOException If the write operation is failed.
     */
    public List<Folding> findAll() throws IOException {
	List<Folding> results = new ArrayList<>();
	for (Iterator<Folding> i = cube.iterator(); i.hasNext();) {
	    Folding folding = i.next();
	    if (folding.isAllEdgesValid() && folding.isAllCornersValid()) {
		write(folding);
	    }
	}
	return results;
    }

    private void write(Folding folding) throws IOException {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));) {
	    writer.newLine();
	    writer.newLine();
	    writer.write(folding.toString());
	}
    }
}