package hc.model;

/**
 * Represents one of the pieces of a happy cube. The face Internally is mapped
 * as a 2D byte array. The piece can be rotated or turned to the opposite side
 * that changes the internal pattern positions.
 * 
 * @author andras
 *
 */
public class Face {

    public static final int LENGTH = 5;

    private static final int ROTATIONS = 4;

    private final String id;

    /** 0..1; value is changed when the piece is turned to the opposite side. */
    private int orientation;

    /** 0..3: value is changed by each rotation. */
    private int rotationIndex;

    private byte[][] matrix;

    public Face(String id, byte[][] matrix) {
	this.id = id;
	this.matrix = matrix;
    }

    public Face(Face face) {
	byte[][] tmp = face.pattern();
	byte[][] array = new byte[tmp.length][];
	for (int i = 0; i < tmp.length; ++i) {
	    array[i] = tmp[i].clone();
	}

	this.id = face.id;
	this.matrix = array;
    }

    public String getLabel() {
	return id + (orientation == 0 ? "" : "'");
    }

    /**
     * Rotates or turns the face depending on the current position.
     * 
     * @return The result of the transformation.
     */
    public Face nextPos() {
	
	if (rotationIndex == ROTATIONS - 1) {
	    turn();
	} else {
	    rotate();
	}
	return this;
    }

    public byte[] topEdge() {
	return matrix[0];
    }

    public byte[] bottomEdge() {
	return matrix[LENGTH - 1];
    }

    public byte[] leftEdge() {
	byte[] left = new byte[LENGTH];
	for (int i = 0; i < LENGTH; ++i) {
	    left[i] = matrix[LENGTH - 1 - i][0];
	}
	return left;
    }

    public byte[] rightEdge() {
	byte[] right = new byte[LENGTH];
	for (int i = 0; i < LENGTH; ++i) {
	    right[i] = matrix[LENGTH - 1 - i][LENGTH - 1];
	}
	return right;
    }

    public byte topLeftCorner() {
	return matrix[0][0];
    }

    public byte topRightCorner() {
	return matrix[0][LENGTH - 1];
    }

    public byte bottomLeftCorner() {
	return matrix[LENGTH - 1][0];
    }

    public byte bottomRightCorner() {
	return matrix[LENGTH - 1][LENGTH - 1];
    }

    /**
     * Rotates a face with 90 degrees.
     */
    void rotate() {
	int length = matrix[0].length;
	for (int i = 0; i < length / 2; i++) {
	    for (int j = i; j < length - i - 1; j++) {
		byte temp = matrix[i][j];
		matrix[i][j] = matrix[length - 1 - j][i];
		matrix[length - 1 - j][i] = matrix[length - 1 - i][length - 1 - j];
		matrix[length - 1 - i][length - 1 - j] = matrix[j][length - 1 - i];
		matrix[j][length - 1 - i] = temp;
	    }
	}
	rotationIndex = rotationIndex == ROTATIONS - 1 ? 0 : ++rotationIndex;
    }

    /**
     * Turns the face to the opposite side.
     */
    void turn() {
	int length = matrix[0].length;
	for (int i = 0; i < length; ++i) {
	    for (int j = 0; j < length / 2; ++j) {
		byte temp = matrix[i][j];
		matrix[i][j] = matrix[i][length - 1 - j];
		matrix[i][length - 1 - j] = temp;
	    }
	}
	orientation = orientation == 0 ? 1 : 0;
	rotationIndex = 0;
    }

    public byte[][] pattern() {
	return matrix;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	for (int i = 0; i < LENGTH; i++) {
	    for (int j = 0; j < LENGTH; j++) {
		if (matrix[i][j] == 0) {
		    builder.append(" ");
		} else {
		    builder.append("o");
		}
	    }
	    builder.append('\n');
	}
	return builder.toString();
    }
}