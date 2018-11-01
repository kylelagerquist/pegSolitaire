package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

/**
 * This class represents tests for the marble solitaire controller for the triangular version.
 */
public class TriangleSolitaireModelImplTest {
  // test whether the game state is the same after creating the game
  TriangleSolitaireModelImpl defaultGame = new TriangleSolitaireModelImpl();
  // test constructor with two arguments initializes correctly
  TriangleSolitaireModelImpl defaultGameWithStart = new TriangleSolitaireModelImpl(1, 1);
  // test constructor with one arguments initializes correctly
  TriangleSolitaireModelImpl gameWith7 = new TriangleSolitaireModelImpl(7);
  // test constructor with three arguments initializes correctly
  TriangleSolitaireModelImpl gameWith3Param = new TriangleSolitaireModelImpl(7, 1, 1);

  @Test
  public void testGameState() {
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            defaultGame.getGameState());
    assertEquals(
            "    O\n" +
                    "   O _\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            defaultGameWithStart.getGameState());
    assertEquals("      _\n" +
                    "     O O\n" +
                    "    O O O\n" +
                    "   O O O O\n" +
                    "  O O O O O\n" +
                    " O O O O O O\n" +
                    "O O O O O O O",
            gameWith7.getGameState());
    assertEquals("      O\n" +
                    "     O _\n" +
                    "    O O O\n" +
                    "   O O O O\n" +
                    "  O O O O O\n" +
                    " O O O O O O\n" +
                    "O O O O O O O",
            gameWith3Param.getGameState());
    // constructor with invalid position for empty slot
    try {
      TriangleSolitaireModelImpl invalidGame = new TriangleSolitaireModelImpl(0, 1);
      fail("Invalid empty cell position (0,1)");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid empty cell position (0,1)");
    }
    // constructor with non positive odd number for arm thickness for constructor with one argument
    try {
      TriangleSolitaireModelImpl invalidGame = new TriangleSolitaireModelImpl(1);
      fail("1 is not a positive odd integer greater than 1");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "1 is not a positive odd integer greater than 1");
    }
    // constructor with non positive odd number for arm thickness for constructor
    // with three arguments
    try {
      TriangleSolitaireModelImpl invalidGame = new TriangleSolitaireModelImpl(0, 0, 6);
      fail("0 is not a positive odd integer greater than 1");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "0 is not a positive odd integer greater than 1");
    }
    // another test to verify validity of a previous test
    try {
      TriangleSolitaireModelImpl invalidGame = new TriangleSolitaireModelImpl(6, 0);
      fail("Invalid empty cell position (6,0)");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid empty cell position (6,0)");
    }
    // another test to verify validity of a previous test
    try {
      TriangleSolitaireModelImpl invalidGame = new TriangleSolitaireModelImpl(-3);
      fail("-3 is not a positive odd integer greater than 1");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "-3 is not a positive odd integer greater than 1");
    }
    // another test to verify validity of a previous test
    try {
      TriangleSolitaireModelImpl invalidGame = new TriangleSolitaireModelImpl(0, 5, 9);
      fail("0 is not a positive odd integer greater than 1");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "0 is not a positive odd integer greater than 1");
    }
  }


  // Test that the getScore method works correctly
  @Test
  public void testGetScore() {
    TriangleSolitaireModelImpl defaultGame = new TriangleSolitaireModelImpl();
    TriangleSolitaireModelImpl gameWith7 = new TriangleSolitaireModelImpl(7);
    assertEquals(14,
            defaultGame.getScore());
    assertEquals(27,
            gameWith7.getScore());

  }


  // Test the game state is accurate after valid making moves, and errors are thrown after making
  // invalid moves.
  @Test
  public void testMove() {
    TriangleSolitaireModelImpl defaultGame = new TriangleSolitaireModelImpl();
    // trying to move in north-east direction
    defaultGame.move(2, 0, 0, 0);
    assertEquals(
            "    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move in north-west direction
    defaultGame.move(3, 2, 1, 0);
    assertEquals(
            "    O\n" +
                    "   O O\n" +
                    "  _ _ O\n" +
                    " O O _ O\n" +
                    "O O O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move in south-west direction
    defaultGame.move(0, 0, 2, 0);
    assertEquals(
            "    _\n" +
                    "   _ O\n" +
                    "  O _ O\n" +
                    " O O _ O\n" +
                    "O O O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move in east direction
    defaultGame.move(3, 0, 3, 2);
    assertEquals(
            "    _\n" +
                    "   _ O\n" +
                    "  O _ O\n" +
                    " _ _ O O\n" +
                    "O O O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move in west direction
    defaultGame.move(3, 3, 3, 1);
    assertEquals(
            "    _\n" +
                    "   _ O\n" +
                    "  O _ O\n" +
                    " _ O _ _\n" +
                    "O O O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    // trying to move in south-east direction
    defaultGame.move(1, 1, 3, 3);
    assertEquals(
            "    _\n" +
                    "   _ _\n" +
                    "  O _ _\n" +
                    " _ O _ O\n" +
                    "O O O O O",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    defaultGame.move(4, 4, 2, 2);
    assertEquals(
            "    _\n" +
                    "   _ _\n" +
                    "  O _ O\n" +
                    " _ O _ _\n" +
                    "O O O O _",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    defaultGame.move(4, 1, 2, 1);
    assertEquals(
            "    _\n" +
                    "   _ _\n" +
                    "  O O O\n" +
                    " _ _ _ _\n" +
                    "O _ O O _",
            defaultGame.getGameState());
    assertEquals(false,
            defaultGame.isGameOver());
    defaultGame.move(4, 2, 4, 4);
    assertEquals(
            "    _\n" +
                    "   _ _\n" +
                    "  O O O\n" +
                    " _ _ _ _\n" +
                    "O _ _ _ O",
            defaultGame.getGameState());
    assertEquals(true,
            defaultGame.isGameOver());
    // trying to move to invalid to position
    try {
      defaultGame.move(2, 0, 2, 6);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move from invalid from position
    try {
      defaultGame.move(0, 2, 0, 0);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move to a position that is not empty
    try {
      defaultGame.move(5, 0, 3, 0);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move jumping over an empty slot
    try {
      defaultGame.move(3, 0, 0, 0);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
    // trying to move to position that are more than two positions apart
    try {
      defaultGame.move(3, 3, 0, 0);
      fail("Invalid move parameters");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Invalid move parameters");
    }
  }
}