package slider;


import java.util.ArrayList;
import java.util.Scanner;


public class Player implements SliderPlayer{

    private Board currentBoard;
    private char globalPlayer;


    public void init(int dimension, String board, char player){
        String gameBoard[][] = new String[dimension][dimension];
        Scanner scanner = new Scanner(board);
        int counter = dimension-1;

        while (scanner.hasNext()){
            for (int i=0; i < dimension;i++){
                gameBoard[counter][i] = scanner.next();
            }
            counter--;
        }
        globalPlayer = player;

        currentBoard = new Board(gameBoard);

    }

    public void update(Move move){

        if (move == null){
            return;
        }

        String[][] board = currentBoard.getBoard();
        //render(board, 5);

        if (move.d == Move.Direction.DOWN){
            board[move.j-1][move.i] = board[move.j][move.i];
            board[move.j][move.i] = "+";
        }

        if ((move.j == currentBoard.getDimension()-1) && (move.d == Move.Direction.UP)){
            board[move.j][move.i] = "+";
        }
        else if (move.d == Move.Direction.UP){
            board[move.j+1][move.i] = board[move.j][move.i];
            board[move.j][move.i] = "+";
        }

        if (move.d == Move.Direction.LEFT){
            board[move.j][move.i-1] = board[move.j][move.i];
            board[move.j][move.i] = "+";
        }

        if ((move.i == currentBoard.getDimension()-1) && (move.d == Move.Direction.RIGHT)){
            board[move.j][move.i] = "+";
        }
        else if (move.d == Move.Direction.RIGHT) {
            board[move.j][move.i+1] = board[move.j][move.i];
            board[move.j][move.i] = "+";
        }

        System.out.println(globalPlayer);
        Distance dist = new Distance();
        System.out.println(dist.ManhattanDistanceH(currentBoard.getBoard(),5));

        //render(board, 5);
        //currentBoard = new Board(board);

    }

    public Move move(){
        ArrayList<Move> moves = possibleMoves(currentBoard,globalPlayer);

        Move bestMove = bestMove(moves,globalPlayer,currentBoard);
        return bestMove;
    }


    public ArrayList<Move> possibleMoves(Board board,char player){
        String[][] tmpBoard = board.getBoard();
        Move move;
        ArrayList<Move> moves = new ArrayList<Move>();



        if (player == 'H') {

            for (int j = 0; j < board.getDimension(); j++) {
                for (int i = 0; i < board.getDimension(); i++) {
                    if (tmpBoard[j][i].equals("H")){

                        if (j==board.getDimension()-1){

                        }
                        else if (tmpBoard[j+1][i].equals("+")){
                            move = new Move(i,j, Move.Direction.UP);
                            moves.add(move);
                        }

                        if (j==0){

                        }
                        else if(tmpBoard[j-1][i].equals("+")){
                            move = new Move(i,j,Move.Direction.DOWN);
                            moves.add(move);
                        }
                        if ((i==board.getDimension()-1)){
                            move = new Move(i,j, Move.Direction.RIGHT);
                            moves.add(move);
                        }
                        else if(tmpBoard[j][i+1].equals("+")){
                            move = new Move(i,j, Move.Direction.RIGHT);
                            moves.add(move);
                        }

                    }

                }
            }
        }

        if (player == 'V') {

            for (int j = 0; j < board.getDimension(); j++) {
                for (int i = 0; i < board.getDimension(); i++) {
                    if (tmpBoard[j][i].equals("V")){
                        if (j==board.getDimension()-1){
                            move = new Move(i,j, Move.Direction.UP);
                            moves.add(move);
                        }
                        else if (tmpBoard[j+1][i].equals("+")){
                            move = new Move(i,j, Move.Direction.UP);
                            moves.add(move);
                        }
                        if (i==0){
                        }
                        else if (tmpBoard[j][i-1].equals("+")){
                            move = new Move(i,j,Move.Direction.LEFT);
                            moves.add(move);
                        }
                        if (i==board.getDimension()-1){

                        }
                        else if (tmpBoard[j][i+1].equals("+")){
                            move = new Move(i,j, Move.Direction.RIGHT);
                            moves.add(move);
                        }
                    }
                }
            }
        }

        if (moves.isEmpty()){
            return null;
        }


        return moves;
    }

    public Move bestMove(ArrayList<Move> moves,char player, Board board){
        int value;
        int bestValue;
        Move bestMove;

        if (moves==null){
            return null;
        }else{
            bestValue = evaluateBestMove(moves.get(0),player,board);
            bestMove = moves.get(0);
            moves.remove(0);
        }

        if (moves==null){
            return bestMove;
        }
        for (Move m : moves){
            value = evaluateBestMove(m,player,board);
            if (bestValue < value){
                bestValue = value;
                bestMove = m;
            }

        }

        boardAfterMove(bestMove,board);
        return bestMove;
    }


    public int evaluateBestMove(Move move,char player,Board board) {
        int value_after_move;
        int current_value;

        current_value = Value(board, player);
        boardAfterMove(move,board);

        value_after_move = Value(board, player);
        undoMove(move,board);

        return (value_after_move - current_value);
    }




    public int Value(Board board,char player){
        int value;
        Distance difference = new Distance();
        if(player == 'H'){
            value = difference.ManhattanDistanceV(board.getBoard(), board.getDimension()) -
                    difference.ManhattanDistanceH(board.getBoard(), board.getDimension());
        }
        else{
            value = difference.ManhattanDistanceH(board.getBoard(), board.getDimension()) -
                    difference.ManhattanDistanceV(board.getBoard(), board.getDimension());
            }

        return value;
    }

    public void boardAfterMove(Move move, Board board){

        String[][] tmpBoard = board.getBoard();

        if (move.d == Move.Direction.DOWN){
            tmpBoard[move.j-1][move.i] = tmpBoard[move.j][move.i];
            tmpBoard[move.j][move.i] = "+";


        }
        if ((move.j == board.getDimension()-1) && move.d == Move.Direction.UP){
            tmpBoard[move.j][move.i] = "+";
        }

        else if (move.d == Move.Direction.UP){
            tmpBoard[move.j+1][move.i] = tmpBoard[move.j][move.i];
            tmpBoard[move.j][move.i] = "+";

        }

        if (move.d == Move.Direction.LEFT){
            tmpBoard[move.j][move.i-1] = tmpBoard[move.j][move.i];
            tmpBoard[move.j][move.i] = "+";

        }
        if ((move.i == board.getDimension()-1) && (move.d == Move.Direction.RIGHT)){
            tmpBoard[move.j][move.i] = "+";
        }
        else if (move.d == Move.Direction.RIGHT){
            tmpBoard[move.j][move.i+1] = tmpBoard[move.j][move.i];
            tmpBoard[move.j][move.i] = "+";
        }


    }

    public void undoMove(Move move, Board board){
        String[][] tmpBoard = board.getBoard();
        String player = "";


        if((move.j==0) && move.d == Move.Direction.DOWN){
            tmpBoard[move.j][move.i] = player.valueOf(globalPlayer);
        }
        else if (move.d == Move.Direction.DOWN){
            tmpBoard[move.j][move.i] = tmpBoard[move.j-1][move.i];
            tmpBoard[move.j-1][move.i] = "+";

        }
        if ((move.j == board.getDimension()-1) && (move.d == Move.Direction.UP)){
            tmpBoard[move.j][move.i] = player.valueOf(globalPlayer);
        }
        else if (move.d == Move.Direction.UP){
            tmpBoard[move.j][move.i] = tmpBoard[move.j+1][move.i];
            tmpBoard[move.j+1][move.i] = "+";

        }
        if (move.i==0){

        }
        else if (move.d == Move.Direction.LEFT){
            tmpBoard[move.j][move.i] = tmpBoard[move.j][move.i-1];
            tmpBoard[move.j][move.i-1] = "+";

        }
        if ((move.i ==board.getDimension()-1) && (move.d == Move.Direction.RIGHT)){
            tmpBoard[move.j][move.i] = player.valueOf(globalPlayer);
        }
        else if (move.d == Move.Direction.RIGHT){
            tmpBoard[move.j][move.i] = tmpBoard[move.j][move.i+1];
            tmpBoard[move.j][move.i+1] = "+";
        }

    }

    /*for testing purposed only*/
    public void render(String[][] board,int x){
        for (int i=x-1; i >= 0; i--){
            for (int j=0; j < x;j++){
                System.out.print(board[i][j] +" ");

            }
            System.out.println();
        }
        System.out.println("+++++++++++++++++++++++++++++");
    }


}




