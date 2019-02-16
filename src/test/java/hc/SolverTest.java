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
	byte[][] F1 = new byte[][] {
	    {1,1,0,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {0,1,1,0,0}
	};

	byte[][] F2 = new byte[][] {
	    {0,0,1,0,0},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {0,0,1,0,0}
	};

	byte[][] F3 = new byte[][] {
	    {1,1,0,1,1},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {0,1,1,1,1},
	    {1,1,0,1,0}
	};

	byte[][] F4 = new byte[][] {
	    {0,0,1,0,1},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,1,0,0}
	};

	byte[][] F5 = new byte[][] {
	    {0,1,0,1,0},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {0,1,0,0,0}
	};

	byte[][] F6 = new byte[][] {
	    {0,0,0,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {0,1,0,1,1}
	};
	return Arrays.asList(
		new Face("F1", F1),
		new Face("F4", F4),
		new Face("F2", F2),
		new Face("F5", F5),
		new Face("F3", F3),
		new Face("F6", F6));

    }

    private List<Face> createBlueFaces() {
	byte[][] F1 = new byte[][] {
	    {0,0,1,0,0},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {0,0,1,0,0}
	};

	byte[][] F3 = new byte[][] {
	    {1,0,1,0,1},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {1,0,1,0,1}
	};

	byte[][] F5 = new byte[][] {
	    {0,0,1,0,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {0,0,1,0,0}
	};

	byte[][] F4 = new byte[][] {
	    {0,1,0,1,0},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {1,1,0,1,0}
	};

	byte[][] F2 = new byte[][] {
	    {1,0,1,0,0},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,0,1,0}
	};

	byte[][] F6 = new byte[][] {
	    {0,0,1,0,1},
	    {1,1,1,1,1},
	    {0,1,1,1,0},
	    {1,1,1,1,1},
	    {0,1,0,1,1}
	};

	return Arrays.asList(
		new Face("F1", F1),
		new Face("F2", F2),
		new Face("F3", F3),
		new Face("F4", F4),
		new Face("F5", F5),
		new Face("F6", F6));
    }
}