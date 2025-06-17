package model;

import javax.swing.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import java.util.Stack;

public class GameBoard extends Observable {
    private boolean Ongoing;
    private final int Height;
    private final int Width;
    private Cell[][] gameBoard;
    private JLabel[][] labels;
    private Stack<Cell> changedCells;

    public GameBoard(int Height, int Width) {
        this.Ongoing = false;
        this.Height = Height;
        this.Width = Width;
        this.gameBoard = new Cell[Height][Width];
        this.labels = new JLabel[Height][Width];
        this.changedCells = new Stack<>();
        initBoard();
    }

    public boolean isOngoing () {
        return this.Ongoing;
    }
    public int getWidth() {
        return this.Width;
    }

    public int getHeight() {
        return this.Height;
    }

    public boolean cellExist(int y, int x) {
        return y < this.Height && x < this.Width && y >= 0 && x >= 0;
    }

    public Cell getCell(int y, int x) {
        return this.gameBoard[y][x];
    }


    public JLabel[][] getLabels() {
        return this.labels;
    }

    public Stack<Cell> getChangedCells() {
        return this.changedCells;
    }

    public boolean getState(int y, int x) {
        return this.gameBoard[y][x].getState();
    }

    public void setState(int y, int x, boolean state) {
        this.gameBoard[y][x].setState(state);
        this.changedCells.add(this.gameBoard[y][x]);

    }

    public void setOngoing(boolean state) {
        this.Ongoing = state;
    }

    public void initBoard() {
        for (int i = 0; i < this.Height; i++) {
            for (int j = 0; j < this.Width; j++) {
                int random = (int)Math.round(Math.random());
                boolean state = random == 1;
                gameBoard[i][j] = new Cell(i, j, state);
            }
        }
    }

    public boolean nextCellState(boolean state, int aliveCount) {
        return (state && (aliveCount == 2 || aliveCount == 3)) || (!state && aliveCount == 3);
    }

    public int checkNeighbour(int y, int x) {
        int startY = Math.max(y - 1, 0), endY = Math.min(y + 1, this.Height - 1),
                startX = Math.max(x - 1, 0), endX = Math.min(x + 1, this.Width - 1);

        int aliveCount = 0;
        for (int i = startY; i <= endY; i++) {
            for (int j = startX; j <= endX; j++) {
                if (i == y && j == x) {
                    continue;
                }

                if (this.getState(i, j)) {
                    aliveCount++;
                }
            }
        }

        return aliveCount;
    }

    public void updateCells() {
        if (Ongoing) {
            Cell[][] tmp = new Cell[this.Height][this.Width];
            for (int i = 0; i < this.Height; i++) {
                for (int j = 0; j < this.Width; j++) {
                    boolean nextState = nextCellState(getState(i, j), checkNeighbour(i, j));
                    if (getState(i, j) != nextState || getState(i, j) == nextState) {
                        changedCells.add(this.gameBoard[i][j]);
                    }
                    tmp[i][j] = new Cell(i, j, nextState);
                }
            }
            this.gameBoard = tmp;
            setChanged();
            notifyObservers();
        }
    }
}
