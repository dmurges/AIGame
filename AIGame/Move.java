
package slider;


public class Move {


	public enum Direction { UP, DOWN, LEFT, RIGHT }

	/** The board position to which this move applies. For detailed information 
	 * about the coordinate system, see the Rules of the Game specification */
	public final int i, j;
	/** The direction in which the piece is to be moved by this move. */
	public final Move.Direction d;

	/** Create a new move for a given position (i, j) in a given direction d */
	public Move(int i, int j, Move.Direction d) {
		this.i = i;
		this.j = j;
		this.d = d;
	}

	@Override
	public String toString() {
		return "("+i+","+j+"): " + d;
	}
}

