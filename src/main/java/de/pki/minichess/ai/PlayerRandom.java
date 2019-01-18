package de.pki.minichess.ai;

import de.pki.minichess.game.Color;
import de.pki.minichess.game.Move;
import de.pki.minichess.game.MoveService;
import de.pki.minichess.game.Square;
import de.pki.minichess.game.utils.PieceUtil;

import java.util.Random;
import java.util.Vector;

/**
 * Implementation of Random AI Player
 */
public class PlayerRandom implements IPlayer {

    private Color color;
    private Random rand = new Random();

    /**
     * Generates a player with given color
     *
     * @param color color of the player
     */
    public PlayerRandom(Color color) {
        this.color = color;
    }

    @Override
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
