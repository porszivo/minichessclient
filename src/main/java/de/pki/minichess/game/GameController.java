package de.pki.minichess.game;

import de.pki.minichess.ai.KIPlayer;
import de.pki.minichess.client.connector.Client;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Controls the automatic game processing.
 * <p>
 * A good point to insert the KI.
 */
public class GameController {

	private static final Log LOG = LogFactory.getLog(GameController.class);

	/**
	 * Executes an automatically played game. KI should be inserted here.
	 *
	 * @return The game steps until game finishes.
	 */
	public String runGame(Color color, Client client) {

		State gameState = new State();
		KIPlayer player = new KIPlayer(color);

		StringBuilder builder = new StringBuilder();
		boolean gameOver = false;
		Move currentMove;
		try {

			while (!gameOver) {

				if (gameState.getCurrentPlayer().equals(color)) {
					currentMove = player.pickMove(gameState.getBoard());
					client.sendMove(currentMove.toString());
					LOG.info("Unser Zug: " + currentMove.toString());
				} else {
					currentMove = new Move(client.getMove());
				}

				builder.append(currentMove).append("\n");
				gameOver = gameState.moveByMove(currentMove);
				builder.append("------------------\n");
				builder.append(gameState.getCurrentStateToString()).append("\n");
				LOG.info(gameState.getCurrentStateToString());
			}
		} catch (IOException e) {
			LOG.error("Fehler..", e);
		}

		LOG.info(gameState.getCurrentStateToString());
		return builder.toString();
	}

}
