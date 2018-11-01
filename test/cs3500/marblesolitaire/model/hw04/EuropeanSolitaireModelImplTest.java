package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

/**
 * This class represents tests for the marble solitaire controller for the european version.
 */
public class EuropeanSolitaireModelImplTest {
  // test whether the game state is the same after creating the game
  @Test
  public void testGameState() {
    // test default constructor initializes correctly
    EuropeanSolitaireModelImpl defaultGame = new EuropeanSolitaireModelImpl();
    // test constructor with two arguments initializes correctly
    EuropeanSolitaireModelImpl defaultGameWithStart = new EuropeanSolitaireModelImpl(0, 3);
    // test constructor with one arguments initializes correctly
    EuropeanSolitaireModelImpl gameWith5 = new EuropeanSolitaireModelImpl(5);
    // test constructor with three arguments initializes correctly
    EuropeanSolitaireModelImpl gameWith3Param = new EuropeanSolitaireModelImpl(5, 0, 6);
    assertEquals("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    ""
                    + "  O O O O O\n" +
                    "    O O O",
            defaultGame.getGameState());
    assertEquals("    O _ O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    ""
                    + "  O O O O O\n" +
                    "    O O O",
            defaultGameWithStart.getGameState());
    assertEquals("        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    ""
                    + "      O O O O O O O\n" +
                    "        O O O O O",
            gameWith5.getGameState());
    assertEquals("        O O _ O O\n" +
                    "      O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    ""
                    + "      O O O O O O O\n" +
                    "        O O O O O",
            gameWith3Param.getGameState());
    // constructor with invalid position for empty slot
    try {
      EuropeanSolitaireModelImpl invalidGame = new EuropeanSolitaireModelImpl(0, 0);
      fail("Invalid empty cell position (0,0)");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid empty cell position (0,0)");
    }
    // constructor with non positive odd number for arm thickness for constructor with one argument
    try {
      EuropeanSolitaireModelImpl invalidGame = new EuropeanSolitaireModelImpl(4);
      fail("4 is not a positive odd integer greater than 1");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "4 is not a positive odd integer greater than 1");
    }
    // constructor with non positive odd number for arm thickness for constructor
    // with three arguments
    try {
      EuropeanSolitaireModelImpl invalidGame = new EuropeanSolitaireModelImpl(4, 0, 6);
      fail("4 is not a positive odd integer greater than 1");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "4 is not a positive odd integer greater than 1");
    }
    // another test to verify validity of a previous test
    try {
      EuropeanSolitaireModelImpl invalidGame = new EuropeanSolitaireModelImpl(1, 0);
      fail("Invalid empty cell position (1,0)");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid empty cell position (1,0)");
    }
    // another test to verify validity of a previous test
    try {
      EuropeanSolitaireModelImpl invalidGame = new EuropeanSolitaireModelImpl(6);
      fail("6 is not a positive odd integer greater than 1");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "6 is not a positive odd integer greater than 1");
    }
    // another test to verify validity of a previous test
    try {
      EuropeanSolitaireModelImpl invalidGame = new EuropeanSolitaireModelImpl(12, 5, 9);
      fail("12 is not a positive odd integer greater than 1");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "12 is not a positive odd integer greater than 1");
    }
  }


  // Test that the getScore method works correctly
  @Test
  public void testGetScore() {
    EuropeanSolitaireModelImpl defaultGame = new EuropeanSolitaireModelImpl();
    EuropeanSolitaireModelImpl gameWith5 = new EuropeanSolitaireModelImpl(5);
    assertEquals(36,
            defaultGame.getScore());
    assertEquals(128,
            gameWith5.getScore());

  }


  // Test the game state is accurate after valid making moves, and errors are thrown after making
  // invalid moves.
  @Test
  public void testMove() {
    EuropeanSolitaireModelImpl defaultGame = new EuropeanSolitaireModelImpl();
    // trying to move from 2 above
    defaultGame.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    ""
                    + "  O O O O O\n" +
                    "    O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move from 2 below
    defaultGame.move(4, 3, 2, 3);
    assertEquals("    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O _ O O O\n" +
                    ""
                    + "  O O O O O\n" +
                    "    O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move from 2 to the left
    defaultGame.move(1, 1, 1, 3);
    assertEquals("    O O O\n" +
                    "  _ _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O _ O O O\n" +
                    ""
                    + "  O O O O O\n" +
                    "    O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move from 2 to the right
    defaultGame.move(1, 4, 1, 2);
    assertEquals("    O O O\n" +
                    "  _ O _ _ O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O _ O O O\n" +
                    ""
                    + "  O O O O O\n" +
                    "    O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move to invalid to position
    try {
      defaultGame.move(0, 3, 0, 0);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move from invalid from position
    try {
      defaultGame.move(0, 0, 0, 3);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move to a position that is not empty
    try {
      defaultGame.move(0, 2, 0, 4);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move jumping over an empty slot
    try {
      defaultGame.move(1, 2, 1, 4);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move to position that are more than two positions apart
    try {
      defaultGame.move(2, 0, 2, 4);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move diagonal but still two positions away
    try {
      defaultGame.move(5, 1, 3, 3);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
  }
}