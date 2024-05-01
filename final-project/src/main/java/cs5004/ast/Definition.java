package cs5004.ast;

import java.util.List;
import java.util.Objects;

/**
 * Represents a top-level function definition.
 */
public class Definition {
  private final String name;
  private final List<String> arguments;
  private final Expression body;

  /**
   * Constructs a new function definition.
   * @param name the name of the function
   * @param arguments the name of the function's arguments, in order from left
   *                  to right.  Must not be null; can be empty.
   * @param body the function's body expression
   */
  public Definition(String name, List<String> arguments, Expression body) {
    this.name = Objects.requireNonNull(name);
    this.arguments = List.copyOf(Objects.requireNonNull(arguments));
    this.body = Objects.requireNonNull(body);
  }

  public String getName() {
    return name;
  }

  public List<String> getArguments() {
    return arguments;
  }

  public Expression getBody() {
    return body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Definition that = (Definition) o;
    return Objects.equals(name, that.name) && Objects.equals(
      arguments, that.arguments) && Objects.equals(body, that.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, arguments, body.format());
  }

  @Override
  public String toString() {
    return String.format("Definition(%s, %s, %s)", name, arguments, body.format());
  }
}
