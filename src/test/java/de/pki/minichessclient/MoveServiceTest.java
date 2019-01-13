package de.pki.minichessclient;

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
    Square start = new Square(2,2);
    Square end = new Square(2,1);
    expectedMoves.add(new Move(start, end));
    
    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  @Test
  public void shouldReturnPossibleBlackPawnMovesOnly() {
    setFigure('p');
    Set<Move> expectedMoves = new HashSet<Move>();
    Square start = new Square(2,2);
    Square end = new Square(2,3);
    expectedMoves.add(new Move(start, end));
    
    Vector<Move> possibleMoves = MoveService.getPossibleMoves(xPositionOfFigure, yPositionOfFigure, oneFigureBoard);
    Set<Move> possibleMovesSet = new HashSet<Move>(possibleMoves);
    assertThat(compareTwoSets(possibleMovesSet, expectedMoves), is(true));
  }
  
  private void setFigure(char figure) {
    for (char[] line : this.oneFigureBoard) {
      for (int i = 0; i < line.length; i++) {
        if (line[i] == 'X') line[i] = figure;
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
      builder.append(move.toString());
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
