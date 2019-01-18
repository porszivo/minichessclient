package de.pki.minichess.ai;

import de.pki.minichess.game.Move;

/**
 * Interface for Playerimplementations
 */
public interface IPlayer {

    /**
     * Picks a move for the player on the given board with a specific logic
     *
     * @param board current board to pick a move from
     * @return Picked Move
     */
    Move pickMove(char[][] board);
}
