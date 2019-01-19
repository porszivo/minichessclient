package de.pki.minichess.game;

import java.util.Set;
import java.util.Vector;

public abstract class AbstractMoveServiceTest {
  
  protected String boardToString(char[][] board) {
    StringBuilder currentState = new StringBuilder();
    for (int row = 0; row < 6; row++) {
      currentState.append("\n");
      for (int column = 0; column < 5; column++)
        currentState.append(board[row][column]);
    }
    return currentState.toString();
  }

  protected String setToString(Set<Move> set) {
    StringBuilder builder = new StringBuilder();
    for (Move move : set) {
      builder.append(move.toString() + "\n");
    }
    return builder.toString();
  }

  protected String vectorToString(Vector<Move> vector) {
    StringBuilder builder = new StringBuilder();
    for (Move move : vector) {
      builder.append(move.toString());
    }
    return builder.toString();
  }

  protected boolean compareTwoSets(Set one, Set two) {
    return (one.containsAll(two) && two.containsAll(one));
  }

}
