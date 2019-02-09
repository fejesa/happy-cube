package hc.model;

import org.junit.Test;

import junit.framework.Assert;

public class FaceTest {

    @Test
    public void rotate() {
	Face face = new Face("0", createMatrix());
	String start = face.toString();
	System.out.println(start);
	for (int i = 0; i < 4; ++i) {
	    face.rotate();
	    System.out.println(face);
	}
	String end = face.toString();
	Assert.assertEquals(start, end);
    }

    @Test
    public void turn() {
	Face face = new Face("0", createMatrix());
	String start = face.toString();
	System.out.println(start);

	face.turn();
	System.out.println(face);

	face.turn();

	String end = face.toString();
	Assert.assertEquals(start, end);
    }

    @Test
    public void cycle() {
	Face face = new Face("0", createMatrix());
	String start = face.toString();
	for (int i = 1; i <= 3 * 8; ++i) {
	    face.nextPos();
	    if (i % 8 == 0) {
		Assert.assertTrue(start.equals(face.toString()));
	    } else {
		Assert.assertFalse(start.equals(face.toString()));
	    }
	}
    }

    private byte[][] createMatrix() {
	return new byte[][] {
	    {0,1,0,1,0},
	    {1,1,1,1,0},
	    {0,1,1,1,1},
	    {1,1,1,1,0},
	    {1,1,0,1,1}
	};
    }
}