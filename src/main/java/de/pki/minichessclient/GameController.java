package de.pki.minichessclient;

public class GameController {
  
  public GameController() {
    
  }
  
  public String runGame() {
    GameState gameState = new GameState();
    PlayerRandom whitePlayer = new PlayerRandom(Color.WHITE);
    PlayerRandom blackPlayer = new PlayerRandom(Color.BLACK);
    
    StringBuilder builder = new StringBuilder();
    boolean gameOver = false;
    
    while (!gameOver) {
      Move nextMove = null;

      if (gameState.getCurrentPlayer() == Color.WHITE) {
        nextMove = whitePlayer.pickMove(gameState.getBoard());
      } else if (gameState.getCurrentPlayer() == Color.BLACK) {
        nextMove = blackPlayer.pickMove(gameState.getBoard());
      }
      builder.append(nextMove+"\n");
      gameOver = gameState.moveByMove(nextMove);

      builder.append("------------------\n");
      builder.append(gameState.getCurrentStateToString() + "\n");
    }
    return builder.toString();
  }

}
