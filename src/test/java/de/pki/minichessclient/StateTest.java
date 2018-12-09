package de.pki.minichessclient;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void shouldGetInitialBoard() {
        char[] expectedFirstLine = {'k', 'q', 'b', 'n', 'r'};
        char[] expectedSecondLine = {'p', 'p', 'p', 'p', 'p'};
        char[] expectedThirdAndForthLine = {'.', '.', '.', '.', '.'};
        char[] expectedFithLine = {'P', 'P', 'P', 'P', 'P'};
        char[] expectedSixthLine = {'R', 'N', 'B', 'Q', 'K'};

        char[][] expectedState = {
                expectedFirstLine,
                expectedSecondLine,
                expectedThirdAndForthLine,
                expectedThirdAndForthLine,
                expectedFithLine,
                expectedSixthLine
        };

        char[][] initialState = state.getBoard();

        assertThat(initialState, is(expectedState));
    }

    @Test
    public void shouldReturnInitialMoveNumber() {
        int initialMoveNumber = 1;
        int currentMoveNumber = state.getMoveNumber();
        assertThat(currentMoveNumber, is(initialMoveNumber));
    }

    @Test
    public void shouldReturnInitialPlayer() {
        Color initialPlayer = Color.WHITE;
        Color currentPlayer = state.getCurrentPlayer();
        assertThat(currentPlayer, is(initialPlayer));
    }

    @Test
    public void shouldPrintCurrenState() {
        String expectedState = "1 W\nkqbnr\nppppp\n.....\n.....\nPPPPP\nRNBQK";
        String currentState = state.getCurrentStateToString();
        assertThat(currentState, is(expectedState));
    }

    @Test
    public void shouldSetTheCurrentState() {
        String expectedState = "2 W\nkqbnr\n.pppp\np....\n.....\nPPPPP\nRNBQK";
        state.setCurrentState(expectedState);
        String currentState = state.getCurrentStateToString();
        assertThat(currentState, is(expectedState));
    }

    @Test
    public void shouldMoveIfMoveIsValid() {
        String expectedState = "1 B\nkqbnr\nppppp\n.....\nP....\n.PPPP\nRNBQK";
        String move = "a2-a3";
        state.moveByString(move);
        String currentState = state.getCurrentStateToString();
        assertThat(currentState, is(expectedState));
    }

    @Test
    public void shouldNotMoveIfMoveIsOutOfRange() {
        String expectedState = "1 W\nkqbnr\nppppp\n.....\n.....\nPPPPP\nRNBQK";
        String move = "x2-y3";
        state.moveByString(move);
        String currentState = state.getCurrentStateToString();
        assertThat(currentState, is(expectedState));
    }

    @Test
    public void shoudNotMoveIfStartPieceIsFromOponent() {
        String expectedState = "1 W\nkqbnr\nppppp\n.....\n.....\nPPPPP\nRNBQK";
        state.moveByString("a5-a4");
        String currentState = state.getCurrentStateToString();
        assertThat(currentState, is(expectedState));
    }

    @Test
    public void shoudNotMoveIfStartPieceIsEmpty() {
        String expectedState = "1 W\nkqbnr\nppppp\n.....\n.....\nPPPPP\nRNBQK";
        state.moveByString("a4-a5");
        String currentState = state.getCurrentStateToString();
        assertThat(currentState, is(expectedState));
    }

    @Test
    public void shouldIncrementMoveNumberAfterOneRound() {
        state.moveByString("a2-a3");
        state.moveByString("a5-a4");
        int currentMoveNumber = state.getMoveNumber();
        assertThat(currentMoveNumber, is(2));
    }

    @Test
    public void shoudPromoteWhitePawn() {
        String expectedState = "1 B\nkQbnr\np.ppp\n.....\n.....\nP.PPP\nRNBQK";
        state.setCurrentState("1 W\nk.bnr\npPppp\n.....\n.....\nP.PPP\nRNBQK");
        state.moveByString("b5-b6");
        String currentState = state.getCurrentStateToString();
        assertThat(currentState, is(expectedState));
    }

    @Test
    public void shoudPromoteBlackPawn() {
        String expectedState = "2 W\nkqbnr\np.ppp\n.....\n.....\nP.PPP\nRqBQK";
        state.setCurrentState("1 B\nkqbnr\np.ppp\n.....\n.....\nPpPPP\nR.BQK");
        state.moveByString("b2-b1");
        String currentState = state.getCurrentStateToString();
        assertThat(currentState, is(expectedState));
    }
}

