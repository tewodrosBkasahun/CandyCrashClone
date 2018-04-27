package model;

import java.util.Random;

/**
 * @author Tewodros Kasahun
 */
public class ClearCellGame extends Game {
	private Random random;
	private int strategy;
	private int score = 0;
	private boolean gameOver;

	/**
	 * Standard constructor.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	}

	/**
	 * Return game status
	 */
	@Override
	public boolean isGameOver() {
		return this.gameOver;
	}

	/**
	 * Return the current score
	 */
	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public void nextAnimationStep() {
		if (!emptyRow(board.length - 1))
			this.gameOver = true;
		else {
			BoardCell[][] copy = copyBoard();
			for (int r = copy.length - 1; r > 0; r--)
				board[r] = copy[r - 1];
			for (int c = 0; c < getMaxCols(); c++)
				setBoardCell(0, c, BoardCell.getNonEmptyRandomBoardCell(this.random));
		}
	}

	/**
	 * Process all cells surrounding the given coordinate in all 8 directions. Call
	 * collapseCells method at the end.
	 */
	@Override
	public void processCell(int rowIndex, int colIndex) {
		if (!isGameOver()) {
			BoardCell color = getBoardCell(rowIndex, colIndex);
			boolean isCleared = false;
			setBoardCell(rowIndex, colIndex, null);
			while (!isCleared) {
				isCleared = true;
				for (int r = 0; r < getMaxRows(); r++)
					for (int c = 0; c < getMaxCols(); c++)
						if (getBoardCell(r, c) == color) {
							if (r != (getMaxRows() - 1) && getBoardCell(r + 1, c) == null) {
								setBoardCell(r, c, null);
								isCleared = false;
							}
							/* Check the cell above. */
							if (r != 0 && getBoardCell(r - 1, c) == null) {
								setBoardCell(r, c, null);
								isCleared = false;
							}
							/* Check the cell to the right. */
							if (c != (getMaxCols() - 1) && getBoardCell(r, c + 1) == null) {
								setBoardCell(r, c, null);
								isCleared = false;
							}
							/* Check the cell to the left. */
							if (c != 0 && getBoardCell(r, c - 1) == null) {
								setBoardCell(r, c, null);
								isCleared = false;
							}
							/* Check the cell to the bottom right. */
							if (r != (getMaxRows() - 1) && c != (getMaxCols() - 1)
									&& getBoardCell(r + 1, c + 1) == null) {
								setBoardCell(r, c, null);
								isCleared = false;
							}
							/* Check the cell to the bottom left. */
							if (r != (getMaxRows() - 1) && c != 0 && getBoardCell(r + 1, c - 1) == null) {
								setBoardCell(r, c, null);
								isCleared = false;
							}
							/* Check the cell to the top right. */
							if (r != 0 && c != (getMaxCols() - 1) && getBoardCell(r - 1, c + 1) == null) {
								setBoardCell(r, c, null);
								isCleared = false;
							}
							/* Check the cell to the top left. */
							if (r != 0 && c != 0 && getBoardCell(r - 1, c - 1) == null) {
								setBoardCell(r, c, null);
								isCleared = false;
							}
						}
			}
			for (int r = 0; r < getMaxRows(); r++)
				for (int c = 0; c < getMaxCols(); c++)
					if (getBoardCell(r, c) == null) {
						this.score++;
						setBoardCell(r, c, BoardCell.EMPTY);
					}
			collapseCells();
		}
	}

	private void collapseCells() {
		BoardCell[][] newBoard = new BoardCell[getMaxRows()][];
		int index = 0;
		//Checking if each row is empty. If not, add that row to newBoard. 
		for (BoardCell[] r : board) {
			boolean emptyRow = true; 
			for (BoardCell c : r)
				if (c != BoardCell.EMPTY)
					emptyRow = false;
			if (!emptyRow)
				newBoard[index++] = r;
		}
		/* Set any remaining rows to empty. */
		for (int r = 0; r < newBoard.length; r++)
			if (newBoard[r] == null) {
				newBoard[r] = new BoardCell[getMaxCols()];
				for (int c = 0; c < getMaxCols(); c++) // Initiate each cell.
					newBoard[r][c] = BoardCell.EMPTY;
			}
		board = newBoard;
	}
}
