package de.pki.minichess.game;

import de.pki.minichess.game.utils.PieceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Holds the current state of a minichessgame
 */
public class State {

	private static final Log LOG = LogFactory.getLog(State.class);

	private char[][] board;
	private int moveNumber;
	private Color currentPlayer;

	/**
	 * Generate new State with initial settings
	 */
	public State() {
		board = new char[][]{
			{'k', 'q', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p'},
			{'.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.'},
			{'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K'}
		};
		moveNumber = 1;
		currentPlayer = Color.WHITE;
	}

	/**
	 * Getter for board
	 *
	 * @return
	 */
	public char[][] getBoard() {
		return board;
	}

	/**
	 * Setter for board
	 *
	 * @param board
	 */
	public void setBoard(char[][] board) {
		this.board = board;
	}

	/**
	 * Getter for MoveNumber
	 *
	 * @return
	 */
	public int getMoveNumber() {
		return moveNumber;
	}

	/**
	 * Setter for MoveNumber
	 *
	 * @param moveNumber
	 */
	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}

	/**
	 * Getter for CurrentPlayer
	 *
	 * @return
	 */
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Getter for CurrentPlayer
	 *
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(char currentPlayer) {
		if (currentPlayer == 'B') {
			this.currentPlayer = Color.BLACK;
		}
		if (currentPlayer == 'W') {
			this.currentPlayer = Color.WHITE;
		}
	}

	/**
	 * Returns current state (board, player, moveNumber) as String
	 *
	 * @return
	 */
	public String getCurrentStateToString() {
		StringBuilder currentState = new StringBuilder(
			moveNumber + " " + currentPlayer.getColorCode());
		for (int row = 0; row < 6; row++) {
			currentState.append("\n");
			for (int column = 0; column < 5; column++) {
				currentState.append(board[row][column]);
			}
		}
		return currentState.toString();
	}

	/**
	 * Set current state (board, player, moveNumber) by parsing a valid String
	 *
	 * @param stateString valid String of game state
	 */
	public void setCurrentState(String stateString) {
		String[] lines = stateString.split("\n");

		char[][] newBoard = new char[6][5];

		String[] firstLineArgs = lines[0].split(" ");
		setMoveNumber(Integer.parseInt(firstLineArgs[0]));
		setCurrentPlayer(firstLineArgs[1].charAt(0));

		for (int lineIndex = 1; lineIndex < 7; lineIndex++) {
			newBoard[lineIndex - 1] = lines[lineIndex].toCharArray();
		}
		setBoard(newBoard);
	}

	/**
	 * Fulfills a move for a valid String
	 *
	 * @param move given move as String in format a1-b2
	 */
	public void moveByString(String move) {
		if (isValidMoveString(move)) {
			String[] squares = move.split("-");
			Square squareFrom = new Square(squares[0]);
			Square squareTo = new Square(squares[1]);
			moveByMove(new Move(squareFrom, squareTo));
		}
	}

	/**
	 * Fulfills a move for given move
	 *
	 * @param move given move
	 * @return true if game over, false else
	 */
	public boolean moveByMove(Move move) {
		if (move == null) {
			return true;
		}
		char pieceToMove = board[move.getFrom().getY()][move.getFrom().getX()];
		if (isValidStartPiece(pieceToMove) && MoveService.isMoveValid(move, board)) {
			if (checkForGameOver(move)) {
				return true;
			}
			if (canPieceBePromoted(pieceToMove, move.getTo().getY())) {
				pieceToMove = promotePawn(pieceToMove);
			}
			board[move.getTo().getY()][move.getTo().getX()] = pieceToMove;
			board[move.getFrom().getY()][move.getFrom().getX()] = '.';
			switchCurrentPlayer();
		}
		return false;
	}

	/**
	 * Promotes a pawn to queen
	 *
	 * @param pieceToMove piece to promote
	 * @return
	 */
	private char promotePawn(char pieceToMove) {
		return (char) (((int) pieceToMove) + 1);
	}

	/**
	 * Checks if pawn could be promoted to queen
	 *
	 * @param pieceToMove  piece to check for promotion
	 * @param yDestination y destination position
	 * @return
	 */
	private boolean canPieceBePromoted(char pieceToMove, int yDestination) {
		if (Character.toLowerCase(pieceToMove) != 'p') { //only check for pawns
			return false;
		}
		if (currentPlayer == Color.BLACK && yDestination == 5) { // black pawn reaches end of board
			return true;
		}
		return currentPlayer == Color.WHITE && yDestination == 0;
	}

	/**
	 * Switches current Player and increments moveNumber if necessary
	 */
	private void switchCurrentPlayer() {
		if (currentPlayer == Color.WHITE) {
			currentPlayer = Color.BLACK;
		} else {
			setMoveNumber(getMoveNumber() + 1);
			currentPlayer = Color.WHITE;
		}
	}

	/**
	 * Checks if start piece of move is valid
	 *
	 * @param pieceToMove start piece of a move
	 * @return
	 */
	private boolean isValidStartPiece(char pieceToMove) {
		if (currentPlayer != PieceUtil.getColorForPiece(pieceToMove)) {
			return false;
		}
		return pieceToMove != '.';
	}

	/**
	 * Validates move string
	 *
	 * @param move string to validate
	 * @return
	 */
	private boolean isValidMoveString(String move) {
		return move.matches("([a-e][1-6]-[a-e][1-6])");
	}

	/**
	 * checks if given move finishes the game
	 * <p>
	 * TODO: Should return the state (matt, unentschieden, ongoing) instead of a boolean.
	 *
	 * @param move
	 * @return true if game over, false else
	 */
	private boolean checkForGameOver(Move move) {
		char targetPiece = Character.toLowerCase(board[move.getTo().getY()][move.getTo().getX()]);
		if (targetPiece == 'k') {
			LOG.info("Schach-Matt");
			return true;
		}
		if ((moveNumber == 40) && (currentPlayer == Color.BLACK)) {
			LOG.info("Unentschieden");
			return true;
		}
		return false;
	}

	public int[] eval() {
		int[] score = {0, 0};
		for (char[] zeile : board) {
			for (char zelle : zeile) {
				if (PieceUtil.getColorForPiece(zelle).equals(Color.WHITE)) {
					score[0] += PieceUtil.getValueOnPosition(zelle);
				} else {
					score[1] += PieceUtil.getValueOnPosition(zelle);
				}
			}
		}
		return score;
	}
}

