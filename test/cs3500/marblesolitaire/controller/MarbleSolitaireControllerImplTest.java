package cs3500.marblesolitaire.controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.InputInteraction;
import cs3500.marblesolitaire.Interaction;
import cs3500.marblesolitaire.PrintInteraction;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for the marble solitaire controller.
 */
public class MarbleSolitaireControllerImplTest {

  void testRun(MarbleSolitaireModel model, Interaction... interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, actualOutput);
    controller.playGame(model);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  // Test that enull models are not allowed
  @Test
  public void testForNull() {
    StringBuilder myAppendable = new StringBuilder();
    StringReader myReadable = new StringReader("222");

    MarbleSolitaireModel myModel = new MarbleSolitaireModelImpl();
    MarbleSolitaireController myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);

    try {
      myController.playGame(null);
      fail("Model cannot be null");
    } catch (IllegalArgumentException illegalArg) {
      assertEquals(illegalArg.getMessage(), "Model cannot be null");
    }
  }

  // Test that the game plays till completion
  @Test
  public void testForCompletion() throws Exception {
    testRun(new TriangleSolitaireModelImpl(3),
            new InputInteraction(""),
            new PrintInteraction("    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    "Score: 5"),
            new InputInteraction("3 1 1 1"),
            new PrintInteraction("    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    "Score: 4"),
            new InputInteraction("3 3 3 1"),
            new PrintInteraction("    O\n" +
                    "   _ O\n" +
                    "  O _ _\n" +
                    "Score: 3"),
            new InputInteraction("1 1 3 3"),
            new PrintInteraction("Game over!\n" +
                    "    _\n" +
                    "   _ _\n" +
                    "  O _ O\n" +
                    "Score: 2"));
  }

  // Test that the game is quit when you provide an input of 'q' or 'Q' for the from-row
  @Test
  public void testQuit1() throws Exception {
    testRun(new TriangleSolitaireModelImpl(3),
            new InputInteraction("q 3 1 1"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    "Score: 5"));
  }

  // Test that the game is quit when you provide an input of 'q' or 'Q' for the from-column
  @Test
  public void testQuit2() throws Exception {
    testRun(new TriangleSolitaireModelImpl(3),
            new InputInteraction("3 q 1 1"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    "Score: 5"));
  }


  // Test that the game is quit when you provide an input of 'q' or 'Q' for the to-row
  @Test
  public void testQuit3() throws Exception {
    testRun(new EuropeanSolitaireModelImpl(3),
            new InputInteraction("3 3 Q 1"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    "Score: 5"));
  }

  // Test that the game is quit when you provide an input of 'q' or 'Q' for the to-column
  @Test
  public void testQuit4() throws Exception {
    testRun(new EuropeanSolitaireModelImpl(3),
            new InputInteraction("3 3 1 Q"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    "Score: 5"));
  }

  // Test that the game returns correctly after premature quit
  @Test
  public void testQuit5() throws Exception {
    testRun(new EuropeanSolitaireModelImpl(3),
            new InputInteraction("Q"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    "Score: 5"));
  }
  /*
  // Test if you quit right away
  @Test
  public void test0() throws Exception {
    testRun(new MarbleSolitaireModelImpl(),
            new InputInteraction(""),
            new PrintInteraction("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32"),
            new InputInteraction("4 2 4 4"),
            new PrintInteraction("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32"),
            new InputInteraction("2 3 4 3"),

            new InputInteraction("4 5 4 3"),
            new PrintInteraction("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ _ O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32"),

            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32"));
  }


  // Test if you quit right away
  @Test
  public void test1() throws Exception {
    StringReader myReadable = new StringReader("q \n");
    StringBuilder myAppendable = new StringBuilder();

    MarbleSolitaireControllerImpl myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(new MarbleSolitaireModelImpl());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", myAppendable.toString());
  }

  // Test a valid move
  @Test
  public void test2() {
    StringReader myReadable = new StringReader("2 4 4 4 q");
    StringBuilder myAppendable = new StringBuilder();

    MarbleSolitaireControllerImpl myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(new MarbleSolitaireModelImpl());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", myAppendable.toString());
  }


  // Test a valid move with invalid chars in the read
  @Test
  public void myTest2() {
    //Readable input = new InputStreamReader(System.in);
    //Appendable output = System.out;
    StringBuilder output = new StringBuilder();
    StringReader input = new StringReader(" 4 6 4 4 q");
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    new MarbleSolitaireControllerImpl(input, output).playGame(model);

    StringBuilder myAppendable = new StringBuilder();
    StringReader myReadable = new StringReader("A 4 6 4 4 A q");

    MarbleSolitaireModel myModel = new MarbleSolitaireModelImpl();
    MarbleSolitaireController myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(myModel);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", myModel.getGameState());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", output);
  }

  // FOR THE ERROR TESTS JUST DISREGARD THE ASSERT EQUALS
  // I WAS GETTING AN ERROR WHEN I DID NOT INCLUDE IT,
  // BUT THE PROPER ERRORS ARE GETTING

  // Test that errors are thrown
  @Test(expected = IllegalStateException.class)
  public void myTest3() {
    StringBuilder myAppendable = new StringBuilder();
    StringReader myReadable = new StringReader("A");

    MarbleSolitaireModel myModel = new MarbleSolitaireModelImpl();
    MarbleSolitaireController myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(myModel);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", myAppendable.toString());
  }

  // Test that errors are thrown
  @Test(expected = IllegalStateException.class)
  public void myTest4() {
    StringBuilder myAppendable = new StringBuilder();
    StringReader myReadable = new StringReader("");

    MarbleSolitaireModel myModel = new MarbleSolitaireModelImpl();
    MarbleSolitaireController myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(myModel);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", myAppendable.toString());
  }

  // Test that errors are thrown
  @Test(expected = IllegalStateException.class)
  public void myTest5() {
    StringBuilder myAppendable = new StringBuilder();
    StringReader myReadable = new StringReader("aaa");

    MarbleSolitaireModel myModel = new MarbleSolitaireModelImpl();
    MarbleSolitaireController myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(myModel);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", myAppendable.toString());
  }

  // Test that errors are thrown
  @Test(expected = IllegalStateException.class)
  public void myTest6() {
    StringBuilder myAppendable = new StringBuilder();
    StringReader myReadable = new StringReader("aaa");

    MarbleSolitaireModel myModel = new MarbleSolitaireModelImpl();
    MarbleSolitaireController myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(myModel);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", myAppendable.toString());
  }

  // Test that errors are thrown
  @Test(expected = IllegalStateException.class)
  public void myTest7() {
    StringBuilder myAppendable = new StringBuilder();
    StringReader myReadable = new StringReader("222");

    MarbleSolitaireModel myModel = new MarbleSolitaireModelImpl();
    MarbleSolitaireController myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(myModel);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", myAppendable.toString());
  }

  // Test that errors are thrown
  @Test(expected = IllegalStateException.class)
  public void myTest8() {
    StringBuilder myAppendable = new StringBuilder();
    StringReader myReadable = new StringReader("4 4");

    MarbleSolitaireModel myModel = new MarbleSolitaireModelImpl();
    MarbleSolitaireController myController =
            new MarbleSolitaireControllerImpl(myReadable, myAppendable);
    myController.playGame(myModel);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", myAppendable.toString());
  }
  */
}