import model.Game;
import view.GUI;
import view.printBoard;


public class main {
    public static void main(String[] args) {
        Game game = new Game(64, 64);
        game.gameLoop();
    }
}

