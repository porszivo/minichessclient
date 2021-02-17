package de.pki.minichess;

import de.pki.minichess.client.ui.Console;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Application class to run a game of minichess
 */
public class MinichessApplication {

	private static final Log LOG = LogFactory.getLog(MinichessApplication.class);

	/**
	 * Initializes two players with given ai and plays a game of minichess dirty loop stops if a
	 * move finishes the game
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Console console = new Console();
			boolean goOn = true;
			while (goOn) {
				goOn = console.requestCommand();
			}
		} catch (IOException | RuntimeException e) {
			LOG.error("SOMETHING WENT TERRIBLY WRONG!!! :-(", e);
		}

	}
}
