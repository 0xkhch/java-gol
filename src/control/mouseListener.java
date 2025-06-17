package control;

import model.GameBoard;
import view.printBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;



public class mouseListener extends Observable implements MouseListener, MouseMotionListener {
    private final GameBoard board;
    private static boolean isDragging;

    public mouseListener(GameBoard board) {
        this.board = board;
        isDragging = false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2) {
            isDragging = !isDragging;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON3) {
            this.board.setOngoing(true);
        }

        else if (e.getButton() == MouseEvent.BUTTON1) {
            board.setState(
                    (int) ((JLabel) e.getSource()).getClientProperty("y"),
                    (int) ((JLabel) e.getSource()).getClientProperty("x"),
                    !board.getState(
                            (int) ((JLabel) e.getSource()).getClientProperty("y"),
                            (int) ((JLabel) e.getSource()).getClientProperty("x")
                    ));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            //System.out.println("Right Mouse released");
            this.board.setOngoing(false);
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            isDragging = false;
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isDragging) {
            board.setState(
                    (int) ((JLabel) e.getSource()).getClientProperty("y"),
                    (int) ((JLabel) e.getSource()).getClientProperty("x"),
                    !board.getState(
                            (int) ((JLabel) e.getSource()).getClientProperty("y"),
                            (int) ((JLabel) e.getSource()).getClientProperty("x")
            ));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
