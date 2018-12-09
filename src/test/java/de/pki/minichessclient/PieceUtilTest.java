package de.pki.minichessclient;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PieceUtilTest {
    @Test
    public void shouldReturnWhiteForUpperCase() {
        char expectedColor = 'W';
        char resultColor = PieceUtil.getColorForPiece('B');
        assertThat(resultColor, is(expectedColor));
    }

    @Test
    public void shouldReturnBlackForLowerCase() {
        char expectedColor = 'B';
        char resultColor = PieceUtil.getColorForPiece('k');
        assertThat(resultColor, is(expectedColor));
    }

    @Test
    public void shouldReturnEmptyForDot() {
        char expectedColor = 'e';
        char resultColor = PieceUtil.getColorForPiece('.');
        assertThat(resultColor, is(expectedColor));
    }
}
