package de.pki.minichessclient;

import java.util.Vector;

/**
 * Service to get possible moves or validate moves.
 */
public class MoveService {

    /**
     * Checks if a given move is valid on a given board.
     *
     * @param move  move to validate
     * @param board state of board to validate
     * @return true if move is valid else false
     */
    public static boolean isMoveValid(Move move, char[][] board) {
        Vector<Move> moves = getPossibleMoves(move.getFrom().getX(), move.getFrom().getY(), board);
        for (Move moveItem : moves) {
            if (moveItem.equals(move))
                return true;
        }
        return false;
    }

    /**
     * Gets piece of given position and scans for possible moves.
     *
     * @param xStart       x start position
     * @param yStart       y start position
     * @param currentBoard state of the current board
     * @return vector of possible moves
     */
    public static Vector<Move> getPossibleMoves(int xStart, int yStart, char[][] currentBoard) {
        Vector<Move> moves = new Vector<>();

        char currentPiece = currentBoard[yStart][xStart];
        char color = PieceUtil.getColorForPiece(currentPiece);
        boolean stopShort = false;
        String capture = "true";

        switch (Character.toLowerCase(currentPiece)) {
            case 'k':
                stopShort = true;
                moves.addAll(symmscan(xStart, yStart, 0, 1, capture, stopShort, currentBoard));
                moves.addAll(symmscan(xStart, yStart, 1, 1, capture, stopShort, currentBoard));
                break;
            case 'q':
                moves.addAll(symmscan(xStart, yStart, 0, 1, capture, stopShort, currentBoard));
                moves.addAll(symmscan(xStart, yStart, 1, 1, capture, stopShort, currentBoard));
                break;
            case 'b':
                moves.addAll(symmscan(xStart, yStart, 1, 1, capture, stopShort, currentBoard));
                stopShort = true;
                capture = "false";
                moves.addAll(symmscan(xStart, yStart, 0, 1, capture, stopShort, currentBoard));
                break;
            case 'r':
                moves.addAll(symmscan(xStart, yStart, 0, 1, capture, stopShort, currentBoard));
                break;
            case 'n':
                stopShort = true;
                moves.addAll(symmscan(xStart, yStart, 1, 2, capture, stopShort, currentBoard));
                moves.addAll(symmscan(xStart, yStart, -1, 2, capture, stopShort, currentBoard));
                break;
            case 'p':
                int direction = 1;
                if (color == 'W') {
                    direction = -1;
                }
                stopShort = true;
                moves.addAll(moveScan(xStart, yStart, -1, direction, "only", stopShort, currentBoard));
                moves.addAll(moveScan(xStart, yStart, 1, direction, "only", stopShort, currentBoard));
                moves.addAll(moveScan(xStart, yStart, 0, direction, "false", stopShort, currentBoard));
                break;
        }
        return moves;
    }


    /**
     * Scans for possible moves in all four rotational symetries.
     * Example:
     * xDirection = 1; yDirection = 1;
     * 1. ( 1,  1)
     * 2. ( 1, -1)
     * 3. (-1, -1)
     * 4. ( 1. -1)
     *
     * @param xStart       x start position
     * @param yStart       y start position
     * @param xDirection   x direction of move
     * @param yDirection   y direction of move
     * @param capture      caputre flag ("true", "false" and "only")
     * @param stopShort    if true stops after one iteration
     * @param currentBoard state of the current board
     * @return vector of possible moves
     */
    private static Vector<Move> symmscan(int xStart, int yStart, int xDirection, int yDirection, String capture, boolean stopShort, char[][] currentBoard) {
        Vector<Move> moves = new Vector<>();
        for (int i = 0; i < 4; i++) {
            moves.addAll(moveScan(xStart, yStart, xDirection, yDirection, capture, stopShort, currentBoard));

            //exchange directions
            int tmpDirection = xDirection;
            xDirection = yDirection;
            yDirection = tmpDirection * -1;
        }
        return moves;
    }

    /**
     * Scans for possible moves with given params.
     *
     * @param xStart       x start position
     * @param yStart       y start position
     * @param xDirection   x direction of move
     * @param yDirection   y direction of move
     * @param capture      caputre flag ("true", "false" and "only")
     * @param stopShort    if true stops after one iteration
     * @param currentBoard state of the current board
     * @return vector of possible moves
     */
    private static Vector<Move> moveScan(int xStart, int yStart, int xDirection, int yDirection, String capture, boolean stopShort, char[][] currentBoard) {
        int xDest = xStart;
        int yDest = yStart;
        char color = PieceUtil.getColorFromPosition(xStart, yStart, currentBoard);
        Vector<Move> moves = new Vector<>();
        do {
            xDest += xDirection;
            yDest += yDirection;
            if (xDest >= 5 || xDest < 0 || yDest >= 6 || yDest < 0) {
                break;
            }
            char destinationColor = PieceUtil.getColorFromPosition(xDest, yDest, currentBoard);
            if (destinationColor != 'e') {// if target is not empty
                if (destinationColor == color)
                    break;
                if (capture.equals("false"))
                    break;
                stopShort = true;

            } else if (capture.equals("only"))
                break;
            moves.add(new Move(new Square(xStart, yStart), new Square(xDest, yDest)));
        } while (!stopShort);
        return moves;
    }
}
