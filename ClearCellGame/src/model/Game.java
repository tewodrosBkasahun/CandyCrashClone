package model;


import java.util.Arrays;

/** This class represents the logic of a game where a board is updated on each step of the game
 * animation. The board can also be updated by selecting a board cell.
 * 
 * @author Dept of Computer Science, UMCP */
public abstract class Game {

    protected BoardCell[][] board;

    /** Defines a board with BoardCell.EMPTY cells.
     * 
     * @param maxRows
     * @param maxCols */
    public Game(int maxRows, int maxCols) {
	this.board = new BoardCell[maxRows][maxCols];
	for (int r = 0; r < this.board.length; r++)
	    for (int c = 0; c < this.board[r].length; c++)
		this.board[r][c] = BoardCell.EMPTY;
    }

    public int getMaxRows() {

	return this.board.length;
    }

    public int getMaxCols() {

	return this.board[0].length;
    }

    /* Return a deep copy of this.board. */
    protected BoardCell[][] copyBoard() {

	final BoardCell[][] result = new BoardCell[this.board.length][];
	for (int i = 0; i < this.board.length; i++)
	    result[i] = Arrays.copyOf(this.board[i], this.board[i].length);
	return result;
    }

    public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {

	this.board[rowIndex][colIndex] = boardCell;
    }

    public BoardCell getBoardCell(int rowIndex, int colIndex) {

	return this.board[rowIndex][colIndex];
    }

    /** Initializes row with the specified color.
     * 
     * @param rowIndex
     * @param cell */
    public void setRowWithColor(int rowIndex, BoardCell cell) {

	for (int c = 0; c < this.board[rowIndex].length; c++)
	    this.board[rowIndex][c] = cell;
    }

    /** Initializes column with the specified color.
     * 
     * @param colIndex
     * @param cell */
    public void setColWithColor(int colIndex, BoardCell cell) {

	for (int r = 0; r < this.board.length; r++)
	    this.board[r][colIndex] = cell;
    }

    /** Initializes the board with the specified color.
     * 
     * @param cell */
    public void setBoardWithColor(BoardCell cell) {

	for (int r = 0; r < this.board.length; r++)
	    for (int c = 0; c < this.board[r].length; c++)
		this.board[r][c] = cell;
    }

    /** Check if a given row is empty. Used by collapseCell method in ClearCellGame class. Note to
     * self: is this method unnecessary?
     * 
     * @param row
     * @return true or false. */
    protected boolean emptyRow(int row) {

	for (BoardCell c : this.board[row])
	    if (c != BoardCell.EMPTY)
		return false;
	return true;
    }

    /* Used by StudentTests class. */
    @Override
    public String toString() {

	StringBuffer board = new StringBuffer();
	for (BoardCell[] r : this.board) {
	    for (BoardCell c : r)
		board.append(c.getName() + " ");
	    board.append("\n");
	}
	return board.toString();
    }

    public abstract boolean isGameOver();

    public abstract int getScore();

    /** Advances the animation one step. */
    public abstract void nextAnimationStep();

    /** Adjust the board state according to the current board state and the selected cell.
     * 
     * @param rowIndex
     * @param colIndex */
    public abstract void processCell(int rowIndex, int colIndex);
}
