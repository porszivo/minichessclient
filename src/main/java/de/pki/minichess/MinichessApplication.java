package de.pki.minichess;

import de.pki.minichess.client.ui.Console;
import de.pki.minichess.game.GameController;

import java.io.IOException;

/**
 * Application class to run a game of minichess
 */
public class MinichessApplication {

  /**
   * Initializes two players with given ai and plays a game of minichess
   * dirty loop stops if a move finishes the game
   *
   * @param args
   */
  public static void main(String[] args) {

    //GameController controller = new GameController();
    //controller.runGame();
    try {
      Console console = new Console();
      boolean goOn = true;
      while (goOn) {
        try {
          goOn = console.requestCommand();
        } catch (RuntimeException e) {
          // It's strange but the Client throws RuntimeExceptions as business exceptions. So we just handle them
          // smoothly by printing out the message and keeping the application running.
          System.out.println("ERROR: " + e.getStackTrace());
          System.out.println("ERROR: " + e.getMessage());
        }

      }
    } catch (IOException e) {
      System.out.println("SOMETHING WENT TERRIBLY WRONG!!! :-(");
      e.printStackTrace();
    }


  }
}