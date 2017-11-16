
package slider;

public interface SliderPlayer {


	public void init(int dimension, String board, char player);

	public void update(Move move);

	public Move move();
}
