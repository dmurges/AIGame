package slider;


public class Distance {

    public int DistanceDifference(String board[][],int dimension){

        int result;

        result = ManhattanDistanceH(board,dimension) -
                ManhattanDistanceV(board,dimension);
        return result;
    }

    public int ManhattanDistanceH(String board[][], int dimension){
        int totalDistances = 0;
        int rows = 0;
        boolean flag = false;

        while (rows < dimension){
            for (int i=0; i < dimension;i++){
                if (board[rows][i].equals("H")){
                    for (int j=i; j < dimension;j++){
                        totalDistances++;
                        if ((!board[rows][j].equals("+")) && (flag == false) && (j == (dimension-1))){
                            flag = true;
                            totalDistances++;
                        }
                    }
                }

                flag = false;
            }
            rows++;
        }
        return totalDistances;
    }

    public int ManhattanDistanceV(String board[][],int dimension){
        int totalDistances = 0;
        int rows = 0;
        boolean flag = false;

        while (rows < dimension){
            for (int i=0; i < dimension;i++){
                if (board[rows][i].equals("V")){
                    for (int j=rows; j < dimension ;j++){
                        totalDistances++;
                        if ((!board[j][i].equals("+")) && (flag == false) && (j > 0)){
                            flag = true;
                            totalDistances++;
                        }
                    }
                }
                flag = false;
            }
            rows++;
        }

        return totalDistances;
    }

}
