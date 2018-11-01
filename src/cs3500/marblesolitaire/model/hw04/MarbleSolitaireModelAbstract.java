package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireCell;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents the implementation of the operations offered by the marble solitaire model.
 * This is an abstract class that is extended by the three versions of the game: American, European,
 * and Triangle. One object of the model represents playing one game of marble solitaire. The model
 * stores all information about the current implementation of the game.
 */
public abstract class MarbleSolitaireModelAbstract implements MarbleSolitaireModel {
  protected final int armThickness;
  protected final int sRow;
  protected final int sCol;
  // A gameboard is an array of array of cells representing cells on the gameboard.
  // It is generated based upon the inputted conditions of the game.
  // American and European versions have placeholder
  protected MarbleSolitaireCell[][] gameBoard;

  /**
   * Constructor for MarbleSolitaireModelImpl that takes in parameters to set the conditions of the
   * game.
   *
   * @param armThickness thickness of the arm in European or American versions, or base of the
   *                     triangle in Triangle versions of the game.
   * @param sRow         Start row of the game.
   * @param sCol         Start columns of the game.
   * @param gameBoard    The structure of all the cells in the game and whether they are a
   *                     placeholder, contain a marble, or is empty.
   */
  public MarbleSolitaireModelAbstract(int armThickness, int sRow, int sCol,
                                      MarbleSolitaireCell[][] gameBoard)
          throws IllegalArgumentException {
    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
    this.gameBoard = gameBoard;
  }

  /**
   * Checks whether the game has valid parameters. For American and European versions, the
   * side-length must be greater than one, an integer, and odd. The start position must also be
   * within the confines of the board.
   *
   * @param sideLength Thickness of the arm in European or American versions, or base of the
   *                   triangle in Triangle versions of the game.
   * @param sRow       Start row of the game.
   * @param sCol       Start columns of the game.
   */
  protected static void validGameParameters(int sideLength, int sRow, int sCol) {
    if (sideLength < 1 || sideLength % 2 == 0) {
      throw new IllegalArgumentException(String.format("%d is not a positive odd integer greater "
              + "than 1", sideLength));
    } else if (Math.min(sCol, sRow) < 0 || Math.max(sCol, sRow) >= sideLength * 3 - 2) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d,%d)",
              sRow, sCol));
    }
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
  public abstract void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException;

  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. Specific implementations may place additional constraints
   * on the validity of a move.
   *
   * @param fromRow    the row number of the position to be moved from (starts at 0)
   * @param fromCol    the column number of the position to be moved from (starts at 0)
   * @param toRow      the row number of the position to be moved to (starts at 0)
   * @param toCol      the column number of the position to be moved to (starts at 0)
   * @param isTriangle Whether the move is being attempted on a triangle board.
   * @throws IllegalArgumentException if the move is not possible
   */
  protected void moveHelper(int fromRow, int fromCol, int toRow, int toCol, boolean isTriangle) {
    if (isTriangle && isValidDiagonalMove(fromRow, fromCol, toRow, toCol)) {
      // remove the marble from the from cell and add a marble to the to cell
      this.gameBoard[fromRow][fromCol].hasMarble = false;
      this.gameBoard[toRow][toCol].hasMarble = true;
      this.gameBoard[(toRow + fromRow) / 2][(toCol + fromCol) / 2].hasMarble = false;
    } else if (!isTriangle && isValidMove(fromRow, fromCol, toRow, toCol)) {
      // remove the marble from the from cell and add a marble to the to cell
      this.gameBoard[fromRow][fromCol].hasMarble = false;
      this.gameBoard[toRow][toCol].hasMarble = true;
      this.gameBoard[(toRow + fromRow) / 2][(toCol + fromCol) / 2].hasMarble = false;
    } else {
      //+fromRow+","+fromCol+","+toRow+","+toCol
      throw new IllegalArgumentException("Invalid move parameters");
    }
  }

  /**
   * Checks the validity of a given move, in a horizontal or vertical direction on European or
   * American boards. Also checks validity of a horizontal move on a triangular board. Because of
   * the structure of a triangular game board, this also checks the validity of a diagonal move in
   * the north-east or south-west direction.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (Math.min(fromRow, fromCol) < 0 || Math.min(toRow, toCol) < 0
            || Math.max(fromRow, fromCol) > this.armThickness * 3 - 2
            || Math.max(toRow, toCol) >= this.armThickness * 3 - 2) {
      return false;
    } else if (this.gameBoard[fromRow][fromCol].isPlaceholder
            || this.gameBoard[toRow][toCol].isPlaceholder) {
      return false;
    } else if (!((fromRow == toRow) || (fromCol == toCol))) {
      return false;
    } else if (!this.gameBoard[fromRow][fromCol].hasMarble
            || this.gameBoard[toRow][toCol].hasMarble) {
      return false;
    } else {
      return ((Math.abs(fromRow - toRow) == 2
              && this.gameBoard[(fromRow + toRow) / 2][fromCol].hasMarble)
              || (Math.abs(fromCol - toCol) == 2
              && this.gameBoard[fromRow][(fromCol + toCol) / 2].hasMarble));
    }
  }

  /**
   * Checks the validity of a given move, in a south-east or north-west direction direction on a
   * triangular game board.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  private boolean isValidDiagonalMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (Math.min(fromRow, toRow) < 0 || Math.min(fromCol, toCol) < 0
            || Math.max(fromRow, toRow) >= this.armThickness
            || Math.max(fromCol, toCol) >= this.armThickness
            || fromCol > fromRow || toCol > toRow) {
      return false;
    } else if (!this.gameBoard[fromRow][fromCol].hasMarble
            || this.gameBoard[toRow][toCol].hasMarble) {
      return false;
    } else if (isValidMove(fromRow, fromCol, toRow, toCol)) {
      return true;
    } else if (!(Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2)) {
      return false;
    } else {
      return this.gameBoard[(fromRow + toRow) / 2][(fromCol + toCol) / 2].hasMarble;
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  public abstract boolean isGameOver();


  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @param isTriangle whether the game board is triangular or not.
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOverHelper(boolean isTriangle) {
    for (int i = 0; i < this.gameBoard.length; i++) {
      MarbleSolitaireCell[] row = this.gameBoard[i];

      for (int j = 0; j < row.length; j++) {
        MarbleSolitaireCell cell = row[j];
        if (isTriangle && (isValidDiagonalMove(i, j, i + 2, j + 2)
                || isValidDiagonalMove(i, j, i - 2, j - 2)
                || isValidDiagonalMove(i, j, i, j + 2)
                || isValidDiagonalMove(i, j, i, j - 2)
                || isValidDiagonalMove(i, j, i + 2, j)
                || isValidDiagonalMove(i, j, i - 2, j))) {
          return false;
        } else if (!isTriangle && !cell.isPlaceholder && (isValidMove(i, j, i, j + 2)
                || isValidMove(i, j, i + 2, j) || isValidMove(i, j, i - 2, j)
                || isValidMove(i, j, i, j - 2))) {
          return false;
        }
      }
    }
    return true;
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
  public abstract String getGameState();


  /**
   * Helper for getGameState that returns the state of the game as a string. Because of the game
   * board difference between European/American boards and Triangle boards this helper method is
   * necessary. If the game state is needed for a triangle, spaces need to be added in front of each
   * row except the last. There are no placeholders in the triangle game board.
   *
   * @param isTriangle whether the game state is for a triangle game board or not.
   * @return the game state as a string
   */
  protected String getGameStateHelper(boolean isTriangle) {
    StringBuilder state = new StringBuilder();
    boolean endOfRow = false;

    for (int i = 0; i < this.gameBoard.length; i++) {
      MarbleSolitaireCell[] row = this.gameBoard[i];
      if (isTriangle) {
        for (int k = 0; k < this.gameBoard.length - i - 1; k++) {
          state.append(" ");
        }
      }
      for (MarbleSolitaireCell cell : row) {
        if (cell.isPlaceholder && !endOfRow) {
          state.append("  ");
        } else if (cell.hasMarble) {
          state.append("O ");
          endOfRow = true;
        } else if (!cell.isPlaceholder) {
          state.append("_ ");
        }
      }
      state.deleteCharAt(state.length() - 1);
      state.append("\n");
      endOfRow = false;
    }
    return state.substring(0, state.length() - 1);
  }


  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int counter = 0;

    // iterate through all rows in the game board
    for (MarbleSolitaireCell[] row : this.gameBoard) {
      // iterate through all cells in the row
      for (MarbleSolitaireCell cell : row) {
        // increase the counter if the cell has a marble in it
        if (cell.hasMarble) {
          counter++;
        }
      }
    }
    return counter;
  }

  /**
   * Generates an array of array of cells where each array represents a row and each
   * MarbleSolitaireCell represents a cell in the game. Each cell is either a placeholder or not,
   * and either has a marble or no marble. Although the European and American games are quite
   * similar, the European board has a few more cells in the game.
   *
   * @param sideLength the length of an arm.
   * @param sRow       row of the initial start cell that is empty.
   * @param sCol       column of the initial start cell that is empty.
   * @param isEuropean whether the game is being built by european standards or not.
   */
  protected static MarbleSolitaireCell[][] gameBoardGenerator(int sideLength, int sRow, int sCol,
                                                              boolean isEuropean) {
    validGameParameters(sideLength, sRow, sCol);
    int width = sideLength * 3 - 2;
    int placeholders = width - sideLength;
    MarbleSolitaireCell[][] gameBoardAcc = new MarbleSolitaireCell[width][width];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < width; j++) {

        if (j < placeholders / 2 || j >= width - (placeholders / 2)) {
          gameBoardAcc[i][j] = new MarbleSolitaireCell(i, j, true);
        } else {
          gameBoardAcc[i][j] = new MarbleSolitaireCell(i, j);
        }
      }


      if (i == sideLength * 2 - 2 && isEuropean) {
        placeholders = 2;
      } else if (i < sideLength - 1 && isEuropean) {
        placeholders -= 2;
      } else if (i > sideLength * 2 - 2 && isEuropean) {
        placeholders += 2;
      } else if (i == sideLength - 2) {
        placeholders = 0;
      } else if (i == sideLength * 2 - 2) {
        placeholders = width - sideLength;

      }
    }
    if (gameBoardAcc[sRow][sCol].isPlaceholder) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d,%d)",
              sRow, sCol));
    } else {
      gameBoardAcc[sRow][sCol].hasMarble = false;
    }
    return gameBoardAcc;
  }
}
