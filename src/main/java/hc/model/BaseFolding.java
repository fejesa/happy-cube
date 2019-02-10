package hc.model;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the following folding structure:</br>
 *     +---+
 *     |BA |
 *     |CK |
 *     |   |
 * +---+---+---+---+
 * |LE |DO |RI |UP |
 * |FT |WN |GHT|   |
 * |   |   |   |   |
 * +---+---+---+---+
 *     |FR |
 *     |ONT|
 *     |   |
 *     +---+
 * @author andras
 *
 */
public class BaseFolding implements Folding {

    private static final int DOWN = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int BACK = 3;
    private static final int FRONT = 4;
    private static final int LEFT = 5;

    private final List<Face> faces;

    public BaseFolding(List<Face> faces) {
	this.faces = faces;
    }

    @Override
    public boolean isAllEdgesValid() {
	if (notJoinedEdge(left().bottomEdge(), down().topEdge())) {
	    return false;
	}
	if (notJoinedEdge(down().bottomEdge(), right().topEdge())) {
	    return false;
	}

	if (notJoinedEdge(right().bottomEdge(), up().topEdge())) {
	    return false;
	}
	if (notJoinedEdge(up().bottomEdge(), left().topEdge())) {
	    return false;
	}
	if (notJoinedEdge(left().rightEdge(), back().topEdge())) {
	    return false;
	}
	if (notJoinedEdge(left().leftEdge(), front().topEdge())) {
	    return false;
	}

	if (notJoinedEdge(right().leftEdge(), back().bottomEdge())) {
	    return false;
	}
	if (notJoinedEdge(right().leftEdge(), front().topEdge())) {
	    return false;
	}
	if (notJoinedEdge(up().rightEdge(), back().rightEdge())) {
	    return false;
	}
	if (notJoinedEdge(up().leftEdge(), front().leftEdge())) {
	    return false;
	}
	if (notJoinedEdge(down().rightEdge(), back().leftEdge())) {
	    return false;
	}
	return notJoinedEdge(down().leftEdge(), front().rightEdge());
    }

    @Override
    public boolean isAllCornersValid() {
	if (notValidCorner(left().topRightCorner(), up().bottomRightCorner(), back().topRightCorner())) {
	    return false;
	}
	if (notValidCorner(left().topLeftCorner(), up().bottomLeftCorner(), front().topLeftCorner())) {
	    return false;
	}
	if (notValidCorner(back().bottomRightCorner(), right().bottomRightCorner(), up().topRightCorner())) {
	    return false;
	}
	if (notValidCorner(front().bottomLeftCorner(), right().bottomLeftCorner(), up().topLeftCorner())) {
	    return false;
	}
	if (notValidCorner(left().bottomLeftCorner(), down().topLeftCorner(), front().topRightCorner())) {
	    return false;
	}
	if (notValidCorner(left().bottomRightCorner(), down().topRightCorner(), back().topLeftCorner())) {
	    return false;
	}
	if (notValidCorner(front().bottomRightCorner(), down().bottomLeftCorner(), right().topLeftCorner())) {
	    return false;
	}
	return notValidCorner(down().bottomRightCorner(), back().bottomLeftCorner(), right().topRightCorner());
    }

    /**
     * A corner is valid if the given corners of the three joined faces have no collision.
     * In this case the sum of the three given values must be 1. 
     * @return <tt>true</tt> if there is a collision.
     */
    private boolean notValidCorner(byte a, byte b, byte c) {
	return a + b + c != 1;
    }

    /**
     * An edge is valid if the given edges of the two faces can be joined without overlapping.
     * For example in one edge is mapped as {0,1,0,1,0} and the other is {1,0,1,0,0} then they can be joined. 
     * @return <tt>true</tt> if the two edges cannot be joined.
     */
    private boolean notJoinedEdge(byte[] a, byte[] b) {
	for (int i = 1; i < 4; ++i) {
	    if (a[i] + b[i] != 1) {
		return true;
	    }
	}
	return false;
    }

    private Face left() {
	return faces.get(LEFT);
    }

    private Face right() {
	return faces.get(RIGHT);
    }

    private Face up() {
	return faces.get(UP);
    }

    private Face down() {
	return faces.get(DOWN);
    }

    private Face back() {
	return faces.get(BACK);
    }

    private Face front() {
	return faces.get(FRONT);
    }

    @Override
    public String toString() {
	Face blank = new Face("", new byte[][] {
	    { 0, 0, 0, 0, 0 },
	    { 0, 0, 0, 0, 0 },
	    { 0, 0, 0, 0, 0 },
	    { 0, 0, 0, 0, 0 },
	    { 0, 0, 0, 0, 0 } });

	StringBuilder builder = new StringBuilder();
	appendContent(builder, Arrays.asList(blank, left()));
	appendContent(builder, Arrays.asList(front(), down(), back()));
	appendContent(builder, Arrays.asList(blank, right()));
	appendContent(builder, Arrays.asList(blank, up()));

	return builder.toString();
    }

    private void appendContent(StringBuilder builder, List<Face> faces) {
	for (int line = 0; line < Face.LENGTH; ++line) {
	    for (int i = 0; i < faces.size(); ++i) {
		byte[][] pattern = faces.get(i).pattern();
		for (int j = 0; j < Face.LENGTH; j++) {
		    if (pattern[line][j] == 0) {
			builder.append(" ");
		    } else {
			builder.append("o");
		    }
		}
	    }
	    builder.append('\n');
	}
    }
}
