package cs3500.marblesolitaire;

/**
 * Represents a user providing the program with an input.
 */
public class InputInteraction implements Interaction {
  String input;

  /**
   * Constructor that takes in a singular string input.
   *
   * @param input the string to be input.
   */
  public InputInteraction(String input) {
    this.input = input;
  }

  /**
   * Apply method for a given input and designated output to be used. Makes for easier testing.
   *
   * @param in the input string builder.
   * @param out the output string builder.
   */
  public void apply(StringBuilder in, StringBuilder out) {
    in.append(input);
  }

  /**
   * Designates an interaction with the program that takes in an input to the program.
   *
   * @param in the input string.
   */
  static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }
}
