package cs3500.marblesolitaire;

/**
 * Represents a user providing the program with an input.
 */
public interface Interaction {
  /**
   * Apply method for a given input and designated output to be used. Makes for easier testing.
   *
   * @param in  the input string builder.
   * @param out the output string builder.
   */
  void apply(StringBuilder in, StringBuilder out);

  /**
   * Prints out all of the given lines.
   *
   * @param lines the lines to append to the output
   */
  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }
}
