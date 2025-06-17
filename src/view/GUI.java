package view;

import control.mouseListener;
import model.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class GUI extends JFrame implements Observer {
    GameBoard board;
    Painter painter;

    public GUI(GameBoard board) {
        super("Life2D");
        this.board = board;
        board.addObserver(this);

        this.painter = new Painter(board);
        this.painter.canvas();


        this.setBackground(Color.ORANGE);
        this.setContentPane(painter);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("GUI updated");
        this.painter.revalidate();
        this.painter.repaint();
    }

}
