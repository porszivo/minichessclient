package de.pki.minichessclient;

import java.util.Random;
import java.util.Vector;

public class PlayerRandom {

    private char color;
    private Random rand = new Random();

    /**
     * Generates a player with given color
     *
     * @param color color of the player
     */
    PlayerRandom(char color) {
        this.color = color;
    }

    /**
     * Picks a move for the player on the given board
     *
     * @param board current board to pick a move from
     * @return
     */
    public Move pickMove(char[][] board) {
        Vector<Square> currentPlayerPieces = scanPiecesForCurrentPlayer(board);
        Vector<Move> possibleMoves = new Vector<>();
        for (Square piece : currentPlayerPieces) {
            possibleMoves.addAll(MoveService.getPossibleMoves(piece.getX(), piece.getY(), board));
        }
        int nextMoveIndex = rand.nextInt(possibleMoves.size());
        return possibleMoves.get(nextMoveIndex);
    }

    private Vector<Square> scanPiecesForCurrentPlayer(char[][] board) {
        Vector<Square> ownedPieces = new Vector<>();
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                if (PieceUtil.getColorForPiece(board[row][column]) == color) {
                    ownedPieces.add(new Square(column, row));
                }
            }
        }
        return ownedPieces;
    }

}
