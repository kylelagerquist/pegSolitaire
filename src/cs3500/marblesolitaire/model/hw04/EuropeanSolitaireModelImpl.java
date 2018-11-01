package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents the implementation of the operations offered by the marble solitaire model.
 * One object of the model represents playing one game of marble solitaire. The model stores all
 * information about the current implementation of the game. This specific model is for the European
 * implementation of the game. The game board is generated as a sqaure, with the corner positions
 * being a placeholder cell.
 */
public class EuropeanSolitaireModelImpl extends MarbleSolitaireModelAbstract {
  /**
   * Constructor for EuropeanSolitaireModelImpl all fields are default.
   */
  public EuropeanSolitaireModelImpl() {
    super(3, 3, 3,
            gameBoardGenerator(3, 3, 3, true));
  }

  /**
   * Constructor for EuropeanSolitaireModelImpl start position is in center and the client decides
   * how big to make the game board. The gameboard must be an odd integer greater than 1.
   *
   * @param armThickness the number of marbles in the top row
   */
  public EuropeanSolitaireModelImpl(int armThickness) {
    super(armThickness, 3, 3, gameBoardGenerator(armThickness,
            (armThickness * 3 - 2) / 2, (armThickness * 3 - 2) / 2, true));
  }

  /**
   * Constructor for EuropeanSolitaireModelImpl arm thickness of 3 is set to default and the client
   * decides where the start position is. the start position must be within the available cells of
   * the default game board
   *
   * @param sRow the row that contains the start cell
   * @param sCol the column that contains the start cell
   */
  public EuropeanSolitaireModelImpl(int sRow, int sCol) {
    super(3, sRow, sCol, gameBoardGenerator(3, sRow, sCol, true));
  }

  /**
   * Constructor for EuropeanSolitaireModelImpl where the client decides how big to make the game
   * board and also where the start position will be. The game board must be an odd integer greater
   * than 1. The start position must be within the available cells of the decided game board.
   *
   * @param armThickness the number of marbles in the top row
   * @param sRow         the row that contains the start cell
   * @param sCol         the column that contains the start cell
   */
  public EuropeanSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol, gameBoardGenerator(armThickness, sRow, sCol, true));
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
    this.moveHelper(fromRow, fromCol, toRow, toCol, false);
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return isGameOverHelper(false);
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
    return this.getGameStateHelper(false);
  }
}
