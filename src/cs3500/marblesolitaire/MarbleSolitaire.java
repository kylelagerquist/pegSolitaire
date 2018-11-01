package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

/**
 * This class represents the main method to play the marble solitaire game. Any version of the game
 * can be played depending on what the user passes in as arguments. The user must pass one of
 * english, european, or triangular. This argument will decide which board shape (and hence which
 * model) will be used. The user may optionally pass the two arguments -size N, where N is a number,
 * to specify the size of the board. If unspecified, it will use the default size for the chosen
 * board shape. Lastly, the user may optionally pass the three arguments -hole R C, where R and C
 * are numbers, to specify the row and column of the initial hole. If unspecified, it will use the
 * default hole position for the chosen board shape
 */
public final class MarbleSolitaire {
  /**
   * Plays a game of marble solitaire. The game is textual based and receives input from a user to
   * make moves or quit the game. Moves must be valid to be made.
   *
   * @param args the parameters of what game will be played
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    MarbleSolitaireModel model;

    String gameType = "";
    boolean hasSize = false;
    boolean hasStartPos = false;
    int size = 0;
    int sRow = 0;
    int sCol = 0;

    // iterate through all of the arguments checking for validity of the arguments.
    for (int i = 0; i < args.length; i++) {
      String argument = args[i];

      if (argument.equals("english") || argument.equals("european")
              || argument.equals("triangular")) {
        gameType = argument;
      } else if (argument.equals("-size") && i < args.length - 1) {
        try {
          int givenSize = Integer.parseInt(args[i + 1]);
          size = givenSize;
          hasSize = true;
          i++;
        } catch (Exception e) {
          System.out.println("Invalid command-line arguments. " + e.getMessage());
          return;
        }
      } else if (argument.equals("-hole") && i < args.length - 2) {
        try {
          int givenSRow = Integer.parseInt(args[i + 1]);
          int givenSCol = Integer.parseInt(args[i + 2]);
          sRow = givenSRow - 1;
          sCol = givenSCol - 1;
          hasStartPos = true;
          i++;
          i++;
        } catch (Exception e) {
          System.out.println("Invalid command-line arguments.");
          return;
        }
      } else {
        System.out.println("Invalid command-line arguments.");
        return;
      }
    }


    // extremely repetitive code but the instructions do say that "you do not need to explicitly
    // handle invalid command lines (e.g. by producing an informative error message)". This was
    // just extra for my case in order to further test and check outputs. If this was part of the
    // assignment the time would have been spent to abstract this code.
    try {
      if (gameType.equals("english")) {
        if (hasSize && hasStartPos) {
          model = new MarbleSolitaireModelImpl(size, sRow, sCol);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else if (hasSize) {
          model = new MarbleSolitaireModelImpl(size);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else if (hasStartPos) {
          model = new MarbleSolitaireModelImpl(sRow, sCol);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else {
          model = new MarbleSolitaireModelImpl();
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        }
      } else if (gameType.equals("european")) {
        if (hasSize && hasStartPos) {
          model = new EuropeanSolitaireModelImpl(size, sRow, sCol);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else if (hasSize) {
          model = new EuropeanSolitaireModelImpl(size);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else if (hasStartPos) {
          model = new EuropeanSolitaireModelImpl(sRow, sCol);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else {
          model = new EuropeanSolitaireModelImpl();
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        }
      } else if (gameType.equals("triangular")) {
        if (hasSize && hasStartPos) {
          model = new TriangleSolitaireModelImpl(size, sRow, sCol);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else if (hasSize) {
          model = new TriangleSolitaireModelImpl(size);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else if (hasStartPos) {
          model = new TriangleSolitaireModelImpl(sRow, sCol);
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        } else {
          model = new TriangleSolitaireModelImpl();
          new MarbleSolitaireControllerImpl(input, output).playGame(model);
        }
      } else {
        System.out.println("Invalid command-line arguments.");
        return;
      }
    } catch (Exception e) {
      System.out.println("Invalid command-line arguments");
      return;
    }
  }
}

