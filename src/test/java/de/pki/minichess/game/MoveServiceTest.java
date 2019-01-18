package de.pki.minichess.game;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MoveServiceTest {

  private char[][] oneFigureBoard;
  private int xPositionOfFigure;
  private int yPositionOfFigure;

  @Before
  public void setUp() {
    this.oneFigureBoard = new char[][] { //
        { '.', '.', '.', '.', '.' }, //
        { '.', '.', '.', '.', '.' }, //
        { '.', '.', 'X', '.', '.' }, //
        { '.', '.', '.', '.', '.' }, //
        { '.', '.', '.', '.', '.' }, //
        { '.', '.', '.', '.', '.' } };
    this.xPositionOfFigure = 2;
    this.yPositionOfFigure = 2;
  }

  @Test
  public void shouldReturnPossibleWhitePawnMovesOnly() {
    setFigure('P');
    Set<Move> expectedMoves = new HashSet<Move>();
    Square start = new Square(2, 2);
    Square end = new Square(2, 1);
    expectedMoves.add(new Move(start, end));

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  @Test
  public void shouldReturnPossibleBlackPawnMovesOnly() {
    setFigure('p');
    Set<Move> expectedMoves = new HashSet<Move>();
    Square start = new Square(2, 2);
    Square end = new Square(2, 3);
    expectedMoves.add(new Move(start, end));

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  @Test
  public void shouldReturnPossibleWhiteKingMovesOnly() {
    setFigure('K');
    Set<Move> expectedMoves = getExpectedKingMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  @Test
  public void shouldReturnPossibleBlackKingMovesOnly() {
    setFigure('k');
    Set<Move> expectedMoves = getExpectedKingMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  
  
  @Test
  public void shouldReturnPossibleWhiteQueenMovesOnly() {
    setFigure('Q');
    Set<Move> expectedMoves = getExpectedQueenMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  @Test
  public void shouldReturnPossibleBlackQueenMovesOnly() {
    setFigure('q');
    Set<Move> expectedMoves = getExpectedQueenMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);

    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  @Test
  public void shouldReturnPossibleWhiteRookMovesOnly() {
    setFigure('R');
    Set<Move> expectedMoves = getExpectedRookMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  @Test
  public void shouldReturnPossibleBlackRookMovesOnly() {
    setFigure('r');
    Set<Move> expectedMoves = getExpectedRookMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);

    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  @Test
  public void shouldReturnPossibleWhiteBishopMovesOnly() {
    setFigure('B');
    Set<Move> expectedMoves = getExpectedBishopMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  @Test
  public void shouldReturnPossibleBlackBishopMovesOnly() {
    setFigure('b');
    Set<Move> expectedMoves = getExpectedBishopMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);

    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  @Test
  public void shouldReturnPossibleWhiteKnightMovesOnly() {
    setFigure('N');
    Set<Move> expectedMoves = getExpectedKnightMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }

  @Test
  public void shouldReturnPossibleBlackKnightMovesOnly() {
    setFigure('n');
    Set<Move> expectedMoves = getExpectedKnightMoves();

    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    

    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  private Set<Move> getExpectedKingMoves() {
    Set<Move> expectedMoves = new HashSet<Move>();
    // south
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 3)));
    // north
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 1)));
    // east
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 2)));
    // west
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 2)));
    // south east
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 1)));
    // south west
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 3)));
    // north east
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 3)));
    // north west
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 1)));
    return expectedMoves;
  }

  private Set<Move> getExpectedQueenMoves() {
    Set<Move> expectedMoves = new HashSet<Move>();
    // south
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 3)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 4)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 5)));
    // north
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 1)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 0)));
    // east
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 2)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(4, 2)));
    // west
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 2)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(0, 2)));
    // south east
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 1)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(4, 0)));
    // south west
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 3)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(4, 4)));
    // north east
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 3)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(0, 4)));
    // north west
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 1)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(0, 0)));
    return expectedMoves;
  }
  
  private Set<Move> getExpectedRookMoves() {
    Set<Move> expectedMoves = new HashSet<Move>();
    // south
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 3)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 4)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 5)));
    // north
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 1)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 0)));
    // east
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 2)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(4, 2)));
    // west
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 2)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(0, 2)));
   
    return expectedMoves;
  }
  
  private Set<Move> getExpectedBishopMoves() {
    Set<Move> expectedMoves = new HashSet<Move>();
    // south
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 3)));

    // north
    expectedMoves.add(new Move(new Square(2, 2), new Square(2, 1)));

    // east
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 2)));

    // west
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 2)));

    // south east
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 1)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(4, 0)));
    // south west
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 3)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(4, 4)));
    // north east
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 3)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(0, 4)));
    // north west
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 1)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(0, 0)));
    return expectedMoves;
  }
  
  private Set<Move> getExpectedKnightMoves() {
    Set<Move> expectedMoves = new HashSet<Move>();
  
    expectedMoves.add(new Move(new Square(2, 2), new Square(0, 1)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(0, 3)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 0)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(1, 4)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(4, 1)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(4, 3)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 0)));
    expectedMoves.add(new Move(new Square(2, 2), new Square(3, 4)));

    return expectedMoves;
  }

  private void setFigure(char figure) {
    for (char[] line : this.oneFigureBoard) {
      for (int i = 0; i < line.length; i++) {
        if (line[i] == 'X')
          line[i] = figure;
      }
    }
  }

  private String boardToString() {
    StringBuilder currentState = new StringBuilder();
    for (int row = 0; row < 6; row++) {
      currentState.append("\n");
      for (int column = 0; column < 5; column++)
        currentState.append(this.oneFigureBoard[row][column]);
    }
    return currentState.toString();
  }

  private String setToString(Set<Move> set) {
    StringBuilder builder = new StringBuilder();
    for (Move move : set) {
      builder.append(move.toString() + "\n");
    }
    return builder.toString();
  }

  private String vectorToString(Vector<Move> vector) {
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
