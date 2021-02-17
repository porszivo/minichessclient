package de.pki.minichess.game.utils;

import de.pki.minichess.game.Color;

/**
 * Helper Class to support piececolor detection.
 */
public class PieceUtil {

	private PieceUtil() {}

    /**
     * Returns color for a given position on a given board
     *
     * @param xCord        x position
     * @param yCord        y position
     * @param currentBoard current state of board
     * @return color as char
     */
    public static Color getColorFromPosition(int xCord, int yCord, char[][] currentBoard) {
        return getColorForPiece(currentBoard[yCord][xCord]);
    }

    /**
     * Returns color for a given piece
     *
     * @param piece piece as char
     * @return color as char
     */
    public static Color getColorForPiece(char piece) {
        if ((piece >= 'A') && (piece <= 'Z'))
            return Color.WHITE;
        if ((piece >= 'a') && (piece <= 'z'))
            return Color.BLACK;
        return Color.EMPTY;
    }

    public static int getValueOnPosition(char piece) {
        piece = Character.toLowerCase(piece);
        switch (piece) {
            case 'p':
                return 100;
            case 'r':
                return 500;
            case 'n':
			case 'b':
				return 300;
			case 'q':
                return 900;
            default:
                return 2500;
        }
    }


}
