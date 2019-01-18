package de.pki.minichess;

import de.pki.minichess.game.Square;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SquareTest {

    @Test
    public void shouldCreateCorrectSquareByString() {
        int expectedColumn = 0;
        int expectedRow = 4;

        Square square = new Square("a2");

        assertThat(square.getY(), is(expectedRow));
        assertThat(square.getX(), is(expectedColumn));
    }
}
