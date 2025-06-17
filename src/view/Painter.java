package view;
import control.mouseListener;
import model.Cell;
import model.GameBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class Painter extends JPanel {
    private final GameBoard board;

    public Painter(GameBoard board) {
        this.board = board;
        this.setFocusable(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        System.out.println("Paint Component redrew the screen...");
        update();
    }

    public void canvas() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);


        for (int i = 0; i < this.board.getHeight(); i++) {
            for (int j = 0; j < this.board.getWidth(); j++) {
                JLabel label = new JLabel();

                //Generating the buttons
                if (board.cellExist(i, j)) {

                    label.putClientProperty("y", i);
                    label.putClientProperty("x", j);
                    label.setPreferredSize(new Dimension(25, 25));
                    label.setOpaque(true);
                    label.addMouseListener(new mouseListener(this.board));
                    label.addMouseMotionListener(new mouseListener(this.board));

                    if (!board.getState(i, j)) {
                        label.setBackground(Color.BLACK);
                        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    } else {
                        label.setBackground(Color.WHITE);
                        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                    }

                    gridBagConstraints.gridy = i;
                    gridBagConstraints.gridx = j;

                    //For resizing the window
                    gridBagConstraints.weightx = 1;
                    gridBagConstraints.weighty = 1;
                    gridBagConstraints.fill = GridBagConstraints.BOTH;

                    //Adding the button to the container
                    gridBagLayout.setConstraints(label, gridBagConstraints);
                    this.board.getLabels()[i][j] = label;
                    this.add(label);
                }
            }
        }
    }

    public void update() {
        while (!this.board.getChangedCells().isEmpty()) {
            Cell cell = this.board.getChangedCells().pop();
            this.board.getLabels()[cell.getY()][cell.getX()].setBackground(cell.getState() ? Color.WHITE : Color.BLACK);
            this.board.getLabels()[cell.getY()][cell.getX()].setBorder(BorderFactory.createLineBorder(cell.getState() ? Color.WHITE : Color.BLACK, 1));
        }
        repaint();
    }

}
