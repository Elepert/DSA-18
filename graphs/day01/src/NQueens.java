import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    public static boolean checkColumn(ArrayList<Integer> column, int c) {
        for (int i = 0; i< column.size();i++){
            if (column.get(i) == c) {
                return true;
            }
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        ArrayList<char[][]> answers = new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();
        char[][] initBoard = new char[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                initBoard[i][j] = '.';
            }
        }
        Queens(0, n, initBoard, columns, answers);
        return answers;
    }

    public static ArrayList<char[][]> Queens(int row, int n, char[][] board, ArrayList<Integer> column, ArrayList<char[][]> answers) {
        if (row >= n ){
            answers.add(copyOf(board));
            return answers;
        }
        for (int i = 0; i < n; i++){
            if (!checkDiagonal(board, row, i) && !checkColumn(column, i)){
                board[row][i] = 'Q';
                column.add(i);
                Queens(row+1, n, board, column, answers);
                column.remove(Integer.valueOf(i));
            }
            board[row][i] = '.';
        }

        return answers;
    }

    public static void printAllBoards(ArrayList<char[][]> boards){
        for (int i = 0; i< boards.size(); i++){
            printBoard(boards.get(i), boards.get(i).length);
            System.out.println("--------------");
        }
    }

    public static void printBoard(char[][] board, int n){
        for (int i = 0; i<n; i++){
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
