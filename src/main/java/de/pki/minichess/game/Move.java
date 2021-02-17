package de.pki.minichess.game;

import java.util.Objects;

/**
 * Move Class which holds the starting and destination position
 */
public class Move {

    private Square from;
    private Square to;

    /**
     * Generates new Move with given starting and destination position
     *
     * @param from starting position
     * @param to   destination position
     */
    Move(Square from, Square to) {
        this.from = from;
        this.to = to;
    }

    Move(String move) {
        this.from = new Square(move.substring(0,2));
        this.to = new Square(move.substring(move.length()-2));
    }

    /**
     * Getter starting position
     *
     * @return
     */
    public Square getFrom() {
        return from;
    }

    /**
     * Getter destination position
     *
     * @return
     */
    public Square getTo() {
        return to;
    }

    /**
     * Returns starting and destination positions as string
     *
     * @return
     */
    public String toString() {
        return from + "-" + to;
    }

    /**
     * Equals method to compare Moves
     *
     * @param o object to compare
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(from, move.from) &&
                Objects.equals(to, move.to);
    }

    /**
     * Generates hashcode
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
