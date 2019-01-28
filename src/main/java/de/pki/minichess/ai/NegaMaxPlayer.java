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
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class NegaMaxPlayer implements IPlayer {

    private Color color;
    private Random rand = new Random();

    /**
     * Generates a player with given color
     *
     * @param color color of the player
     */
    public NegaMaxPlayer(Color color) {
        this.color = color;
    }

    @Override
    public Move pickMove(char[][] board) {
        Vector<Square> currentPlayerPieces = scanPiecesForCurrentPlayer(board);
        Vector<Move> possibleMoves = new Vector<>();
        //return miniMax(Color.WHITE, 5, board);
        return null;
    }

    private int miniMax(Color spieler, int tiefe, char[][] board) {
        Vector<Square> currentPlayerPieces = scanPiecesForCurrentPlayer(board);
        Vector<Move> possibleMoves = new Vector<>();
        for (Square piece : currentPlayerPieces) {
            possibleMoves.addAll(MoveService.getPossibleMoves(piece.getX(), piece.getY(), board));
        }
        if (tiefe == 0 || possibleMoves.isEmpty()) {
            State state = new State();
            state.setBoard(board);
            return state.eval()[0] - state.eval()[1];
        }/*
        int maxWert = -unendlich;
        generiereMoeglicheZuege(spieler);
        while (noch Zug da) {
            fuehreNaechstenZugAus();
            int wert = -miniMax(-spieler, tiefe-1);
            macheZugRueckgaengig();
            if (wert > maxWert) {
                maxWert = wert;
                if (tiefe == gewuenschteTiefe)
                    gespeicherterZug = Zug;
            }
        }
        return maxWert;*/
        return 0;
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
