package cs3500.marblesolitaire.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for the marble solitaire model.
 */
public class MarbleSolitaireModelImplTest {
  MarbleSolitaireModelImpl defaultGame = new MarbleSolitaireModelImpl();
  MarbleSolitaireModelImpl gameWith5 = new MarbleSolitaireModelImpl(5);

  // test whether the game is over after initially creating the game
  @Test
  public void testGameOver() {
    /*
    assertEquals(false,
            defaultGame.isGameOver());
    assertEquals(false,
            gameWith5.isGameOver());
    */
    MarbleSolitaireModelImpl x = new MarbleSolitaireModelImpl(0, 2);
    assertEquals(false,
            x.isGameOver());


  }

  // test whether the game state is the same after creating the game
  @Test
  public void testGameState() {
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    ""
                    + "    O O O\n" +
                    "    O O O",
            defaultGame.getGameState());
    assertEquals("        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    ""
                    + "        O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    ""
                    + "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    ""
                    + "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    ""
                    + "        O O O O O\n" +
                    "        O O O O O",
            gameWith5.getGameState());
  }

  // test that the initial score is correct after generating the game
  @Test
  public void testGetScore() {
    assertEquals(32,
            defaultGame.getScore());
    assertEquals(104,
            gameWith5.getScore());

  }

  // test the game state is accurate after making moves
  @Test
  public void testMove() {
    MarbleSolitaireModelImpl testGame = new MarbleSolitaireModelImpl();
    testGame.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    ""
                    + "    O O O\n" +
                    "    O O O",
            testGame.getGameState());
  }

  // test the game state is accurate after making moves
  @Test
  public void testMove1() {
    MarbleSolitaireModelImpl testGame = new MarbleSolitaireModelImpl();
    testGame.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    ""
                    + "    O O O\n" +
                    "    O O O",
            testGame.getGameState());
  }

  // test the game state is accurate after making moves
  @Test
  public void testMove2() {
    MarbleSolitaireModelImpl testGame = new MarbleSolitaireModelImpl(0, 2);
    testGame.move(0, 4, 0, 2);
    assertEquals("    O _ _\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O",
            testGame.getGameState());
  }

  // test the game state is accurate after making moves
  @Test
  public void testMove3() {
    MarbleSolitaireModelImpl testGame = new MarbleSolitaireModelImpl();
    testGame.move(5, 3, 3, 3);
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n"
                    + "    O _ O\n" +
                    "    O O O",
            testGame.getGameState());
  }

  // test the game state is accurate after making moves
  // check if the game is over and the score after each move
  // check if the game state is as epected after making 6 valid moves
  @Test
  public void testMove4() {
    MarbleSolitaireModelImpl testGame = new MarbleSolitaireModelImpl();
    testGame.move(1, 3, 3, 3);
    assertEquals(31,
            testGame.getScore());
    assertEquals(false,
            testGame.isGameOver());
    testGame.move(4, 3, 2, 3);
    assertEquals(30,
            testGame.getScore());
    assertEquals(false,
            testGame.isGameOver());
    testGame.move(6, 3, 4, 3);
    assertEquals(29,
            testGame.getScore());
    assertEquals(false,
            testGame.isGameOver());
    testGame.move(3, 5, 3, 3);
    assertEquals(28,
            testGame.getScore());
    assertEquals(false,
            testGame.isGameOver());
    testGame.move(3, 2, 3, 4);
    assertEquals(27,
            testGame.getScore());
    assertEquals(false,
            testGame.isGameOver());
    testGame.move(3, 0, 3, 2);
    assertEquals(26,
            testGame.getScore());
    assertEquals(true,
            testGame.isGameOver());

    assertEquals("    O O O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "_ _ O _ O _ O\n" +
                    "O O O O O O O\n"
                    + "    O _ O\n" +
                    "    O _ O",
            testGame.getGameState());
  }

  // attempt many types of illegal moves and check to see if the proper error is thrown
  @Test
  public void invalidMoves() {
    MarbleSolitaireModelImpl testGame = new MarbleSolitaireModelImpl();
    try {
      testGame.move(-1, 3, 3, 3);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }

    try {
      testGame.move(3, 3, 3, 4);
      fail("From position does not have marble");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }

    try {
      testGame.move(4, 3, 2, 3);
      fail("To position already has marble");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }

    try {
      testGame.move(3, 0, 3, 3);
      fail("Trying to move horizontal, but too far apart");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }

    try {
      testGame.move(0, 3, 3, 3);
      fail("Trying to move vertical, but too far apart");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }

    try {
      testGame.move(2, 4, 3, 3);
      fail("Cannot move diagonal");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }

    testGame.move(5, 3, 3, 3);
    try {
      testGame.move(6, 3, 4, 3);
      fail("No marble between to and from");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
  }

  // attempt to create differnt illegal game board and check if the proper error is thrown
  @Test
  public void invalidConstructors() {
    try {
      MarbleSolitaireModelImpl testGame = new MarbleSolitaireModelImpl(0, 0);
      fail("Invalid empty cell position (0,0)");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid empty cell position (0,0)");
    }
  }
}
