# AIGame

The game lets 2 agents play the game of slider against each other. The rules are as follows:

One agent controls the H pieces, the other controls the V pieces.
The objective is to get all their pieces to the other side of the board.
The H pieces can only move right, up and down while the V pieces can only move up, left and right.

At runtime the referee will define a number of blocks on the board, which will be less than N -1, where
N equals the dimension of the board.

The job of the agent is to find the smartest way to get all its pieces to the other side of the board.

The Player agent is used as an example if you want feel free to add more sophisticated agents to the program and
let them play each other

# Running
Use your terminal and direct to the AIGame directory.
Use the "javac -d bin/ *java" to compile the program.

If you want to use a 5x5 board for the game, and the Player.java file as an agent then run the program as follows:

java -cp bin slider.Referee 5 slider.Player slider.Player 200

To slow down the print you can add a sleep time at the which is 200 above. This argument is optional and in ms.


# Warning
In some cases the pieces will block each other and the agents won't let each other make a dumb move so
either one wins. This leads to an infinite loop and therefore a draw. To stop the game just press Control+C to terminate.
