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
        { '.', 'I', '.', '.', '.' }, //
        { '.', '.', 'G', 'H', 'J' }, //
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
  
  @Test
  public void shouldReturnPossibleBlackPawnMovesOnlyWhileBeating() {
    setFigure('X', 'p');
    setOtherFigures("Q.PPPR.R".toCharArray());
    Set<Move> expectedMoves = new HashSet<Move>();
    Square start = new Square(2, 2);
    Square end = new Square(2, 3);
    expectedMoves.add(new Move(start, end));
    start = new Square(2, 2);
    end = new Square(1, 3);
    expectedMoves.add(new Move(start, end));

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, board);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  @Test
  public void shouldReturnPossibleBlackPawnMovesOnlyWhileBlocked() {
    setFigure('X', 'p');
    setOtherFigures("pkppprQK".toCharArray());
    Set<Move> expectedMoves = new HashSet<Move>();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, board);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);

    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  /**
   * Checks if the white knight can jump over own and enemy pieces. Checks if it beats correctly, i.e. only enemies.
   * 
   * Field looks like:
   * 
   *    { '.', 'p', '.', '.', '.' }, //
   *    { '.', '.', 'R', 'R', 'K' }, //
   *    { 'r', '.', 'N', '.', '.' }, //
   *    { 'P', 'Q', 'k', '.', '.' }, //
   *    { '.', 'q', '.', '.', '.' }, //
   *    { '.', '.', '.', '.', '.' }
   * 
   */
  @Test
  public void shouldReturnPossibleWhiteKnightMovesOnlyWhileBeatingAndBlocked() {
    setFigure('X', 'N');
    setOtherFigures("QkqP.rRRpK".toCharArray());
    Set<Move> expectedMoves = new HashSet<Move>();
    Square start = new Square(2, 2);
    Square end = new Square(1, 4);
    expectedMoves.add(new Move(start, end));
    end = new Square(3, 4);
    expectedMoves.add(new Move(start, end));
    end = new Square(4, 3);
    expectedMoves.add(new Move(start, end));
    end = new Square(0, 1);
    expectedMoves.add(new Move(start, end));
    end = new Square(1, 0);
    expectedMoves.add(new Move(start, end));
    end = new Square(3, 0);
    expectedMoves.add(new Move(start, end));
    
    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, board);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  /**
   * Checks if the white knight can jump over own and enemy pieces. Checks if it beats correctly, i.e. only enemies.
   * 
   * Field looks like:
   * 
   *    { '.', 'P', '.', '.', '.' }, //
   *    { '.', '.', 'r', 'r', 'k' }, //
   *    { 'R', '.', 'n', '.', '.' }, //
   *    { 'p', 'q', 'K', '.', '.' }, //
   *    { '.', 'Q', '.', '.', '.' }, //
   *    { '.', '.', '.', '.', '.' }
   * 
   */
  @Test
  public void shouldReturnPossibleBlackKnightMovesOnlyWhileBeatingAndBlocked() {
    setFigure('X', 'n');
    setOtherFigures("qKQp.RrrPk".toCharArray());
    Set<Move> expectedMoves = new HashSet<Move>();
    Square start = new Square(2, 2);
    Square end = new Square(1, 4);
    expectedMoves.add(new Move(start, end));
    end = new Square(3, 4);
    expectedMoves.add(new Move(start, end));
    end = new Square(4, 3);
    expectedMoves.add(new Move(start, end));
    end = new Square(0, 1);
    expectedMoves.add(new Move(start, end));
    end = new Square(1, 0);
    expectedMoves.add(new Move(start, end));
    end = new Square(3, 0);
    expectedMoves.add(new Move(start, end));
    
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
  
  private void printExpectedAndPossibleMoves(Set<Move> expected, Set<Move> possible) {
    System.out.println("Expected: "+ setToString(expected));
    System.out.println("Possible: "+ setToString(possible));
  }

}
