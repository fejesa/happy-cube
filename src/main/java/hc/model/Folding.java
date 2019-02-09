package hc.model;

/**
 * Represents a folding of a cube.
 * There are 11 combinations to make a cube without reversing them.
 * Implementation of this interface represents a net.
 *
 * @author andras
 *
 */
public interface Folding {

	/**
	 * Checks all 12 edges of a cube.
	 * @return <tt>true</tt> if all edge is valid
	 */
	boolean isAllEdgesValid();
	
	/**
	 * Checks all 8 corners of a cube. 
	 * @return <tt>true</tt> if all corner is valid
	 */
	boolean isAllCornersValid();
}
