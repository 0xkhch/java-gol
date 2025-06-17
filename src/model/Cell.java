package model;

public class Cell {
    private boolean state;
    private boolean nextState;
    private final int y;
    private final int x;

    public Cell(int y, int x, boolean cellState) {
        this.state = cellState;
        this.y = y;
        this.x = x;
    }

    public boolean getState() {
        return this.state;
    }

    public boolean getNextState() {
        return this.nextState;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setNextState(boolean state) {
        this.nextState = state;
    }
}
