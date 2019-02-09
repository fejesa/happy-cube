package hc;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import hc.model.Face;
import hc.model.HC;
import junit.framework.Assert;

public class SolverTest {

    @Test
    public void firstShot() throws IOException {
	HC cube = new HC(createFaces());
	Solver solver = new Solver(cube, Paths.get("solver_first_shot.txt"));
	Assert.assertNotNull(solver.findAny());
    }

    @Test
    public void rotateOneFace() throws IOException {
	List<Face> faces = createFaces();
	faces.get(2).nextPos();
	HC cube = new HC(faces);
	Solver solver = new Solver(cube, Paths.get("solver_rotate_one_face.txt"));
	Assert.assertNotNull(solver.findAny());
    }

    private List<Face> createFaces() {
	byte[][] FRONT = new byte[][] {
	    {0,0,1,0,0},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {0,0,1,0,0}
	};

	byte[][] RIGHT = new byte[][] {
	    {1,0,1,0,1},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {1,0,1,0,1}
	};

	byte[][] BACK = new byte[][] {
	    {0,0,1,0,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {0,0,1,0,0}
	};

	byte[][] UP = new byte[][] {
	    {0,1,0,1,0},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {1,1,0,1,0}
	};

	byte[][] DOWN = new byte[][] {
	    {1,0,1,0,0},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,0,1,0}
	};

	byte[][] LEFT = new byte[][] {
	    {0,0,1,0,1},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,0,1,1}
	};

	return Arrays.asList(
		new Face("DOWN", DOWN),
		new Face("RIGHT", RIGHT),
		new Face("UP", UP),
		new Face("BACK", BACK),
		new Face("FRONT", FRONT),
		new Face("LEFT", LEFT));
    }
}
