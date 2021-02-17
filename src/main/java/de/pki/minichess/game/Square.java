package de.pki.minichess.game;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Holds Position
 */
public class Square {

	private static final Log LOG = LogFactory.getLog(Square.class);

    private int x;
    private int y;

    /**
     * Generates a new Square with given x and y position
     *
     * @param x x position
     * @param y y position
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Generates a new Square from a valid position String
     *
     * @param square Valid String of position
     */
    public Square(String square) {
        if (isValidSquareString(square)) {
            char[] pos = square.toCharArray();
            this.x = transXString(pos[0]);
            this.y = transformYString(pos[1]);
        }
    }

    /**
     * Getter x position
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Getter y position
     *
     * @return
     */
    public int getY() {
        return y;
    }

    private int transXString(char pos) {
        int mappedXCoordinate = 0;

        switch (pos) {
            case 'a':
                break;
            case 'b':
                mappedXCoordinate = 1;
                break;
            case 'c':
                mappedXCoordinate = 2;
                break;
            case 'd':
                mappedXCoordinate = 3;
                break;
            case 'e':
                mappedXCoordinate = 4;
                break;
			default:
				LOG.error("Move is not supported");
				break;
        }

        return mappedXCoordinate;
    }

    /**
     * Equals method to compare Squares
     *
     * @param o object to compare
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return x == square.x &&
                y == square.y;
    }

    /**
     * Generates hashcode
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    private int transformYString(char pos) {
        int offsetBoardY = 6;
        return offsetBoardY - (Character.getNumericValue(pos));
    }

    @Override
    public String toString() {
        return xToChar(getX()) + "" + yToChar(getY());
    }

    private String xToChar(int x) {
        String returnValue = "";
        switch(x) {
            case 0:
                returnValue = "a";
                break;
            case 1:
                returnValue = "b";
                break;
            case 2:
                returnValue = "c";
                break;
            case 3:
                returnValue = "d";
                break;
            case 4:
                returnValue = "e";
                break;
			default:
				LOG.error("Invalid Char");
				break;
        }
        return returnValue;
    }

    private String yToChar(int y) {
        return 6-y + "";
    }

    private boolean isValidSquareString(String square) {
        return square.matches("([a-e][1-6])");
    }
}
