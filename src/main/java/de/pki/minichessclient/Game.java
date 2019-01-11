package de.pki.minichessclient;

import java.io.IOException;

/**
 * Application class to run a game of minichess
 */
public class Game {

  /**
   * Initializes two players with given ki and plays a game of minichess
   * dirty loop stops if a move finishes the game
   *
   * @param args
   */
  public static void main(String[] args) {
    GameState gameState = new GameState();
    PlayerRandom whitePlayer = new PlayerRandom(Color.WHITE);
    PlayerRandom blackPlayer = new PlayerRandom(Color.BLACK);

    try {
      Console console = new Console();
      boolean goOn = true;
      while (goOn) {
        try {
          goOn = console.requestCommand();
        } catch (RuntimeException e) {
          // It's strange but the Client throws RuntimeExceptions as business exceptions. So we just handle them
          // smoothly by printing out the message and keeping the application running.
          System.out.println("ERROR: " + e.getMessage());
        }

      }
    } catch (IOException e) {
      System.out.println("SOMETHING WENT TERRIBLY WRONG!!! :-(");
      e.printStackTrace();
    }

    // while (true) {
    // Move nextMove = null;
    //
    // if (gameState.getCurrentPlayer() == Color.WHITE) {
    // nextMove = whitePlayer.pickMove(gameState.getBoard());
    // } else if (gameState.getCurrentPlayer() == Color.BLACK) {
    // nextMove = blackPlayer.pickMove(gameState.getBoard());
    // }
    // System.out.println(nextMove);
    // gameState.moveByMove(nextMove);
    //
    // System.out.println("------------------\n");
    // System.out.println(gameState.getCurrentStateToString() + "\n");
    // }
  }
}