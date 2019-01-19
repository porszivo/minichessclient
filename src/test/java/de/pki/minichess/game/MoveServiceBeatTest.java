package de.pki.minichess.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

public class MoveServiceBeatTest extends AbstractMoveServiceTest {

  private char[][] board;
  private int xPositionOfFigure;
  private int yPositionOfFigure;

  @Before
  public void setUp() {
    this.board = new char[][] { //
        { '.', '.', '.', '.', '.' }, //
        { '.', '.', 'G', 'H', '.' }, //
        { 'F', '.', 'X', 'E', '.' }, //
        { 'D', 'A', 'B', '.', '.' }, //
        { '.', 'C', '.', '.', '.' }, //
        { '.', '.', '.', '.', '.' } };
    this.xPositionOfFigure = 2;
    this.yPositionOfFigure = 2;
  }

  @Test
  public void shouldReturnPossibleWhitePawnMovesOnlyWhileBeating() {
    setFigure('X', 'P');
    setOtherFigures("qkpppr.r".toCharArray());
    Set<Move> expectedMoves = new HashSet<Move>();
    Square start = new Square(2, 2);
    Square end = new Square(2, 1);
    expectedMoves.add(new Move(start, end));
    start = new Square(2, 2);
    end = new Square(3, 1);
    expectedMoves.add(new Move(start, end));

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, board);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);

    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  @Test
  public void shouldReturnPossibleWhitePawnMovesOnlyWhileBlocked() {
    setFigure('X', 'P');
    setOtherFigures("qkppprQK".toCharArray());
    Set<Move> expectedMoves = new HashSet<Move>();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, board);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);

    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  private void setOtherFigures(char[] others) {
    // Starting with A (ascii 65)
    int ascii = 65;
    for (char c : others) {
      setFigure((char) ascii, c);
      ascii++;
    }
  }

  private void setFigure(char charToReplace, char figure) {
    for (char[] line : this.board) {
      for (int i = 0; i < line.length; i++) {
        if (line[i] == charToReplace)
          line[i] = figure;
      }
    }
  }

}
