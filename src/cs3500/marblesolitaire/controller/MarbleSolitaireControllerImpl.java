package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents the implementation of the operations offered by the marble solitaire
 * controller. One object of the controller represents playing one game of marble solitaire. The
 * controller essentially runs the program, and facilitates the game through a sequence of
 * operations.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final Readable in;
  private final Appendable out;
  private boolean hasGameStarted = false;
  private int[] intsForMove = new int[4];
  private int numInts = 0;

  /**
   * Constructor for MarbleSolitaireControllerImpl where a input and output are given. Neither are
   * allowed to be null. The input is what will be read by the scanner, and the output is what will
   * be given back to the user.
   *
   * @param in  The readable input of the game.
   * @param out The appendable output of the game.
   * @throws IllegalArgumentException if the either are null
   */
  public MarbleSolitaireControllerImpl(Readable in, Appendable out) {
    try {
      Objects.requireNonNull(in);
      Objects.requireNonNull(out);
    } catch (NullPointerException exp) {
      throw new IllegalArgumentException("Input or Output cannot be null");
    }
    this.in = in;
    this.out = out;
  }


  /**
   * Plays a game of marble solitaire. The game is textual based and receives input from a user to
   * make moves or quit the game. Moves must be valid to be made.
   *
   * @param model The model of the current game of marble solitaire.
   * @throws IllegalArgumentException If the model is null.
   * @throws IllegalStateException    If the Appendable object is unable to transmit output or the
   *                                  Readable object is unable to provide inputs.
   */
  public void playGame(MarbleSolitaireModel model) {
    try {
      Objects.requireNonNull(model);
    } catch (NullPointerException exp) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    Scanner scan = new Scanner(this.in);
    if (!this.hasGameStarted) {
      this.addToTheAppendable("", model, false);
    }

    while (true) {
      String next = scan.next();
      if (this.isAnInteger(next) && this.numInts == 3) {
        try {
          intsForMove[numInts] = Integer.parseInt(next);
          model.move(intsForMove[0] - 1, intsForMove[1] - 1,
                  intsForMove[2] - 1, intsForMove[3] - 1);
          if (model.isGameOver()) {
            this.addToTheAppendable("Game over!\n", model, true);
            return;
          } else {
            this.addToTheAppendable("", model, false);
            this.numInts = 0;
            this.intsForMove = new int[4];
            break;
          }
        } catch (IllegalArgumentException e) {
          try {
            this.out.append("Invalid move. Play again. " + e.getMessage() + ".\n");
            this.numInts = 0;
            this.intsForMove = new int[4];
          } catch (IOException error) {
            error.printStackTrace();
          }
        }
        break;
      } else if (this.isAnInteger(next)) {
        this.intsForMove[numInts] = Integer.parseInt(next);
        this.numInts++;
      } else if (next.equals("q") || next.equals("Q")) {
        this.addToTheAppendable("Game quit!\nState of game when quit:\n", model, true);
        return;
      } else {
        try {
          this.out.append("Invalid input.\n");
          break;
        } catch (IOException error) {
          error.printStackTrace();
        }
      }
    }
    this.hasGameStarted = true;
    this.playGame(model);
  }


  private boolean isAnInteger(String possibleInt) {
    try {
      Integer.parseInt(possibleInt);
      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Helper method to add to the appendable that will be returned to the user. Each time it is
   * called, it adds the message, current game state, score, and a new line if it is not the last
   * line.
   *
   * @param model        The current model of the marble solitaire game.
   * @param messageToAdd The message to add to the appendable.
   * @param over         Whether or not the user is attempting to quit.
   * @throws IllegalStateException if the appendable is unable to transmit output
   */
  private void addToTheAppendable(String messageToAdd, MarbleSolitaireModel model, boolean over) {
    try {
      out.append(messageToAdd);
      out.append(model.getGameState());
      out.append(String.format("\nScore: %d", model.getScore()));
      if (!over) {
        out.append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

