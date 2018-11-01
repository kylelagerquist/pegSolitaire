package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireCell;

/**
 * This class represents the implementation of the operations offered by the marble solitaire model.
 * One object of the model represents playing one game of marble solitaire. The model stores all
 * information about the current implementation of the game. This specific model is for the
 * triangular implementation of the game. The game board is generated as a right triangle. There are
 * no placeholder cells like there are in other models. Essentially, if the game board would be
 * drawn out, there would be no empty spaces generated from spaces on the left hand side.
 */
public class TriangleSolitaireModelImpl extends MarbleSolitaireModelAbstract {
  /**
   * Constructor for MarbleSolitaireModelImpl all fields are default.
   */
  public TriangleSolitaireModelImpl() {
    super(5, 0, 0,
            triangleBoardGenerator(5, 0, 0));
  }

  /**
   * Constructor for TriangleSolitaireModelImpl start position is in center and the client decides
   * how big to make the game board. The gameboard must be an odd integer greater than 1.
   *
   * @param armThickness the number of marbles in the top row
   */
  public TriangleSolitaireModelImpl(int armThickness) {
    super(armThickness, 0, 0, triangleBoardGenerator(armThickness, 0, 0));
  }

  /**
   * Constructor for TriangleSolitaireModelImpl arm thickness of 3 is set to default and the client
   * decides where the start position is. the start position must be within the available cells of
   * the default game board
   *
   * @param sRow the row that contains the start cell
   * @param sCol the column that contains the start cell
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    super(5, sRow, sCol, triangleBoardGenerator(5, sRow, sCol));
  }

  /**
   * Constructor for TriangleSolitaireModelImpl where the client decides how big to make the game
   * board and also where the start position will be. The game board must be an odd integer greater
   * than 1. The start position must be within the available cells of the decided game board.
   *
   * @param armThickness the number of marbles in the top row
   * @param sRow         the row that contains the start cell
   * @param sCol         the column that contains the start cell
   */
  public TriangleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol, triangleBoardGenerator(armThickness, sRow, sCol));
  }

  /**
   * Generates the triangular game board for the implementation. In the first array there will only
   * be one cell, and then in each following array there is one more cell than the previous. This
   * continues until the length of an array is equal to the desired base length of the triangle.
   * After the game board is generated, the start position is set.
   *
   * @param sideLength the number of marbles in the top row
   * @param sRow       the row that contains the start cell
   * @param sCol       the column that contains the start cell
   */
  private static MarbleSolitaireCell[][]
    triangleBoardGenerator(int sideLength, int sRow, int sCol) {
    validGameParameters(sideLength, sRow, sCol);
    MarbleSolitaireCell[][] gameBoardAcc = new MarbleSolitaireCell[sideLength][];
    for (int i = 0; i < sideLength; i++) {
      MarbleSolitaireCell[] rowAcc = new MarbleSolitaireCell[i + 1];
      for (int j = 0; j <= i; j++) {
        rowAcc[j] = new MarbleSolitaireCell(i, j);
      }
      gameBoardAcc[i] = rowAcc;
    }
    gameBoardAcc[sRow][sCol].hasMarble = false;
    return gameBoardAcc;
  }

  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character (O, X or space for
   * a marble, empty and invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  @Override
  public String getGameState() {
    return getGameStateHelper(true);
  }

  /**
   * Overrides the defined validGameParameters method in the interface because the game parameters
   * for a triangular game are different than the others. This method ensures that the side length
   * is greater than 1 and that the start position is within the confines of the game.
   *
   * @return the game state as a string
   */
  protected static void validGameParameters(int sideLength, int sRow, int sCol) {
    if (sideLength <= 1) {
      throw new IllegalArgumentException(String.format("%d is not a positive odd integer greater "
              + "than 1", sideLength));
    } else if (sCol > sRow || sRow >= sideLength) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d,%d)",
              sRow, sCol));
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return isGameOverHelper(true);
  }

  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. Specific implementations may place additional constraints
   * on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.moveHelper(fromRow, fromCol, toRow, toCol, true);
  }
}
