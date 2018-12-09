package de.pki.minichessclient;

/**
 * Application class to run a game of minichess
 */
public class Game {

    /**
     * Initializes two players with given ki and plays a game of minichess
     * dirty loop stops if a move finishis the game
     *
     * @param args
     */
    public static void main(String[] args) {
        GameState gameState = new GameState();
        PlayerRandom whitePlayer = new PlayerRandom(Color.WHITE);
        PlayerRandom blackPlayer = new PlayerRandom(Color.BLACK);

        while (true) {
            Move nextMove = null;

            if (gameState.getCurrentPlayer() == Color.WHITE) {
                nextMove = whitePlayer.pickMove(gameState.getBoard());
            } else if (gameState.getCurrentPlayer() == Color.BLACK) {
                nextMove = blackPlayer.pickMove(gameState.getBoard());
            }
            System.out.println(nextMove);
            gameState.moveByMove(nextMove);

            System.out.println("------------------\n");
            System.out.println(gameState.getCurrentStateToString() + "\n");
        }
    }
}