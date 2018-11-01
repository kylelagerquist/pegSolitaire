package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This interface represents the operations offered by the marble solitaire controller. One object
 * of the controller represents playing one game of marble solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a game of marble solitaire. The game is textual based and receives input from a user to
   * make moves or quit the game. Moves must be valid to be made.
   *
   * @param model the row number of the position to be moved from (starts at 0)
   */
  void playGame(MarbleSolitaireModel model);
}
