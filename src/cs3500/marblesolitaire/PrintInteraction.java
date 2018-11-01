package cs3500.marblesolitaire;

/**
 * Represents the printing of a sequence of lines to output.
 */
public class PrintInteraction implements Interaction {
  String[] lines;

  /**
   * Constructor that takes in a singular string input.
   *
   * @param lines all of the lines tobe output.
   */
  public PrintInteraction(String... lines) {
    this.lines = lines;
  }

  /**
   * Apply method for a given input and designated output to be used. Makes for easier testing.
   *
   * @param in  the input string builder.
   * @param out the output string builder.
   */
  public void apply(StringBuilder in, StringBuilder out) {
    for (String line : lines) {
      out.append(line).append("\n");
    }
  }

  /**
   * All of the lines that would be expected to be output by the program.
   *
   * @param lines the input string builder.
   */
  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }
}
