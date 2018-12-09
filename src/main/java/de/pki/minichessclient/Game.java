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
        PlayerRandom whitePlayer = new PlayerRandom('W');
        PlayerRandom blackPlayer = new PlayerRandom('B');

        while (true) {
            Move nextMove = null;

            if (gameState.getCurrentPlayer() == 'W') {
                nextMove = whitePlayer.pickMove(gameState.getBoard());
            } else if (gameState.getCurrentPlayer() == 'B') {
                nextMove = blackPlayer.pickMove(gameState.getBoard());
            }
            gameState.moveByMove(nextMove);

            System.out.println(nextMove);
            System.out.println("------------------\n");
            System.out.println(gameState.getCurrentStateToString() + "\n");
        }
    }
}