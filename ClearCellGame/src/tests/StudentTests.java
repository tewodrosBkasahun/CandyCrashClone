package tests;

import java.util.Random;

import org.junit.Test;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

public class StudentTests {

    @Test
    public void testSetRowWithColor() {

	Game game = new ClearCellGame(4, 5, new Random(1L), 1);
	game.setRowWithColor(0, BoardCell.BLUE);
	game.setRowWithColor(0, BoardCell.RED);
	System.out.println(game.toString());
    }

    @Test
    public void testNextAnimationStep() {

	Game game = new ClearCellGame(4, 5, new Random(1L), 1);
	game.nextAnimationStep();
	game.nextAnimationStep();
	System.out.println(game.toString());
    }
}
