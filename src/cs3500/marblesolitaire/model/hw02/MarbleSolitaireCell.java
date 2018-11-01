package cs3500.marblesolitaire.model.hw02;

/**
 * This class represents the required data for each instance of a cell in marble solitaire. One
 * object of the marble solitaire cell represents one single cell in the game.
 */
public class MarbleSolitaireCell {
  final int row;
  final int col;
  public boolean hasMarble;
  public boolean isPlaceholder;

  /**
   * Constructor for MarbleSolitaireCell where the row and column are set and by defualt there is a
   * marble present that is not being clicked and it is not a placeholder.
   *
   * @param row the row number of the position to be moved from (starts at 0)
   * @param col the column number of the position to be moved from (starts at 0)
   */
  public MarbleSolitaireCell(int row, int col) {
    this.row = row;
    this.col = col;
    this.hasMarble = true;
    this.isPlaceholder = false;
  }

  /**
   * Constructor for MarbleSolitaireCell where the row and column are set, it is a empty placeholder
   * cell in the a corner, and by defualt there is no marble present that is not being clicked.
   *
   * @param row the row number of the position to be moved from (starts at 0)
   * @param col the column number of the position to be moved from (starts at 0)
   */
  public MarbleSolitaireCell(int row, int col, boolean isPlaceholder) {
    this.row = row;
    this.col = col;
    this.hasMarble = false;
    this.isPlaceholder = isPlaceholder;
  }
}
