package hc;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import hc.model.BaseFolding;
import hc.model.Face;
import hc.model.HC;
import junit.framework.Assert;

public class SolverTest {

    @Test
    public void firstShotBlue() throws IOException {
	HC<BaseFolding> cube = new HC<>(createBlueFaces(), BaseFolding.class);
	Solver solver = new Solver(cube, Paths.get("solver_first_shot_blue.txt"));
	Assert.assertNotNull(solver.findAny());
    }

    @Test
    public void rotateOneFaceBlue() throws IOException {
	List<Face> faces = createBlueFaces();
	Face face = new Face(faces.get(2).nextPos());
	faces.set(2, face);
	HC<BaseFolding> cube = new HC<>(faces, BaseFolding.class);
	Solver solver = new Solver(cube, Paths.get("solver_rotate_one_face_blue.txt"));
	Assert.assertNotNull(solver.findAny());
    }
    
    @Test
    public void redFaces() throws IOException {
	List<Face> faces = createRedFaces();
	HC<BaseFolding> cube = new HC<>(faces, BaseFolding.class);
	Solver solver = new Solver(cube, Paths.get("solver_red.txt"));
	Assert.assertNotNull(solver.findAny());
    }
    
    private List<Face> createRedFaces() {
	byte[][] FRONT = new byte[][] {
	    {1,1,0,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {0,1,1,0,0}
	};

	byte[][] DOWN = new byte[][] {
	    {0,0,1,0,0},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {0,0,1,0,0}
	};

	byte[][] RIGHT = new byte[][] {
	    {1,1,0,1,1},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {0,1,1,1,1},
	    {1,1,0,1,0}
	};

	byte[][] UP = new byte[][] {
	    {0,0,1,0,1},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,1,0,0}
	};

	byte[][] BACK = new byte[][] {
	    {0,1,0,1,0},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {0,1,0,0,0}
	};

	byte[][] LEFT = new byte[][] {
	    {0,0,0,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {0,1,0,1,1}
	};
	return Arrays.asList(
		new Face("UP", UP),
		new Face("DOWN", DOWN),
		new Face("BACK", BACK),
		new Face("FRONT", FRONT),
		new Face("RIGHT", RIGHT),
		new Face("LEFT", LEFT));

    }
    
    private List<Face> createBlueFaces() {
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