package slider;


import java.util.ArrayList;


public class Board {


    private String[][] board;

    public Board(String[][] board){

        this.board = board;

    }


    public int getDimension(){
        return board[0].length;
    }

    public String[][] getBoard(){
        return board;
    }

    public void setBoard(String[][] board){
        this.board = board;
    }




}
