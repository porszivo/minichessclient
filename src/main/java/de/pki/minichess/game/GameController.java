package de.pki.minichess.game;

import de.pki.minichess.ai.KIPlayer;
import de.pki.minichess.ai.PlayerRandom;
import de.pki.minichess.client.connector.Client;

import java.io.IOException;

/**
 * Controls the automatic game processing.
 * <p>
 * A good point to insert the KI.
 */
public class GameController {

    public Color c;

    /**
     * Executes an automatically played game.
     * KI should be inserted here.
     *
     * @return The game steps until game finishes.
     */
    public String runGame(Color c, Client client) {
        this.c = c;

        State gameState = new State();
        KIPlayer player = new KIPlayer(c);

        StringBuilder builder = new StringBuilder();
        boolean gameOver = false;
        Move currentMove = null;
        try {

            while (!gameOver) {

                if (gameState.getCurrentPlayer().equals(this.c)) {
                    currentMove = player.pickMove(gameState.getBoard());
                    client.sendMove(currentMove.toString());
                    System.out.println("Unser Zug: " + currentMove.toString());
                } else {
                    currentMove = new Move(client.getMove());
                }

                builder.append(currentMove + "\n");
                gameOver = gameState.moveByMove(currentMove);
                builder.append("------------------\n");
                builder.append(gameState.getCurrentStateToString() + "\n");
                System.out.println(gameState.getCurrentStateToString());
            }
        } catch (IOException e) {
            System.out.println("Fehler..");
        }

        System.out.println(gameState.getCurrentStateToString());
        return builder.toString();
    }

}
