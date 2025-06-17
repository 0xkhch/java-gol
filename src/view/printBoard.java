package view;
import model.GameBoard;

public class printBoard {
    public printBoard(GameBoard board) {
        for (int i = 0; i < board.getHeight(); i++) {
            System.out.print("[ ");
            for (int j = 0; j < board.getWidth(); j++) {
                System.out.print(board.getState(i, j) + " ");
            }
            System.out.print("]");
            System.out.println();
        }
        System.out.println();
    }
}
