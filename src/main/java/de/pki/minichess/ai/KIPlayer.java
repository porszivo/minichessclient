package de.pki.minichess.ai;

import de.pki.minichess.game.Color;
import de.pki.minichess.game.Move;
import de.pki.minichess.game.MoveService;
import de.pki.minichess.game.Square;
import de.pki.minichess.game.State;
import de.pki.minichess.game.utils.PieceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class KIPlayer implements IPlayer {

    private Color color;
    private Random rand = new Random();

    /**
     * Generates a player with given color
     *
     * @param color color of the player
     */
    public KIPlayer(Color color) {
        this.color = color;
    }

    @Override
    public Move pickMove(char[][] board) {
        Vector<Square> currentPlayerPieces = scanPiecesForCurrentPlayer(board);
        Vector<Move> possibleMoves = new Vector<>();
        Map<Move, int[]> evaluationScoreMap = new HashMap<>();
        for (Square piece : currentPlayerPieces) {
            possibleMoves.addAll(MoveService.getPossibleMoves(piece.getX(), piece.getY(), board));
        }

        State temp = new State();

        for (Move move : possibleMoves) {
            temp.setCurrentPlayer(color.equals(Color.WHITE) ? 'W' : 'B');
            char[][] boardCopy = deepCopy(board);
            temp.setBoard(boardCopy);
            temp.moveByMove(move);
            evaluationScoreMap.put(move, temp.eval());
        }

        Move bestMove = null;
        int enemyScore = 5001;
        List<Move> keys = new ArrayList(evaluationScoreMap.keySet());
        Collections.shuffle(keys);
        for(Move m : keys) {
            int[] pair = evaluationScoreMap.get(m);
            if (color.equals(Color.WHITE)) {
                if (enemyScore > pair[1]) {
                    enemyScore = pair[1];
                    bestMove = m;
                }
            }
            if (color.equals(Color.BLACK)) {
                if (enemyScore > pair[0]) {
                    enemyScore = pair[0];
                    bestMove = m;
                }
            }
        }
        // int nextMoveIndex = rand.nextInt(possibleMoves.size());
        return bestMove;
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

    public static char[][] deepCopy(char[][] original) {
        if (original == null) {
            return null;
        }

        final char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

}
