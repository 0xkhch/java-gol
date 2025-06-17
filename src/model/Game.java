package model;

import view.GUI;
import view.printBoard;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class Game {
    private GameBoard board;
    private GUI display;

    public Game(int height, int width) {
        this.board = new GameBoard(height, width);
         this.display = new GUI(board);
    }

    public GameBoard getBoard() {
        return this.board;
    }

    public void gameLoop() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            board.updateCells();
            //new printBoard(board);
        }
    }
}
